package seedu.address.logic.commands.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BENCH;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SQUATS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_NAME_BENCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_NAME_SQUATS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_TAG_ARMS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExerciseAtIndex;
import static seedu.address.testutil.TypicalExercises.getTypicalFitNus;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.exercise.ExerciseEditCommand.EditExerciseDescriptor;
import seedu.address.model.FitNus;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exercise.Exercise;
import seedu.address.testutil.EditExerciseDescriptorBuilder;
import seedu.address.testutil.ExerciseBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for ExerciseEditCommand.
 */
public class ExerciseEditCommandTest {

    private Model model = new ModelManager(getTypicalFitNus(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredExerciseList_success() {
        Exercise editedExercise = new ExerciseBuilder().withName("Sprints").build();
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder(editedExercise).build();
        ExerciseEditCommand exerciseEditCommand = new ExerciseEditCommand(INDEX_FIRST_EXERCISE, descriptor);

        String expectedMessage = String.format(ExerciseEditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new FitNus(model.getFitNus()), new UserPrefs());
        expectedModel.setExercise(model.getFilteredExerciseList().get(0), editedExercise);

        assertCommandSuccess(exerciseEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredExerciseList_success() {
        Index indexLastExercise = Index.fromOneBased(model.getFilteredExerciseList().size());
        Exercise lastExercise = model.getFilteredExerciseList().get(indexLastExercise.getZeroBased());

        ExerciseBuilder exerciseInList = new ExerciseBuilder(lastExercise);
        Exercise editedExercise = exerciseInList.withName(VALID_EXERCISE_NAME_SQUATS)
                .withTags(VALID_EXERCISE_TAG_ARMS).build();

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withName(VALID_EXERCISE_NAME_SQUATS)
                .withTags(VALID_EXERCISE_TAG_ARMS).build();
        ExerciseEditCommand exerciseEditCommand = new ExerciseEditCommand(indexLastExercise, descriptor);
        String expectedMessage = String.format(ExerciseEditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new FitNus(model.getFitNus()), new UserPrefs());
        expectedModel.setExercise(lastExercise, editedExercise);

        assertCommandSuccess(exerciseEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredExerciseList_success() {
        ExerciseEditCommand exerciseEditCommand = new ExerciseEditCommand(INDEX_FIRST_EXERCISE,
                new EditExerciseDescriptor());
        Exercise editedExercise = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());

        String expectedMessage = String.format(ExerciseEditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new FitNus(model.getFitNus()), new UserPrefs());

        assertCommandSuccess(exerciseEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredExerciseList_success() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);

        Exercise exerciseInFilteredList = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());
        Exercise editedExercise = new ExerciseBuilder(exerciseInFilteredList)
                .withName(VALID_EXERCISE_NAME_BENCH).build();
        ExerciseEditCommand exerciseEditCommand = new ExerciseEditCommand(INDEX_FIRST_EXERCISE,
                new EditExerciseDescriptorBuilder().withName(VALID_EXERCISE_NAME_BENCH).build());

        String expectedMessage = String.format(ExerciseEditCommand.MESSAGE_EDIT_EXERCISE_SUCCESS, editedExercise);

        Model expectedModel = new ModelManager(new FitNus(model.getFitNus()), new UserPrefs());
        expectedModel.setExercise(model.getFilteredExerciseList().get(0), editedExercise);

        assertCommandSuccess(exerciseEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateExerciseUnfilteredList_failure() {
        Exercise firstExercise = model.getFilteredExerciseList().get(INDEX_FIRST_EXERCISE.getZeroBased());
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder(firstExercise).build();
        ExerciseEditCommand exerciseEditCommand = new ExerciseEditCommand(INDEX_SECOND_EXERCISE, descriptor);

        assertCommandFailure(exerciseEditCommand, model, ExerciseEditCommand.MESSAGE_DUPLICATE_EXERCISE);
    }

    @Test
    public void execute_duplicateExerciseFilteredList_failure() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);

        // edit exercise in filtered list into a duplicate in fitNUS
        Exercise exerciseInList = model.getFitNus().getExerciseList().get(INDEX_SECOND_EXERCISE.getZeroBased());
        ExerciseEditCommand exerciseEditCommand = new ExerciseEditCommand(INDEX_FIRST_EXERCISE,
                new EditExerciseDescriptorBuilder(exerciseInList).build());

        assertCommandFailure(exerciseEditCommand, model, ExerciseEditCommand.MESSAGE_DUPLICATE_EXERCISE);
    }

    @Test
    public void execute_invalidExerciseIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExerciseList().size() + 1);
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder()
                .withName(VALID_EXERCISE_NAME_BENCH).build();
        ExerciseEditCommand exerciseEditCommand = new ExerciseEditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(exerciseEditCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered exercise list where index is larger than size of filtered list,
     * but smaller than size of fitNUS
     */
    @Test
    public void execute_invalidExerciseIndexFilteredList_failure() {
        showExerciseAtIndex(model, INDEX_FIRST_EXERCISE);
        Index outOfBoundIndex = INDEX_SECOND_EXERCISE;
        // ensures that outOfBoundIndex is still in bounds of fitNUS list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFitNus().getExerciseList().size());

        ExerciseEditCommand exerciseEditCommand = new ExerciseEditCommand(outOfBoundIndex,
                new EditExerciseDescriptorBuilder().withName(VALID_EXERCISE_NAME_BENCH).build());

        assertCommandFailure(exerciseEditCommand, model, Messages.MESSAGE_INVALID_EXERCISE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final ExerciseEditCommand standardCommand = new ExerciseEditCommand(INDEX_FIRST_EXERCISE, DESC_BENCH);

        // same values -> returns true
        EditExerciseDescriptor copyDescriptor = new EditExerciseDescriptor(DESC_BENCH);
        ExerciseEditCommand commandWithSameValues = new ExerciseEditCommand(INDEX_FIRST_EXERCISE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ExerciseEditCommand(INDEX_SECOND_EXERCISE, DESC_SQUATS)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new ExerciseEditCommand(INDEX_FIRST_EXERCISE, DESC_SQUATS)));
    }

}
