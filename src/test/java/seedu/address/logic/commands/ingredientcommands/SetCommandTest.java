package seedu.address.logic.commands.ingredientcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SalesBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;

class SetCommandTest {

    private static Ingredient target = new Ingredient(new IngredientName("Milk"));
    private static final IngredientBook stubBook = new IngredientBook();
    private static final IngredientBook stubBook2 = new IngredientBook();

    private Model model = new ModelManager(new AddressBook(), new SalesBook(),
            new IngredientBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {

        stubBook.addIngredient(new Ingredient(new IngredientName("Milk")));
        stubBook.addIngredient(new Ingredient(new IngredientName("Pearl")));
        stubBook.addIngredient(new Ingredient(new IngredientName("Boba")));
        stubBook.addIngredient(new Ingredient(new IngredientName("Black Tea")));
        stubBook.addIngredient(new Ingredient(new IngredientName("Green Tea")));
        stubBook.addIngredient(new Ingredient(new IngredientName("Brown Sugar")));

        model.setIngredientBook(stubBook);

        Ingredient editedIngredient = new Ingredient(new IngredientName("Milk"), new Amount("20"));
        SetCommand.SetIngredientDescriptor descriptor = new SetCommand.SetIngredientDescriptor();
        descriptor.setAmount(new Amount("20"));
        SetCommand setCommand = new SetCommand(target.getIngredientName(), descriptor);

        String expectedMessage = String.format(SetCommand.MESSAGE_SUCCESS, editedIngredient);

        Model expectedModel =
                new ModelManager(new AddressBook(model.getAddressBook()), model.getSalesBook(),
                        stubBook, new UserPrefs());
        expectedModel.setIngredient(target, editedIngredient);

        assertCommandSuccess(setCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_amountsNotChanged_failure() {
        stubBook2.addIngredient(new Ingredient(new IngredientName("Milk")));
        stubBook2.addIngredient(new Ingredient(new IngredientName("Pearl")));
        stubBook2.addIngredient(new Ingredient(new IngredientName("Boba")));
        stubBook2.addIngredient(new Ingredient(new IngredientName("Black Tea")));
        stubBook2.addIngredient(new Ingredient(new IngredientName("Green Tea")));
        stubBook2.addIngredient(new Ingredient(new IngredientName("Brown Sugar")));

        model.setIngredientBook(stubBook2);

        SetCommand.SetIngredientDescriptor descriptor = new SetCommand.SetIngredientDescriptor();
        descriptor.setAmount(new Amount("0"));
        SetCommand setCommand = new SetCommand(new IngredientName("Pearl"), descriptor);

        String expectedMessage = SetCommand.MESSAGE_NO_CHANGE;

        assertCommandFailure(setCommand, model, expectedMessage);
    }


    @Test
    void testEquals() {
        final SetCommand.SetIngredientDescriptor descriptor = new SetCommand.SetIngredientDescriptor();
        descriptor.setAmount(new Amount("20"));
        final SetCommand standardCommand = new SetCommand(new IngredientName("Milk"), descriptor);

        // same values -> returns true
        SetCommand.SetIngredientDescriptor copyDescriptor = new SetCommand.SetIngredientDescriptor();
        copyDescriptor.setAmount(new Amount("20"));
        SetCommand commandWithSameValues = new SetCommand(new IngredientName("Milk"), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different ingredient name -> returns false
        assertFalse(standardCommand.equals(new SetCommand(new IngredientName("Boba"), copyDescriptor)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new SetCommand(new IngredientName("Milk"),
                new SetCommand.SetIngredientDescriptor())));
    }
}
