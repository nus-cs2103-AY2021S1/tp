package seedu.fma.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;
import static seedu.fma.storage.JsonAdaptedExercise.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalExercises.EXERCISE_A;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fma.commons.exceptions.IllegalValueException;
import seedu.fma.model.LogBook;
import seedu.fma.model.util.Calories;
import seedu.fma.model.util.Name;

public class JsonAdaptedExerciseTest {
    private static final String INVALID_EXERCISE = "Sitting in front of my laptop :(";
    private static final int INVALID_CALORIES = -1;

    private static final int VALID_CALORIES = 16;
    private static final String VALID_EXERCISE = EXERCISE_A.getName().toString();

    private final LogBook logBook = new LogBook();

    @BeforeEach
    void setup() {
        logBook.setExercises(Arrays.asList(getSampleExercises()));
    }

    @Test
    public void toModelType_validExerciseDetails_returnsExercise() throws Exception {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(EXERCISE_A);
        assertEquals(EXERCISE_A, exercise.toModelType());
    }

    @Test
    public void toModelType_invalidExercise_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(INVALID_EXERCISE, VALID_CALORIES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullExercise_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(null, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidCalories_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_EXERCISE, INVALID_CALORIES);
        String expectedMessage = Calories.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }
}
