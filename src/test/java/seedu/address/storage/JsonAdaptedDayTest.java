package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDay.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCalorieManager.CALORIE_MANAGER1;
import static seedu.address.testutil.TypicalDays.DAY2;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.day.Date;
import seedu.address.model.day.Weight;

public class JsonAdaptedDayTest {
    private static final String INVALID_DATE = "";
    private static final String INVALID_WEIGHT = "7-0";

    private static final CalorieManager VALID_CALORIE_MANAGER = CALORIE_MANAGER1;
    private static final String VALID_DATE = DAY2.getDate().toString();
    private static final String VALID_WEIGHT = DAY2.getWeight().toString();

    @Test
    public void toModelType_validDayDetails_returnsDay() throws Exception {
        JsonAdaptedDay day = new JsonAdaptedDay(DAY2);
        assertEquals(DAY2, day.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {

        JsonAdaptedDay day =
                new JsonAdaptedDay(INVALID_DATE, VALID_WEIGHT, new JsonAdaptedCalorieManager(VALID_CALORIE_MANAGER));
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedDay day = new JsonAdaptedDay(null, VALID_WEIGHT,
                new JsonAdaptedCalorieManager(VALID_CALORIE_MANAGER));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedDay day =
                new JsonAdaptedDay(VALID_DATE, INVALID_WEIGHT, new JsonAdaptedCalorieManager(VALID_CALORIE_MANAGER));
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedDay day = new JsonAdaptedDay(VALID_DATE, null, new JsonAdaptedCalorieManager(VALID_CALORIE_MANAGER));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }

    @Test
    public void toModelType_nullCalorieManager_throwsIllegalValueException() {
        JsonAdaptedDay day = new JsonAdaptedDay(VALID_DATE, VALID_WEIGHT, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, CalorieManager.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, day::toModelType);
    }

}
