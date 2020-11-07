package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalExercises.getTypicalExercises;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.routine.Routine;
import seedu.address.model.util.Name;

public class JsonAdaptedRoutineTest {

    private static final String INVALID_NAME = "Leg@Day";
    private static final List<JsonAdaptedExercise> VALID_EXERCISES = getTypicalExercises().stream()
            .map(JsonAdaptedExercise::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validRoutineDetails_returnsRoutine() {
        JsonAdaptedRoutine routine = new JsonAdaptedRoutine(LEG_DAY);

        //Test valid Exercises
        Routine testRoutine = new Routine(new Name("Amazing Workout"));
        for (Exercise exercise : getTypicalExercises()) {
            testRoutine.addExercise(exercise);
        }
        JsonAdaptedRoutine routineWithExercises = new JsonAdaptedRoutine("Amazing Workout", VALID_EXERCISES);

        try {
            assertEquals(routine.toModelType(), LEG_DAY);
            assertEquals(routineWithExercises.toModelType(), testRoutine);

        } catch (IllegalValueException e) {
            fail();
        }
    }

    @Test
    public void toModelType_invalidRoutineName_failure() {
        JsonAdaptedRoutine routine = new JsonAdaptedRoutine(INVALID_NAME, VALID_EXERCISES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, routine::toModelType);
    }

    @Test
    public void toModelType_invalidExerciseList_failure() {

        String expectedMessage = String.format(JsonAdaptedRoutine.MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName());

        //null input
        JsonAdaptedRoutine routineNull = new JsonAdaptedRoutine(null, null);
        assertThrows(IllegalValueException.class, expectedMessage, routineNull::toModelType);
    }
}
