package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedOutput.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;

public class JsonAdaptedOutputTest {
    private static final String INVALID_TIME = "TEST";
    private static final String INVALID_TIME_2 = "12345";
    private static final String INVALID_TIME_3 = "";
    private static final String INVALID_TIME_4 = "9999";
    private static final String INVALID_EXERCISE = "";
    private static final String INVALID_CALORIES = "TEST";
    private static final String INVALID_CALORIES_2 = "-1";

    private static final String VALID_TIME = "1230";
    private static final String VALID_EXERCISE = "Run";
    private static final String VALID_CALORIES = "123";
    private static final Output VALID_OUTPUT = new Output(new Time(VALID_TIME), new Exercise(VALID_EXERCISE),
            new CalorieCount(VALID_CALORIES));

    @Test
    public void toModelType_validDayDetails_returnsDay() throws Exception {
        JsonAdaptedOutput output = new JsonAdaptedOutput(VALID_OUTPUT);
        assertEquals(VALID_OUTPUT, output.toModelType());
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(INVALID_TIME, VALID_EXERCISE, VALID_CALORIES);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }

    @Test
    public void toModelType_invalidTime2_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(INVALID_TIME_2, VALID_EXERCISE, VALID_CALORIES);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }

    @Test
    public void toModelType_invalidTime3_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(INVALID_TIME_3, VALID_EXERCISE, VALID_CALORIES);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }

    @Test
    public void toModelType_invalidTime4_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(INVALID_TIME_4, VALID_EXERCISE, VALID_CALORIES);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(null, VALID_EXERCISE, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }

    @Test
    public void toModelType_invalidExercise_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(VALID_TIME, INVALID_EXERCISE, VALID_CALORIES);
        String expectedMessage = Exercise.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }

    @Test
    public void toModelType_nullExercise_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(VALID_TIME, null, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Exercise.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }

    @Test
    public void toModelType_invalidCalorieCount_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(VALID_TIME, VALID_EXERCISE, INVALID_CALORIES);
        String expectedMessage = CalorieCount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }

    @Test
    public void toModelType_invalidCalorieCount2_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(VALID_TIME, VALID_EXERCISE, INVALID_CALORIES_2);
        String expectedMessage = CalorieCount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }

    @Test
    public void toModelType_nullCalorieCount_throwsIllegalValueException() {
        JsonAdaptedOutput output = new JsonAdaptedOutput(VALID_TIME, VALID_EXERCISE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CalorieCount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, output::toModelType);
    }
}
