package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedActivity.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalActivities.ARCHERY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.activity.Importance;
import seedu.address.model.activity.WanderlustDateTime;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;

public class JsonAdaptedActivityTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_COST = "-23";
    private static final String INVALID_IMPORTANCE = "9";
    private static final String INVALID_DATE_TIME = "2020020214:20";

    private static final String VALID_NAME = ARCHERY.getName().getValue();
    private static final String VALID_LOCATION = ARCHERY.getLocation().getValue();
    private static final String VALID_COST = ARCHERY.getCost().getValue();
    private static final String VALID_IMPORTANCE = ARCHERY.getLevelOfImportance().getValue();
    private static final String VALID_DATE_TIME = ARCHERY.getActivityDateTime().getDateTime();

    @Test
    public void toModelType_validActivityDetails_returnsActivity() throws Exception {
        JsonAdaptedActivity activity = new JsonAdaptedActivity(ARCHERY);
        assertEquals(ARCHERY, activity.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(INVALID_NAME, VALID_LOCATION, VALID_COST, VALID_IMPORTANCE, VALID_DATE_TIME);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(null, VALID_LOCATION, VALID_COST, VALID_IMPORTANCE, VALID_DATE_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_NAME, INVALID_LOCATION, VALID_COST, VALID_IMPORTANCE, VALID_DATE_TIME);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_NAME, null, VALID_COST, VALID_IMPORTANCE, VALID_DATE_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidCost_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_NAME, VALID_LOCATION, INVALID_COST, VALID_IMPORTANCE, VALID_DATE_TIME);
        String expectedMessage = Cost.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullCost_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_NAME, VALID_LOCATION, null, VALID_IMPORTANCE, VALID_DATE_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidImportance_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_NAME, VALID_LOCATION, VALID_COST, INVALID_IMPORTANCE, VALID_DATE_TIME);
        String expectedMessage = Importance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullImportance_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_NAME, VALID_LOCATION, VALID_COST, null, VALID_DATE_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Importance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_NAME, VALID_LOCATION, VALID_COST, VALID_IMPORTANCE, INVALID_DATE_TIME);
        String expectedMessage = WanderlustDateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        JsonAdaptedActivity activity =
                new JsonAdaptedActivity(VALID_NAME, VALID_LOCATION, VALID_COST, VALID_IMPORTANCE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, WanderlustDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, activity::toModelType);
    }
}
