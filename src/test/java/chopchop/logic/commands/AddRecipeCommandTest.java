package chopchop.logic.commands;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.ModelStub;
import chopchop.model.recipe.Recipe;
import chopchop.model.recipe.RecipeBook;
import chopchop.model.recipe.ReadOnlyRecipeBook;
import chopchop.testutil.RecipeBuilder;
import static chopchop.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Objects.requireNonNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddRecipeCommandTest {

    @Test
    public void constructor_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRecipeCommand(null));
    }

    @Test
    public void execute_recipeAcceptedByModel_addSuccessful() throws Exception {
        AddRecipeCommandTest.ModelStubAcceptingRecipeAdded modelStub = new ModelStubAcceptingRecipeAdded();
        Recipe validRecipe = new RecipeBuilder().build();

        CommandResult commandResult = new AddRecipeCommand(validRecipe).execute(modelStub);

        assertEquals(String.format(AddRecipeCommand.MESSAGE_SUCCESS, validRecipe),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validRecipe), modelStub.recipesAdded);
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        Recipe validRecipe = new RecipeBuilder().build();
        AddCommand addCommand = new AddRecipeCommand(validRecipe);
        ModelStub modelStub = new ModelStubWithRecipe(validRecipe);

        assertThrows(CommandException.class,
            AddRecipeCommand.MESSAGE_DUPLICATE_RECIPE, () -> addCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Recipe apple_salad = new RecipeBuilder().withName("Apple Salad").build();
        Recipe banana_salad = new RecipeBuilder().withName("Banana Salad").build();
        AddCommand addAppleSaladCommand = new AddRecipeCommand(apple_salad);
        AddCommand addBananaSaladCommand = new AddRecipeCommand(banana_salad);

        // same object -> returns true
        assertTrue(addAppleSaladCommand.equals(addAppleSaladCommand));

        // same values -> returns true
        AddCommand addAppleCommandCopy = new AddRecipeCommand(apple_salad);
        assertTrue(addAppleSaladCommand.equals(addAppleCommandCopy));

        // different types -> returns false
        assertFalse(addAppleSaladCommand.equals(1));

        // null -> returns false
        assertFalse(addAppleSaladCommand.equals(null));

        // different recipe -> returns false
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
            return this.recipe.equals(recipe);
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
            return recipesAdded.stream().anyMatch(recipe::equals);
        }

        @Override
        public void addRecipe(Recipe recipe) {
            requireNonNull(recipe);
            recipesAdded.add(recipe);
        }

        @Override
        public ReadOnlyRecipeBook getRecipeBook() {
            return new RecipeBook();
        }
    }

}
