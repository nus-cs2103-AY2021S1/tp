package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.OrderItem;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalModel;

public class TagCommandTest {
    public Model getTestModels() throws CommandException {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.addOrderItem(new OrderItem(model.getFilteredMenuItemList().get(0), 2));
        model.addOrderItem(new OrderItem(model.getFilteredMenuItemList().get(1), 3));
        return model;
    }

    @Test
    public void tagCommandExecute_success() throws CommandException {
        Model model = getTestModels();
        Model expectedModel = getTestModels();
        Tag tag = new Tag("1 no ice");
        TagCommand tagCommand = new TagCommand(Index.fromZeroBased(0), tag);
        String commandResult = String.format(TagCommand.MESSAGE_TAG_SUCCESS, tag.tagName, 1);
        Set<Tag> expectedSet = new HashSet<>();
        expectedSet.add(tag);
        assertCommandSuccess(tagCommand, model, commandResult, expectedModel);
        assertEquals(model.getObservableOrderItemList().get(0).getTags(), expectedSet);

        String errorResult = String.format(Messages.MESSAGE_EXISTING_TAG, tag.tagName);
        assertCommandFailure(tagCommand, model, errorResult);
        assertEquals(model.getObservableOrderItemList().get(0).getTags(), expectedSet);
    }

    @Test
    public void invalidIndexSelected_throwsCommandException() throws CommandException {
        Model model = getTestModels();
        Index outOfBoundsIndex = Index.fromOneBased(3);
        TagCommand tagCommand = new TagCommand(outOfBoundsIndex, new Tag("1 teh peng"));
        assertCommandFailure(tagCommand, model, Messages.MESSAGE_INVALID_ORDERITEM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        model.selectVendor(-1);
        Tag tag = new Tag("1 no ice");
        assertCommandFailure(new TagCommand(Index.fromZeroBased(0), tag), model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }

}
