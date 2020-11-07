package seedu.address.logic.commands.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalExercises.BENCH_PRESS;
import static seedu.address.testutil.TypicalExercises.SQUATS;
import static seedu.address.testutil.TypicalExercises.getTypicalFitNus;
import static seedu.address.testutil.TypicalRoutines.UPPER_BODY;
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
 * {@code RoutineAddExerciseCommand}.
 */
public class RoutineAddExerciseCommandTest {

    public static final String MESSAGE_SUCCESS = "Exercise added to Routine: %1$s";
    private static final String MESSAGE_MISSING_ROUTINE = "This routine does not exist in fitNUS";
    private static final String MESSAGE_MISSING_EXERCISE_FITNUS = "This exercise does not exist in fitNUS";
    private static final Model typicalModel = new ModelManager(getTypicalFitNus(), new UserPrefs());
    private static final Model populatedModel = new ModelManager(getPopulatedFitNus(), new UserPrefs());


    @Test
    public void execute_validExercise_success() {
        Routine chestDay = new Routine(new Name("Chest Day"));
        typicalModel.addRoutine(chestDay);
        RoutineAddExerciseCommand routineAddExerciseCommand = new RoutineAddExerciseCommand(
                chestDay, BENCH_PRESS
        );

        String expectedMessage = String.format(MESSAGE_SUCCESS, chestDay);
        expectedMessage += "\n" + BENCH_PRESS;

        try {
            assertFalse(chestDay.hasExercise(BENCH_PRESS));
            assertEquals(expectedMessage, routineAddExerciseCommand.execute(typicalModel).getFeedbackToUser());
            assertTrue(chestDay.hasExercise(BENCH_PRESS));
        } catch (CommandException e) {
            fail();
        }
    }

    @Test
    public void execute_invalidRoutine_throwsCommandException() {

        RoutineAddExerciseCommand routineAddExerciseCommand = new RoutineAddExerciseCommand(UPPER_BODY,
                SQUATS);
        assertCommandFailure(routineAddExerciseCommand, typicalModel, MESSAGE_MISSING_ROUTINE);
    }

    @Test
    public void execute_invalidExerciseInFitNus_throwsCommandException() {
        Routine upperBody = populatedModel.getFilteredRoutineList().get(1);
        RoutineAddExerciseCommand routineAddExerciseCommand = new RoutineAddExerciseCommand(upperBody,
                SQUATS);

        assertCommandFailure(routineAddExerciseCommand, populatedModel, MESSAGE_MISSING_EXERCISE_FITNUS);
    }

    @Test
    public void equals() {
        Routine legDay = populatedModel.getFilteredRoutineList().get(1);

        Routine upperBody = populatedModel.getFilteredRoutineList().get(1);

        RoutineAddExerciseCommand routineAddExerciseFirstCommand = new RoutineAddExerciseCommand(
                legDay, SQUATS);
        RoutineAddExerciseCommand routineAddExerciseSecondCommand = new RoutineAddExerciseCommand(
                upperBody, BENCH_PRESS);

        // same object -> returns true
        assertTrue(routineAddExerciseFirstCommand.equals(routineAddExerciseFirstCommand));

        // same values -> returns true
        RoutineAddExerciseCommand routineAddExerciseFirstCommandCopy = new RoutineAddExerciseCommand(
                legDay, SQUATS
        );
        assertTrue(routineAddExerciseFirstCommand.equals(routineAddExerciseFirstCommandCopy));

        // different types -> returns false
        assertFalse(routineAddExerciseFirstCommand.equals(1));

        // null -> returns false
        assertFalse(routineAddExerciseFirstCommand.equals(null));

        // different Routine -> returns false
        assertFalse(routineAddExerciseFirstCommand.equals(routineAddExerciseSecondCommand));
    }

}
