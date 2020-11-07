package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.DailyCalorie;

public class JsonAdaptedDailyCalorieTest {

    private static final DailyCalorie VALID_DAILY_CALORIE = new DailyCalorie(
            LocalDate.of(2020, 7, 5));

    @BeforeAll
    static void init() {
        VALID_DAILY_CALORIE.addCalories(1500);
    }
    @Test
    public void toModelType_allValidInputs_success() {
        JsonAdaptedDailyCalorie dailyCalorie = new JsonAdaptedDailyCalorie("2020-07-05", "1500");
        JsonAdaptedDailyCalorie dailyCalorie1 = new JsonAdaptedDailyCalorie(VALID_DAILY_CALORIE);
        try {
            assertEquals(dailyCalorie.toModelType(), VALID_DAILY_CALORIE);
            assertEquals(dailyCalorie1.toModelType(), VALID_DAILY_CALORIE);
        } catch (IllegalValueException e) {
            fail();
        }
    }

    @Test
    public void toModelType_invalidDate_failure() {
        String failureMessage = String.format(JsonAdaptedDailyCalorie.MISSING_FIELD_MESSAGE_FORMAT,
                LocalDate.class.getSimpleName());
        JsonAdaptedDailyCalorie dailyCalorie = new JsonAdaptedDailyCalorie(null, "1500");

        assertThrows(IllegalValueException.class, failureMessage, dailyCalorie::toModelType);
    }

    @Test
    public void toModelType_invalidCalorie_failure() {

        JsonAdaptedDailyCalorie dailyCalorie = new JsonAdaptedDailyCalorie("2020-07-05", "-1");

        //Negative Calorie value
        assertThrows(IllegalValueException.class,
                JsonAdaptedDailyCalorie.INVALID_CALORIE_MESSAGE_FORMAT, dailyCalorie::toModelType);

        //String as Calorie value
        JsonAdaptedDailyCalorie dailyCalorie1 = new JsonAdaptedDailyCalorie("2020-07-05", "a");
        assertThrows(IllegalValueException.class,
                JsonAdaptedDailyCalorie.INVALID_CALORIE_MESSAGE_FORMAT, dailyCalorie1::toModelType);

        //Too large of a Calorie value
        JsonAdaptedDailyCalorie dailyCalorie2 = new JsonAdaptedDailyCalorie("2020-07-05",
                "99999999999999999");
        assertThrows(IllegalValueException.class,
                JsonAdaptedDailyCalorie.INVALID_CALORIE_MESSAGE_FORMAT, dailyCalorie2::toModelType);
    }
}
