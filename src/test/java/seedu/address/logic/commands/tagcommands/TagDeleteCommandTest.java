package seedu.address.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTagAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TAG;
import static seedu.address.testutil.TypicalProjact.getTypicalProjact;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TagDeleteCommand}.
 */
public class TagDeleteCommandTest {

    private Model model = new ModelManager(getTypicalProjact(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tag tagToDelete = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());
        TagDeleteCommand tagDeleteCommand = new TagDeleteCommand(INDEX_FIRST_TAG);

        String expectedMessage = String.format(TagDeleteCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete);

        ModelManager expectedModel = new ModelManager(model.getProjact(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);

        assertCommandSuccess(tagDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTagList().size() + 1);
        TagDeleteCommand tagDeleteCommand = new TagDeleteCommand(outOfBoundIndex);

        assertCommandFailure(tagDeleteCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTagAtIndex(model, INDEX_FIRST_TAG);

        Tag tagToDelete = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());
        TagDeleteCommand tagDeleteCommand = new TagDeleteCommand(INDEX_FIRST_TAG);

        String expectedMessage = String.format(TagDeleteCommand.MESSAGE_DELETE_TAG_SUCCESS, tagToDelete);

        Model expectedModel = new ModelManager(model.getProjact(), new UserPrefs());
        expectedModel.deleteTag(tagToDelete);

        assertCommandSuccess(tagDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTagAtIndex(model, INDEX_FIRST_TAG);

        Index outOfBoundIndex = INDEX_SECOND_TAG;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjact().getTagList().size());

        TagDeleteCommand tagDeleteCommand = new TagDeleteCommand(outOfBoundIndex);

        assertCommandFailure(tagDeleteCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TagDeleteCommand deleteFirstCommand = new TagDeleteCommand(INDEX_FIRST_TAG);
        TagDeleteCommand deleteSecondCommand = new TagDeleteCommand(INDEX_SECOND_TAG);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        TagDeleteCommand deleteFirstCommandCopy = new TagDeleteCommand(INDEX_FIRST_TAG);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different tag -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
