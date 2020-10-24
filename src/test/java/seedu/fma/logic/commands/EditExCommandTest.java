package seedu.fma.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.fma.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fma.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_CALORIES_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_CALORIES_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_NAME_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_NAME_B;
import static seedu.fma.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.fma.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;
import static seedu.fma.testutil.TypicalLogs.getTypicalLogBook;

import org.junit.jupiter.api.Test;

import seedu.fma.commons.core.Messages;
import seedu.fma.commons.core.index.Index;
import seedu.fma.model.LogBook;
import seedu.fma.model.Model;
import seedu.fma.model.ModelManager;
import seedu.fma.model.UserPrefs;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.testutil.EditExDescriptorBuilder;
import seedu.fma.testutil.ExerciseBuilder;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */

public class EditExCommandTest {

    private Model model = new ModelManager(getTypicalLogBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Index indexLastLog = Index.fromOneBased(model.getFilteredLogList().size());
        Exercise lastExercise = model.getFilteredExerciseList().get(indexLastLog.getZeroBased());

        ExerciseBuilder exerciseInList = new ExerciseBuilder(lastExercise);
        Exercise editedExercise = exerciseInList.withName(VALID_EXERCISE_NAME_B)
                .withCaloriesPerRep(VALID_CALORIES_B).build();

        EditExCommand.EditExDescriptor descriptor = new EditExDescriptorBuilder()
                .withExerciseName(VALID_EXERCISE_NAME_B)
                .withCaloriesPerRep(VALID_CALORIES_B).build();
        EditExCommand editExCommand = new EditExCommand(indexLastLog, descriptor);

        String expectedMessage = String.format(EditExCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new LogBook(model.getLogBook()), new UserPrefs());
        expectedModel.setExercise(lastExercise, editedExercise);

        assertCommandSuccess(editExCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastLog = Index.fromOneBased(model.getFilteredLogList().size());
        Exercise lastExercise = model.getFilteredExerciseList().get(indexLastLog.getZeroBased());

        ExerciseBuilder exerciseInList = new ExerciseBuilder(lastExercise);
        Exercise editedExercise = exerciseInList.withCaloriesPerRep(VALID_CALORIES_A).build();

        EditExCommand.EditExDescriptor descriptor = new EditExDescriptorBuilder()
                .withCaloriesPerRep(VALID_CALORIES_A).build();
        EditExCommand editExCommand = new EditExCommand(indexLastLog, descriptor);

        String expectedMessage = String.format(EditExCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new LogBook(model.getLogBook()), new UserPrefs());
        expectedModel.setExercise(lastExercise, editedExercise);

        assertCommandSuccess(editExCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditExCommand editExCommand = new EditExCommand(INDEX_FIRST_EXERCISE, new EditExCommand.EditExDescriptor());
        Exercise editedExercise = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());

        String expectedMessage = String.format(EditExCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new LogBook(model.getLogBook()), new UserPrefs());

        assertCommandSuccess(editExCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidExerciseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExerciseList().size() + 1);
        EditExCommand.EditExDescriptor descriptor = new EditExDescriptorBuilder()
                .withExerciseName(VALID_EXERCISE_NAME_A).build();
        EditExCommand editExCommand = new EditExCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editExCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        final EditExCommand standardCommand = new EditExCommand(INDEX_FIRST_EXERCISE, EDIT_EX_DESCRIPTOR_A);

        // same values -> returns true
        EditExCommand.EditExDescriptor copyDescriptor = EDIT_EX_DESCRIPTOR_A;
        EditExCommand commandWithSameValues = new EditExCommand(INDEX_FIRST_EXERCISE, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditExCommand(INDEX_SECOND_EXERCISE, EDIT_EX_DESCRIPTOR_A));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditExCommand(INDEX_FIRST_EXERCISE, EDIT_EX_DESCRIPTOR_B));
    }

}


