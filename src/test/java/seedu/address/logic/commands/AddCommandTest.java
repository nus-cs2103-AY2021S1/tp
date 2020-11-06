package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalVendors.getTypicalVendorManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.food.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.testutil.TypicalModel;
import seedu.address.testutil.TypicalVendors;

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


        ObservableList<MenuItem> menu = model.getFilteredMenuItemList();
        MenuItem firstItem = menu.get(0);
        OrderItem addedItem = new OrderItem(firstItem, 1);

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        try {
            expectedModel.addOrderItem(addedItem);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_SUCCESS, addedItem.getName(), 1);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validQuantity_success() {
        Model model = TypicalModel.getModelManagerWithMenu();

        Index first = Index.fromOneBased(2);
        int quantity = 3;
        AddCommand addCommand = new AddCommand(first, quantity);

        ObservableList<MenuItem> menu = model.getFilteredMenuItemList();
        MenuItem secondItem = menu.get(1);
        OrderItem addedItem = new OrderItem(secondItem, 3);

        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        try {
            expectedModel.addOrderItem(addedItem);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
        String expectedMessage = String.format(AddCommand.MESSAGE_ADD_SUCCESS, addedItem.getName(), quantity);

        assertCommandSuccess(addCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = TypicalModel.getModelManagerWithMenu();

        ObservableList<MenuItem> menu = model.getFilteredMenuItemList();
        Index outOfBoundIndex = Index.fromOneBased(menu.size() + 1);
        AddCommand addCommand = new AddCommand(outOfBoundIndex);

        assertCommandFailure(addCommand, model, Messages.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);

    }

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = new ModelManager(getTypicalVendorManager(), new UserPrefs(), TypicalVendors.getManagers(),
                new OrderManager());
        assertCommandFailure(new AddCommand(Index.fromOneBased(1)),
                model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
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
