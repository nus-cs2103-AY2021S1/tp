package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalOrderItems.NUGGETS;
import static seedu.address.testutil.TypicalVendors.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.testutil.OrderItemBuilder;
import seedu.address.testutil.TypicalVendors;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code RemoveCommand}.
 */
public class RemoveCommandTest {


    private Model initialiseModel() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), TypicalVendors.getManagers(),
                new OrderManager());
        model.selectVendor(0);
        try {
            model.addOrderItem(NUGGETS);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }
        return model;
    }

    @Test
    public void execute_validIndex_success() {
        Model model = initialiseModel();
        Index first = Index.fromOneBased(1);
        RemoveCommand removeCommand = new RemoveCommand(first);
        List<OrderItem> lastShownList = model.getObservableOrderItemList();
        OrderItem orderItemToRemove = lastShownList.get(first.getZeroBased());
        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVE_ORDERITEM_SUCCESS, orderItemToRemove);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), TypicalVendors.getManagers(),
                new OrderManager());
        expectedModel.selectVendor(0);

        assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validQuantity_success() {
        Model model = initialiseModel();
        Index first = Index.fromOneBased(1);
        RemoveCommand removeCommand = new RemoveCommand(first, 1);
        OrderItem itemRemoved = new OrderItemBuilder(NUGGETS).withQuantity(1).build();
        OrderItem remainingItems = new OrderItemBuilder(NUGGETS).withQuantity(4).build();

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), TypicalVendors.getManagers(),
                new OrderManager());
        expectedModel.selectVendor(0);
        try {
            expectedModel.addOrderItem(remainingItems);
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }

        String expectedMessage = String.format(RemoveCommand.MESSAGE_REMOVE_ORDERITEM_SUCCESS, itemRemoved);

        assertCommandSuccess(removeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = initialiseModel();
        Index outOfBoundIndex = Index.fromOneBased(model.getObservableVendorList().size() + 1);
        RemoveCommand removeCommand = new RemoveCommand(outOfBoundIndex);

        assertCommandFailure(removeCommand, model, ParserUtil.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RemoveCommand removeFirstCommand = new RemoveCommand(Index.fromOneBased(1));
        RemoveCommand removeSecondCommand = new RemoveCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(removeFirstCommand.equals(removeFirstCommand));

        // same values -> returns true
        RemoveCommand removeFirstCommandCopy = new RemoveCommand(Index.fromOneBased(1));
        assertTrue(removeFirstCommand.equals(removeFirstCommandCopy));

        // different types -> returns false
        assertFalse(removeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(removeFirstCommand.equals(null));

        // different vendor -> returns false
        assertFalse(removeFirstCommand.equals(removeSecondCommand));
    }

}
