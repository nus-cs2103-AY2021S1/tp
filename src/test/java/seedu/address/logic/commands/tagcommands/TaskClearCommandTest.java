package seedu.address.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TAG;
import static seedu.address.testutil.TypicalProjact.getTypicalProjactWithTasks;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

public class TaskClearCommandTest {
    private Model model = new ModelManager(getTypicalProjactWithTasks(), new UserPrefs());

    @Test
    public void execute_tagWithCompletedTasks_success() {
        Tag identifiedTag = model.getFilteredTagList().get(INDEX_SECOND_TAG.getZeroBased());
        Tag editedTag = new Tag(identifiedTag.getTagName(), new ArrayList<>(), identifiedTag.getMeetingLink());

        TaskClearCommand taskClearCommand = new TaskClearCommand(INDEX_SECOND_TAG);
        String expectedMessage = String.format(TaskClearCommand.MESSAGE_CLEAR_TASK_SUCCESS, identifiedTag.getTagName());

        ModelManager expectedModel = new ModelManager(model.getProjact(), new UserPrefs());
        expectedModel.setTag(identifiedTag, editedTag);

        assertCommandSuccess(taskClearCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagWithNoCompletedTasks_throwsCommandException() {
        Tag identifiedTag = model.getFilteredTagList().get(INDEX_FIRST_TAG.getZeroBased());

        TaskClearCommand taskClearCommand = new TaskClearCommand(INDEX_FIRST_TAG);
        String expectedMessage = String.format(TaskClearCommand.MESSAGE_NO_TASKS, identifiedTag.getTagName());

        assertCommandFailure(taskClearCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTagList().size() + 1);
        TaskClearCommand taskClearCommand = new TaskClearCommand(outOfBoundIndex);

        assertCommandFailure(taskClearCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TaskClearCommand commandOne = new TaskClearCommand(INDEX_FIRST_TAG);
        TaskClearCommand commandTwo = new TaskClearCommand(INDEX_SECOND_TAG);

        // same object -> returns true
        assertTrue(commandOne.equals(commandOne));

        // same values -> returns true
        TaskClearCommand commandOneCopy = new TaskClearCommand(INDEX_FIRST_TAG);
        assertTrue(commandOne.equals(commandOneCopy));

        // different types -> returns false
        assertFalse(commandOne.equals(1));

        // null -> returns false
        assertFalse(commandOne.equals(null));

        // different tag -> returns false
        assertFalse(commandOne.equals(commandTwo));
    }
}
