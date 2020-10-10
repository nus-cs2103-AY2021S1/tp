package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTagAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TAG;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code UntagCommand}.
 */
public class UntagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tag tagToDelete = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());
        UntagCommand untagCommand = new UntagCommand(INDEX_FIRST_TAG);

        String expectedMessage = String.format(UntagCommand.MESSAGE_UNTAG_TAG_SUCCESS, tagToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTagList().size() + 1);
        UntagCommand untagCommand = new UntagCommand(outOfBoundIndex);

        assertCommandFailure(untagCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTagAtIndex(model, INDEX_FIRST_TAG);

        Tag tagToDelete = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());
        UntagCommand untagCommand = new UntagCommand(INDEX_FIRST_TAG);

        String expectedMessage = String.format(untagCommand.MESSAGE_UNTAG_TAG_SUCCESS, tagToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);
        showNoTag(expectedModel);

        assertCommandSuccess(untagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTagAtIndex(model, INDEX_FIRST_TAG);

        Index outOfBoundIndex = INDEX_SECOND_TAG;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTagList().size());

        UntagCommand untagCommand = new UntagCommand(outOfBoundIndex);

        assertCommandFailure(untagCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UntagCommand deleteFirstCommand = new UntagCommand(INDEX_FIRST_TAG);
        UntagCommand deleteSecondCommand = new UntagCommand(INDEX_SECOND_TAG);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        UntagCommand deleteFirstCommandCopy = new UntagCommand(INDEX_FIRST_TAG);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTag(Model model) {
        model.updateFilteredTagList(p -> false);

        assertTrue(model.getFilteredTagList().isEmpty());
    }
}
