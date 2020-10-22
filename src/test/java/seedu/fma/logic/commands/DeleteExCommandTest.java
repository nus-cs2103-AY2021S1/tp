package seedu.fma.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.fma.commons.core.Messages;
import seedu.fma.commons.core.index.Index;
import seedu.fma.model.Model;
import seedu.fma.model.ModelManager;
import seedu.fma.model.UserPrefs;
import seedu.fma.model.exercise.Exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.fma.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fma.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fma.testutil.TypicalIndexes.INDEX_FIRST_LOG;
import static seedu.fma.testutil.TypicalIndexes.INDEX_SECOND_LOG;
import static seedu.fma.testutil.TypicalLogs.getTypicalLogBook;

public class DeleteExCommandTest {
    private Model model = new ModelManager(getTypicalLogBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Exercise exerciseToDelete = model.getFilteredExerciseList().get(INDEX_FIRST_LOG.getZeroBased());
        DeleteExCommand deleteCommand = new DeleteExCommand(INDEX_FIRST_LOG);

        String expectedMessage = String.format(DeleteExCommand.MESSAGE_DELETE_EXERCISE_SUCCESS, exerciseToDelete);

        ModelManager expectedModel = new ModelManager(model.getLogBook(), new UserPrefs());
        expectedModel.deleteExercise(exerciseToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExerciseList().size() + 1);
        DeleteExCommand deleteExCommand = new DeleteExCommand(outOfBoundIndex);

        assertCommandFailure(deleteExCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteExCommand deleteExFirstCommand = new DeleteExCommand(INDEX_FIRST_LOG);
        DeleteExCommand deleteExSecondCommand = new DeleteExCommand(INDEX_SECOND_LOG);

        // same object -> returns true
        assertEquals(deleteExFirstCommand, deleteExFirstCommand);

        // same values -> returns true
        DeleteExCommand deleteExFirstCommandCopy = new DeleteExCommand(INDEX_FIRST_LOG);
        assertEquals(deleteExFirstCommand, deleteExFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteExFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteExFirstCommand);

        // different log -> returns false
        assertNotEquals(deleteExFirstCommand, deleteExSecondCommand);
    }
}
