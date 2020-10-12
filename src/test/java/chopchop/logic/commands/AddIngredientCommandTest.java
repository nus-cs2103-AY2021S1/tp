package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static chopchop.testutil.Assert.assertThrows;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.ModelStub;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.ingredient.IngredientBook;
import chopchop.model.ingredient.ReadOnlyIngredientBook;
import chopchop.testutil.IngredientBuilder;

public class AddIngredientCommandTest {

    @Test
    public void constructor_nullIngredient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddIngredientCommand(null));
    }

    @Test
    public void execute_ingredientAcceptedByModel_addSuccessful() throws Exception {
        AddIngredientCommandTest.ModelStubAcceptingIngredientAdded modelStub = new ModelStubAcceptingIngredientAdded();
        Ingredient validIngredient = new IngredientBuilder().build();

        CommandResult commandResult = new AddIngredientCommand(validIngredient).execute(modelStub);

        assertEquals(String.format(AddIngredientCommand.MESSAGE_SUCCESS, validIngredient),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validIngredient), modelStub.ingredientsAdded);
    }

    @Test
    public void execute_duplicateIngredient_throwsCommandException() {
        Ingredient validIngredient = new IngredientBuilder().build();
        chopchop.logic.commands.AddCommand addCommand = new AddIngredientCommand(validIngredient);
        ModelStub modelStub = new ModelStubWithIngredient(validIngredient);

        assertThrows(CommandException.class,
            AddIngredientCommand.MESSAGE_DUPLICATE_INGREDIENT, () -> addCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Ingredient apple = new IngredientBuilder().withName("Apple").build();
        Ingredient banana = new IngredientBuilder().withName("Banana").build();
        AddCommand addAppleCommand = new AddIngredientCommand(apple);
        AddCommand addBananaCommand = new AddIngredientCommand(banana);

        // same object -> returns true
        assertTrue(addAppleCommand.equals(addAppleCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddIngredientCommand(apple);
        assertTrue(addAppleCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAppleCommand.equals(1));

        // null -> returns false
        assertFalse(addAppleCommand.equals(null));

        // different ingredient -> returns false
        assertFalse(addAppleCommand.equals(addBananaCommand));
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
            return this.ingredient.equals(ingredient);
        }
    }

    /**
     * A Model stub that always accept the ingredient being added.
     */
    private class ModelStubAcceptingIngredientAdded extends ModelStub {
        final ArrayList<Ingredient> ingredientsAdded = new ArrayList<>();

        @Override
        public boolean hasIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            return ingredientsAdded.stream().anyMatch(ingredient::equals);
        }

        @Override
        public void addIngredient(Ingredient ingredient) {
            requireNonNull(ingredient);
            ingredientsAdded.add(ingredient);
        }

        @Override
        public ReadOnlyIngredientBook getIngredientBook() {
            return new IngredientBook();
        }
    }

}
