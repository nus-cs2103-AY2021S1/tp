package seedu.address.logic.commands.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExerciseAtIndex;
import static seedu.address.testutil.TypicalExercises.getTypicalFitNus;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.Exercise;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code ExerciseDeleteCommand}.
 */
public class ExerciseDeleteCommandTest {

    private Model model = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredExerciseList_success() {
        Exercise exerciseToDelete = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());
        ExerciseDeleteCommand exerciseDeleteCommand = new ExerciseDeleteCommand(INDEX_FIRST_EXERCISE);

        String expectedMessage = String.format(ExerciseDeleteCommand.MESSAGE_DELETE_EXERCISE_SUCCESS, exerciseToDelete);

        ModelManager expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.deleteExercise(exerciseToDelete);

        assertCommandSuccess(exerciseDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredExerciseList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExerciseList().size() + 1);
        ExerciseDeleteCommand exerciseDeleteCommand = new ExerciseDeleteCommand(outOfBoundIndex);

        assertCommandFailure(exerciseDeleteCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredExerciseList_success() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);

        Exercise exerciseToDelete = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());
        ExerciseDeleteCommand exerciseDeleteCommand = new ExerciseDeleteCommand(INDEX_FIRST_EXERCISE);

        String expectedMessage = String.format(ExerciseDeleteCommand.MESSAGE_DELETE_EXERCISE_SUCCESS, exerciseToDelete);

        Model expectedModel = new ModelManager(model.getFitNus(), new UserPrefs());
        expectedModel.deleteExercise(exerciseToDelete);
        showNoExercise(expectedModel);

        assertCommandSuccess(exerciseDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredExerciseList_throwsCommandException() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);

        Index outOfBoundIndex = INDEX_SECOND_EXERCISE;
        // ensures that outOfBoundIndex is still in bounds of fitNUS list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitNus().getExerciseList().size());

        ExerciseDeleteCommand exerciseDeleteCommand = new ExerciseDeleteCommand(outOfBoundIndex);

        assertCommandFailure(exerciseDeleteCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ExerciseDeleteCommand exerciseDeleteFirstCommand = new ExerciseDeleteCommand(INDEX_FIRST_EXERCISE);
        ExerciseDeleteCommand exerciseDeleteSecondCommand = new ExerciseDeleteCommand(INDEX_SECOND_EXERCISE);

        // same object -> returns true
        assertTrue(exerciseDeleteFirstCommand.equals(exerciseDeleteFirstCommand));

        // same values -> returns true
        ExerciseDeleteCommand exerciseDeleteFirstCommandCopy = new ExerciseDeleteCommand(INDEX_FIRST_EXERCISE);
        assertTrue(exerciseDeleteFirstCommand.equals(exerciseDeleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(exerciseDeleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exerciseDeleteFirstCommand.equals(null));

        // different exercise -> returns false
        assertFalse(exerciseDeleteFirstCommand.equals(exerciseDeleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered exercise list to show no one.
     */
    private void showNoExercise(Model model) {
        model.updateFilteredExerciseList(p -> false);

        assertTrue(model.getFilteredExerciseList().isEmpty());
    }
}
