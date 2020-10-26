package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;
import static chopchop.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import chopchop.model.EntryBook;
import chopchop.model.ModelStub;
import chopchop.model.ReadOnlyEntryBook;
import chopchop.model.recipe.Recipe;
import chopchop.testutil.RecipeBuilder;
import org.junit.jupiter.api.Test;

public class AddRecipeCommandTest {

    @Test
    public void constructor_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRecipeCommand(null));
    }

    @Test
    public void execute_recipeAcceptedByModel_addSuccessful() throws Exception {
        var modelStub = new ModelStubAcceptingRecipeAdded();
        var validRecipe = new RecipeBuilder().build();

        var result = new AddRecipeCommand(validRecipe)
            .execute(modelStub, new CommandTestUtil.HistoryManagerStub());

        assertTrue(result.didSucceed());
        assertEquals(Arrays.asList(validRecipe), modelStub.recipesAdded);
    }

    @Test
    public void equals() {
        var appleSalad = new RecipeBuilder().withName("Apple Salad").build();
        var bananaSalad = new RecipeBuilder().withName("Banana Salad").build();
        var addAppleSaladCommand = new AddRecipeCommand(appleSalad);
        var addBananaSaladCommand = new AddRecipeCommand(bananaSalad);

        // same object -> returns true
        assertTrue(addAppleSaladCommand.equals(addAppleSaladCommand));

        // same values -> returns true
        var addAmericanoCommand = new AddRecipeCommand(appleSalad);
        assertTrue(addAppleSaladCommand.equals(addAmericanoCommand));

        // different types -> returns false
        assertFalse(addAppleSaladCommand.equals(1));

        // null -> returns false
        assertFalse(addAppleSaladCommand.equals(null));

        // different values -> returns false
        assertFalse(addAppleSaladCommand.equals(addBananaSaladCommand));
    }

    /**
     * A Model stub that contains a single recipe.
     */
    private class ModelStubWithRecipe extends ModelStub {
        private final Recipe recipe;

        ModelStubWithRecipe(Recipe recipe) {
            requireNonNull(recipe);
            this.recipe = recipe;
        }

        @Override
        public boolean hasRecipe(Recipe recipe) {
            requireNonNull(recipe);
            return this.recipe.isSame(recipe);
        }

        @Override
        public Optional<Recipe> findRecipeWithName(String name) {
            requireNonNull(name);
            return this.recipe.getName().equalsIgnoreCase(name)
                ? Optional.of(this.recipe)
                : Optional.empty();
        }
    }

    /**
     * A Model stub that always accepts the recipe being added.
     */
    private class ModelStubAcceptingRecipeAdded extends ModelStub {
        final ArrayList<Recipe> recipesAdded = new ArrayList<>();

        @Override
        public boolean hasRecipe(Recipe recipe) {
            requireNonNull(recipe);
            return recipesAdded.stream().anyMatch(recipe::isSame);
        }

        @Override
        public void addRecipe(Recipe recipe) {
            requireNonNull(recipe);
            recipesAdded.add(recipe);
        }

        @Override
        public Optional<Recipe> findRecipeWithName(String name) {
            requireNonNull(name);
            return this.recipesAdded
                .stream()
                .filter(r -> r.getName().equalsIgnoreCase(name))
                .findFirst();
        }

        @Override
        public void setRecipe(Recipe target, Recipe editedRecipe) {
            var r = this.recipesAdded.indexOf(target);
            if (r == -1) {
                throw new NoSuchElementException("recipe not found");
            }

            this.recipesAdded.set(r, editedRecipe);
        }

        @Override
        public ReadOnlyEntryBook<Recipe> getRecipeBook() {
            return new EntryBook<>();
        }
    }
}
