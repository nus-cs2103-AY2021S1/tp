package seedu.address.logic.commands.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalExercises.BENCH_PRESS;
import static seedu.address.testutil.TypicalExercises.SQUATS;
import static seedu.address.testutil.TypicalExercises.getTypicalFitNus;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;
import static seedu.address.testutil.TypicalRoutines.getPopulatedFitNus;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.routine.Routine;
import seedu.address.model.util.Name;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code RoutineDeleteExerciseCommand}.
 */
public class RoutineDeleteExerciseCommandTest {

    private static final String MESSAGE_MISSING_ROUTINE = "This routine does not exist in fitNUS!";
    private static final String MESSAGE_MISSING_EXERCISE = "This exercise does not exist within this routine!";
    private static final String MESSAGE_DELETE_EXERCISE_SUCCESS = "Deleted Exercise from Routine: %1$s";

    private Model typicalModel = new ModelManager(getTypicalFitNus(), new UserPrefs());
    private Model populatedModel = new ModelManager(getPopulatedFitNus(), new UserPrefs());

    @Test
    public void execute_validExercise_success() {
        populatedModel.addExercise(SQUATS);
        Routine legDay = new Routine(new Name("Leg Day"));
        populatedModel.deleteRoutine(LEG_DAY);
        populatedModel.addRoutine(legDay);
        legDay.addExercise(SQUATS);
        RoutineDeleteExerciseCommand routineDeleteExerciseCommand = new RoutineDeleteExerciseCommand(
                LEG_DAY, SQUATS
        );
        String expectedMessage = String.format(
                String.format(MESSAGE_DELETE_EXERCISE_SUCCESS, LEG_DAY), SQUATS);
        try {
            assertEquals(expectedMessage, routineDeleteExerciseCommand.execute(populatedModel).getFeedbackToUser());
            assertFalse(legDay.hasExercise(SQUATS));
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_invalidRoutine_throwsCommandException() {

        Routine upperBody = populatedModel.getFilteredRoutineList().get(1);

        RoutineDeleteExerciseCommand routineDeleteExerciseCommand = new RoutineDeleteExerciseCommand(upperBody,
                SQUATS);
        assertCommandFailure(routineDeleteExerciseCommand, typicalModel, MESSAGE_MISSING_ROUTINE);
    }

    @Test
    public void execute_invalidExerciseInFitNus_throwsCommandException() {
        Routine upperBody = populatedModel.getFilteredRoutineList().get(1);

        RoutineDeleteExerciseCommand routineDeleteExerciseCommand = new RoutineDeleteExerciseCommand(upperBody,
                SQUATS);

        assertCommandFailure(routineDeleteExerciseCommand, populatedModel, MESSAGE_MISSING_EXERCISE);
    }

    @Test
    public void execute_invalidExerciseInRoutine_throwsCommandException() {
        Routine upperBody = populatedModel.getFilteredRoutineList().get(1);
        populatedModel.addExercise(SQUATS);
        RoutineDeleteExerciseCommand routineDeleteExerciseCommand = new RoutineDeleteExerciseCommand(upperBody,
                SQUATS);

        assertCommandFailure(routineDeleteExerciseCommand, populatedModel, MESSAGE_MISSING_EXERCISE);
    }

    @Test
    public void equals() {
        Routine legDay = populatedModel.getFilteredRoutineList().get(1);

        Routine upperBody = populatedModel.getFilteredRoutineList().get(1);

        RoutineDeleteExerciseCommand routineDeleteExerciseFirstCommand = new RoutineDeleteExerciseCommand(
                legDay, SQUATS);
        RoutineDeleteExerciseCommand routineDeleteExerciseSecondCommand = new RoutineDeleteExerciseCommand(
                upperBody, BENCH_PRESS);

        // same object -> returns true
        assertTrue(routineDeleteExerciseFirstCommand.equals(routineDeleteExerciseFirstCommand));

        // same values -> returns true
        RoutineDeleteExerciseCommand routineDeleteExerciseFirstCommandCopy = new RoutineDeleteExerciseCommand(
                legDay, SQUATS
        );
        assertTrue(routineDeleteExerciseFirstCommand.equals(routineDeleteExerciseFirstCommandCopy));

        // different types -> returns false
        assertFalse(routineDeleteExerciseFirstCommand.equals(1));

        // null -> returns false
        assertFalse(routineDeleteExerciseFirstCommand.equals(null));

        // different Routine -> returns false
        assertFalse(routineDeleteExerciseFirstCommand.equals(routineDeleteExerciseSecondCommand));
    }

}
