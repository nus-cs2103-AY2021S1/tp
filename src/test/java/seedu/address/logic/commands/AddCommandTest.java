package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.food.Food;
import seedu.address.model.order.OrderItem;
import seedu.address.testutil.TypicalModel;

public class AddCommandTest {

    @Test
    public void constructor_nullVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_validIndex_success() {
        Model model = TypicalModel.getModelManagerWithMenu();

        Index index = Index.fromOneBased(1);
        AddCommand addCommand = new AddCommand(index);


        ObservableList<Food> menu = model.getFilteredFoodList();
        Food firstItem = menu.get(0);
        OrderItem addedItem = new OrderItem(firstItem, 1);

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        try {
            expectedModel.addOrderItem(addedItem);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_SUCCESS, addedItem);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validQuantity_success() {
        Model model = TypicalModel.getModelManagerWithMenu();

        Index first = Index.fromOneBased(2);
        int quantity = 3;
        AddCommand addCommand = new AddCommand(first, quantity);

        ObservableList<Food> menu = model.getFilteredFoodList();
        Food secondItem = menu.get(1);
        OrderItem addedItem = new OrderItem(secondItem, 3);

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        try {
            expectedModel.addOrderItem(addedItem);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_SUCCESS, addedItem);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = TypicalModel.getModelManagerWithMenu();

        ObservableList<Food> menu = model.getFilteredFoodList();
        Index outOfBoundIndex = Index.fromOneBased(menu.size() + 1);
        AddCommand addCommand = new AddCommand(outOfBoundIndex);

        assertCommandFailure(addCommand, model, ParserUtil.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);

    }

    @Test
    public void equals() {
        AddCommand addCommand1 = new AddCommand(Index.fromOneBased(1));
        AddCommand addCommand2 = new AddCommand(Index.fromOneBased(3));

        // same object -> returns true
        assertTrue(addCommand1.equals(addCommand1));

        // same values -> returns true
        AddCommand addCommandCopy = new AddCommand(Index.fromOneBased(1));
        assertTrue(addCommandCopy.equals(addCommand1));

        // different types -> returns false
        assertFalse(addCommand1.equals(1));

        // null -> returns false
        assertFalse(addCommand1.equals(null));

        // different vendor -> returns false
        assertFalse(addCommand1.equals(addCommand2));
    }

}
