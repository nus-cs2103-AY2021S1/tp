package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.OrderManager;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalModel;
import seedu.address.testutil.TypicalOrderItems;
import seedu.address.testutil.TypicalVendors;

public class UndoCommandTest {
    private static final Model expectedModel = TypicalModel.getModelManagerWithMenu();
    private static final Model model = TypicalModel.getModelManagerWithMenu();

    private static final Model emptyModel = new ModelManager(TypicalVendors.getTypicalVendorManager(), new UserPrefs(),
            TypicalVendors.getManagers(), new OrderManager());
    private static final Model expectedEmptyModel = new ModelManager(TypicalVendors.getTypicalVendorManager(),
            new UserPrefs(), TypicalVendors.getManagers(), new OrderManager());

    @Test
    public void undoCommandExecute_success() throws CommandException {
        // undo an add command
        model.addOrderItem(TypicalOrderItems.MILO);
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getObservableOrderItemList(), expectedModel.getObservableOrderItemList());

        // undo a remove command
        model.addOrderItem(TypicalOrderItems.CHEESE_PRATA);
        expectedModel.addOrderItem(TypicalOrderItems.CHEESE_PRATA);
        model.removeOrderItem(TypicalOrderItems.CHEESE_PRATA);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getObservableOrderItemList(), expectedModel.getObservableOrderItemList());

        Tag tag = new Tag("One extra cheese");

        // undo a tag command
        model.tagOrderItem(TypicalOrderItems.CHEESE_PRATA, tag);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getObservableOrderItemList(), expectedModel.getObservableOrderItemList());

        // undo a untag command
        model.tagOrderItem(TypicalOrderItems.CHEESE_PRATA, tag);
        expectedModel.tagOrderItem(TypicalOrderItems.CHEESE_PRATA, tag);
        model.untagOrderItem(TypicalOrderItems.CHEESE_PRATA);
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getObservableOrderItemList(), expectedModel.getObservableOrderItemList());

        // undo a clear command
        model.addOrderItem(TypicalOrderItems.NASI_GORENG);
        model.addOrderItem(TypicalOrderItems.MILO);
        expectedModel.addOrderItem(TypicalOrderItems.NASI_GORENG);
        expectedModel.addOrderItem(TypicalOrderItems.MILO);
        model.clearOrder();
        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getObservableOrderItemList(), expectedModel.getObservableOrderItemList());
    }

    @Test
    public void emptyUndoHistory_returnsEmptyUndoMessage() throws CommandException {
        UndoCommand undoCommand = new UndoCommand();
        emptyModel.selectVendor(0);
        expectedEmptyModel.selectVendor(0);
        String expectedMessage = UndoCommand.MESSAGE_UNDO_EMPTY;
        // no actions have been made to the order yet
        assertCommandSuccess(undoCommand, emptyModel, expectedMessage, expectedEmptyModel);

        // item has been added and action has been undone
        emptyModel.addOrderItem(TypicalOrderItems.NASI_GORENG);
        emptyModel.undoOrder();
        assertCommandSuccess(undoCommand, emptyModel, expectedMessage, expectedEmptyModel);

        // item has been added and removed and both actions have been undone
        emptyModel.addOrderItem(TypicalOrderItems.NASI_GORENG);
        emptyModel.removeOrderItem(TypicalOrderItems.NASI_GORENG);
        emptyModel.undoOrder();
        emptyModel.undoOrder();
        assertCommandSuccess(undoCommand, emptyModel, expectedMessage, expectedEmptyModel);
    }

    @Test
    public void vendorNotSelected_throwsCommandException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(-1);
        UndoCommand undoCommand = new UndoCommand();
        assertCommandFailure(undoCommand, model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }
}
