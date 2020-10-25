/*package seedu.address.logic.commands.ingredientcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.IngredientBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyIngredientBook;
import seedu.address.model.ingredient.Amount;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;


public class IngredientListCommandTest {


    @Test
    public void execute_viewIngredient_success() {
        Model model = new ModelManager();
        Model expectedModel = model;
        Amount amount = new Amount ("10");

        IngredientBook defaultBook = new IngredientBook();
        defaultBook.addIngredient(new Ingredient(new IngredientName("Milk"), amount));
        defaultBook.addIngredient(new Ingredient(new IngredientName("Pearl"), amount));
        defaultBook.addIngredient(new Ingredient(new IngredientName("Boba"), amount));
        defaultBook.addIngredient(new Ingredient(new IngredientName("Oolong Tea"), amount));
        defaultBook.addIngredient(new Ingredient(new IngredientName("Brown Sugar"), amount));
        ReadOnlyIngredientBook readOnlyIngredientBook = defaultBook;

        model.setIngredientBook(readOnlyIngredientBook);

        IngredientBook original = new IngredientBook();
        original.addIngredient(new Ingredient(new IngredientName("Milk"), amount));
        original.addIngredient(new Ingredient(new IngredientName("Pearl"), amount));
        original.addIngredient(new Ingredient(new IngredientName("Boba"), amount));
        original.addIngredient(new Ingredient(new IngredientName("Oolong Tea"), amount));
        original.addIngredient(new Ingredient(new IngredientName("Brown Sugar"), amount));
        ReadOnlyIngredientBook defaultReadOnlyIngredientBook = original;

        expectedModel.setIngredientBook(defaultReadOnlyIngredientBook);

        IngredientViewSingleCommand.ViewIngredientDescriptor descriptor = new
                IngredientViewSingleCommand.ViewIngredientDescriptor();
        final IngredientListCommand standardCommand = new
                IngredientListCommand();
        String ingredientList = "";
        final char lineSeparator = '\n';
        List<Ingredient> lastShownList = expectedModel.getFilteredIngredientList();
        for (Ingredient i : lastShownList) {
            ingredientList += i.toString() + lineSeparator;
        }
        assertCommandSuccess(standardCommand, model,
                IngredientListCommand.MESSAGE_SUCCESS + ingredientList, expectedModel);

    }


    @Test
    public void equals() {
        final IngredientListCommand standardCommand = new IngredientListCommand();

        // same values -> returns true
        IngredientListCommand commandWithSameValues = new IngredientListCommand();

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

    }
}*/

