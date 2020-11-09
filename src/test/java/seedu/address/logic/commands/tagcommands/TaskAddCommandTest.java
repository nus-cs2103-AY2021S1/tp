package seedu.address.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_HOMEWORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showTagAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TAG;
import static seedu.address.testutil.TypicalProjact.getTypicalProjact;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tagcommands.TaskAddCommand.EditTagDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.TagTask;

public class TaskAddCommandTest {
    private Model model = new ModelManager(getTypicalProjact(), new UserPrefs());

    @Test
    public void execute_invalidTagIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTagList().size() + 1);
        EditTagDescriptor descriptor = new EditTagDescriptorStub(VALID_TASK_NAME_HOMEWORK);
        TaskAddCommand taskAddCommand = new TaskAddCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(taskAddCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidTagIndexFilteredList_failure() {
        showTagAtIndex(model, INDEX_FIRST_TAG);
        Index outOfBoundIndex = INDEX_SECOND_TAG;
        // ensures that outOfBoundIndex is still in bounds of Projact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjact().getTagList().size());

        EditTagDescriptor descriptor = new EditTagDescriptorStub(VALID_TASK_NAME_HOMEWORK);
        TaskAddCommand taskAddCommand = new TaskAddCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(taskAddCommand, model, Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TaskAddCommand commandOne = new TaskAddCommand(Index.fromZeroBased(1),
                new EditTagDescriptorStub(VALID_TASK_NAME_HOMEWORK));
        TaskAddCommand commandTwo = new TaskAddCommand(Index.fromZeroBased(0),
                new EditTagDescriptorStub(VALID_TASK_NAME_MEETING));

        // same object -> returns true
        assertTrue(commandOne.equals(commandOne));

        // different types -> returns false
        assertFalse(commandOne.equals(1));

        // null -> returns false
        assertFalse(commandOne.equals(null));

        // different index -> returns false
        assertFalse(commandOne.equals(commandTwo));
    }

    private class EditTagDescriptorStub extends EditTagDescriptor {
        EditTagDescriptorStub(String validTaskName) {
            setNewTagTask(new TagTask(validTaskName, false));
        }
    }
}
