package seedu.momentum.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.momentum.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.momentum.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.momentum.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.momentum.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.momentum.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.momentum.testutil.TypicalProjects.getTypicalProjectBook;

import org.junit.jupiter.api.Test;

import seedu.momentum.commons.core.Clock;
import seedu.momentum.commons.core.Messages;
import seedu.momentum.commons.core.index.Index;
import seedu.momentum.model.Model;
import seedu.momentum.model.ModelManager;
import seedu.momentum.model.UserPrefs;
import seedu.momentum.model.project.TrackedItem;
import seedu.momentum.testutil.TypicalTimes;

/**
 * Contains unit tests for {@code StartCommand}.
 */
public class StartProjectCommandTest {

    private Model model = new ModelManager(getTypicalProjectBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Clock.initFixed(TypicalTimes.DAY);
        StartCommand startCommand = new StartProjectCommand(INDEX_FIRST);

        TrackedItem trackedItemToStart = model.getDisplayList().get(INDEX_FIRST.getZeroBased());

        ModelManager expectedModel = new ModelManager(model.getProjectBook(), new UserPrefs());
        TrackedItem startedTrackedItem = trackedItemToStart.startTimer();
        expectedModel.setTrackedItem(trackedItemToStart, startedTrackedItem);
        expectedModel.commitToHistory();

        String expectedMessage =
                String.format(StartCommand.MESSAGE_START_TIMER_SUCCESS, INDEX_FIRST.getOneBased())
                + startedTrackedItem.getTimer().getStartTime().getFormatted();

        assertCommandSuccess(startCommand, model, expectedMessage, expectedModel);
        Clock.reset();
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getDisplayList().size() + 1);
        StartCommand startCommand = new StartProjectCommand(outOfBoundIndex);

        assertCommandFailure(startCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyRunning_throwsCommandException() {
        StartCommand startCommand = new StartProjectCommand(INDEX_FIRST);
        TrackedItem trackedItemToStart = model.getDisplayList().get(INDEX_FIRST.getZeroBased());

        model.setTrackedItem(trackedItemToStart, trackedItemToStart.startTimer());

        assertCommandFailure(startCommand, model, StartCommand.MESSAGE_EXISTING_TIMER_ERROR);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Clock.initFixed(TypicalTimes.DAY);
        showProjectAtIndex(model, INDEX_FIRST);

        TrackedItem trackedItemToStart = model.getDisplayList().get(INDEX_FIRST.getZeroBased());

        ModelManager expectedModel = new ModelManager(model.getProjectBook(), new UserPrefs());
        TrackedItem startedTrackedItem = trackedItemToStart.startTimer();
        expectedModel.setTrackedItem(trackedItemToStart, startedTrackedItem);
        showProjectAtIndex(expectedModel, INDEX_FIRST);
        expectedModel.commitToHistory();

        StartCommand startCommand = new StartProjectCommand(INDEX_FIRST);
        String expectedMessage =
                String.format(StartCommand.MESSAGE_START_TIMER_SUCCESS, INDEX_FIRST.getOneBased())
                        + startedTrackedItem.getTimer().getStartTime().getFormatted();


        assertCommandSuccess(startCommand, model, expectedMessage, expectedModel);
        Clock.reset();
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProjectAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of project book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getProjectBook().getTrackedItemList().size());

        StartCommand startCommand = new StartProjectCommand(outOfBoundIndex);

        assertCommandFailure(startCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        StartCommand startFirstCommand = new StartProjectCommand(INDEX_FIRST);
        StartCommand startSecondCommand = new StartProjectCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(startFirstCommand.equals(startFirstCommand));

        // same values -> returns true
        StartCommand startFirstCommandCopy = new StartProjectCommand(INDEX_FIRST);
        assertTrue(startFirstCommand.equals(startFirstCommandCopy));

        // different types -> returns false
        assertFalse(startFirstCommand.equals(1));

        // null -> returns false
        assertFalse(startFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(startFirstCommand.equals(startSecondCommand));
    }
}
