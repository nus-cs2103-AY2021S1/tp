package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedInput.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Time;


public class JsonAdaptedInputTest {
    private static final String INVALID_TIME = "TEST";
    private static final String INVALID_TIME_2 = "12345";
    private static final String INVALID_TIME_3 = "";
    private static final String INVALID_TIME_4 = "9999";
    private static final String INVALID_FOOD = "";
    private static final String INVALID_CALORIES = "TEST";
    private static final String INVALID_CALORIES_2 = "-1";

    private static final String VALID_TIME = "1230";
    private static final String VALID_FOOD = "Bread";
    private static final String VALID_CALORIES = "123";
    private static final Input VALID_INPUT = new Input(new Time(VALID_TIME), new Food(VALID_FOOD),
            new CalorieCount(VALID_CALORIES));

    @Test
    public void toModelType_validDayDetails_returnsDay() throws Exception {
        JsonAdaptedInput input = new JsonAdaptedInput(VALID_INPUT);
        assertEquals(VALID_INPUT, input.toModelType());
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(INVALID_TIME, VALID_FOOD, VALID_CALORIES);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

    @Test
    public void toModelType_invalidTime2_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(INVALID_TIME_2, VALID_FOOD, VALID_CALORIES);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

    @Test
    public void toModelType_invalidTime3_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(INVALID_TIME_3, VALID_FOOD, VALID_CALORIES);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

    @Test
    public void toModelType_invalidTime4_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(INVALID_TIME_4, VALID_FOOD, VALID_CALORIES);
        String expectedMessage = Time.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(null, VALID_FOOD, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

    @Test
    public void toModelType_invalidFood_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(VALID_TIME, INVALID_FOOD, VALID_CALORIES);
        String expectedMessage = Food.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

    @Test
    public void toModelType_nullFood_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(VALID_TIME, null, VALID_CALORIES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Food.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

    @Test
    public void toModelType_invalidCalorieCount_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(VALID_TIME, VALID_FOOD, INVALID_CALORIES);
        String expectedMessage = CalorieCount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

    @Test
    public void toModelType_invalidCalorieCount2_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(VALID_TIME, VALID_FOOD, INVALID_CALORIES_2);
        String expectedMessage = CalorieCount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

    @Test
    public void toModelType_nullCalorieCount_throwsIllegalValueException() {
        JsonAdaptedInput input = new JsonAdaptedInput(VALID_TIME, VALID_FOOD, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CalorieCount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, input::toModelType);
    }

}
