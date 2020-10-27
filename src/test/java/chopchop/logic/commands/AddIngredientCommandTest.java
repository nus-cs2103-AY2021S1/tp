package chopchop.logic.commands;

import java.util.Set;
import java.util.Arrays;
import java.util.Optional;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import chopchop.model.EntryBook;
import chopchop.model.ModelStub;
import chopchop.model.ReadOnlyEntryBook;
import chopchop.model.attributes.units.Volume;
import chopchop.model.ingredient.Ingredient;

import org.junit.jupiter.api.Test;
import chopchop.testutil.IngredientBuilder;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddIngredientCommandTest {

    @Test
    public void execute_ingredientAcceptedByModel_addSuccessful() throws Exception {
        var modelStub = new ModelStubAcceptingIngredientAdded();
        var ingr = new IngredientBuilder().build();

        var result = new AddIngredientCommand(ingr.getName(), Optional.of(ingr.getQuantity()),
            ingr.getExpiryDate(), ingr.getTags())
                .execute(modelStub, new CommandTestUtil.HistoryManagerStub());


        assertTrue(result.didSucceed());
        assertEquals(Arrays.asList(ingr), modelStub.ingredientsAdded);
    }

    @Test
    public void add_ingredients_combine() throws Exception {
        var milk1 = new IngredientBuilder().withName("milk").withQuantity(Volume.litres(0.7)).build();
        var milk2 = new IngredientBuilder().withName("MILK").withQuantity(Volume.cups(1)).build();

        var modelStub = new ModelStubAcceptingIngredientAdded();
        var historyStub = new CommandTestUtil.HistoryManagerStub();

        var out = new AddIngredientCommand("milk", Optional.of(Volume.litres(0.7)), Optional.empty(), Set.of())
            .execute(modelStub, historyStub);

        assertTrue(out.didSucceed());

        var out2 = new AddIngredientCommand("MILK", Optional.of(Volume.cups(1)), Optional.empty(), Set.of())
            .execute(modelStub, historyStub);

        assertTrue(out2.didSucceed());
    }

    @Test
    public void equals() {
        var apple = new IngredientBuilder().withName("Apple").build();
        var banana = new IngredientBuilder().withName("Banana").build();
        var appleCmd = new AddIngredientCommand("Apple", Optional.empty(), Optional.empty(), Set.of());
        var bananaCmd = new AddIngredientCommand("Banana", Optional.empty(), Optional.empty(), Set.of());

        // same object -> returns true
        assertTrue(appleCmd.equals(appleCmd));

        // same values -> returns true
        var appleCmdCopy = new AddIngredientCommand("Apple", Optional.empty(), Optional.empty(), Set.of());
        assertTrue(appleCmd.equals(appleCmdCopy));

        // different types -> returns false
        assertFalse(appleCmd.equals(1));

        // null -> returns false
        assertFalse(appleCmd.equals(null));

        // different ingredient -> returns false
        assertFalse(appleCmd.equals(bananaCmd));
    }

    /**
     * A Model stub that contains a single ingredient.
     */
    private class ModelStubWithIngredient extends ModelStub {
        private final Ingredient ingredient;

        ModelStubWithIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            this.ingredient = ingredient;
        }

        @Override
        public boolean hasIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            return this.ingredient.isSame(ingredient);
        }

        @Override
        public Optional<Ingredient> findIngredientWithName(String name) {
            requireNonNull(name);
            return this.ingredient.getName().equalsIgnoreCase(name)
                ? Optional.of(this.ingredient)
                : Optional.empty();
        }
    }

    /**
     * A Model stub that always accepts the ingredient being added.
     */
    private static class ModelStubAcceptingIngredientAdded extends ModelStub {
        final ArrayList<Ingredient> ingredientsAdded = new ArrayList<>();

        @Override
        public boolean hasIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            return ingredientsAdded.stream().anyMatch(ingredient::isSame);
        }

        @Override
        public void addIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            ingredientsAdded.add(ingredient);
        }

        @Override
        public Optional<Ingredient> findIngredientWithName(String name) {
            requireNonNull(name);
            return this.ingredientsAdded
                .stream()
                .filter(i -> i.getName().equalsIgnoreCase(name))
                .findFirst();
        }

        @Override
        public void setIngredient(Ingredient target, Ingredient editedIngredient) {
            var i = this.ingredientsAdded.indexOf(target);
            if (i == -1) {
                throw new NoSuchElementException("ingredient not found");
            }

            this.ingredientsAdded.set(i, editedIngredient);
        }

        @Override
        public ReadOnlyEntryBook<Ingredient> getIngredientBook() {
            return new EntryBook<>();
        }
    }

}
