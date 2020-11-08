package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalModel;

public class UntagCommandTest {

    public Model getTestModels() throws CommandException {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.addOrderItem(new OrderItem(model.getFilteredMenuItemList().get(0), 2));
        model.addOrderItem(new OrderItem(model.getFilteredMenuItemList().get(1), 3));
        model.tagOrderItem(model.getOrderManager().getOrderItemList().get(0), new Tag("1 no ice sweet"));
        return model;
    }

    @Test
    public void untagCommandExecute_success() throws CommandException {
        Model model = getTestModels();
        Model expectedModel = getTestModels();
        UntagCommand untagCommand = new UntagCommand(Index.fromZeroBased(0));
        String expectedMessage = String.format(UntagCommand.MESSAGE_UNTAG_SUCCESS, 1);
        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getObservableOrderItemList().get(0).getTags(), new HashSet<>());
    }

    @Test
    public void invalidIndexSelected_throwsCommandException() throws CommandException {
        Model model = getTestModels();
        UntagCommand untagCommand = new UntagCommand(Index.fromZeroBased(3));
        assertCommandFailure(untagCommand, model, Messages.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(-1);
        assertCommandFailure(new UntagCommand(Index.fromZeroBased(0)), model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }
}
