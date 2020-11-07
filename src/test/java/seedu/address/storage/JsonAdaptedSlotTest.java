package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSlots.LEG_DAY_WEDNESDAY_1600_1800;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.util.Name;

public class JsonAdaptedSlotTest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Slot's %s field is missing!";
    public static final String INVALID_ACTIVITY_NAME = "CS2103$";
    public static final String INVALID_DAY = "random";
    public static final String INVALID_DURATION = "1600 - 1800";

    public static final String ROUTINE_TYPE = "routine";
    public static final String LESSON_TYPE = "lesson";

    public static final String VALID_ACTIVITY = "CS2103";
    public static final String VALID_DAY = "monday";
    public static final String VALID_DURATION = "1600-1800";

    @Test
    public void toModelType_nullActivityName_throwsIllegalValueException() {
        JsonAdaptedSlot slot = new JsonAdaptedSlot(
                null, ROUTINE_TYPE, VALID_DAY, VALID_DURATION, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, slot::toModelType);
    }

    @Test
    public void toModelType_invalidActivityName_throwsIllegalValueException() {
        JsonAdaptedSlot slot = new JsonAdaptedSlot(
                INVALID_ACTIVITY_NAME, ROUTINE_TYPE, VALID_DAY, VALID_DURATION, null, null);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, slot::toModelType);
    }

    @Test
    public void toModelType_invalidDay_throwsIllegalValueException() {
        JsonAdaptedSlot slot = new JsonAdaptedSlot(
                VALID_ACTIVITY, LESSON_TYPE, INVALID_DAY, VALID_DURATION, null, null);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, slot::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedSlot slot = new JsonAdaptedSlot(
                VALID_ACTIVITY, LESSON_TYPE, VALID_DAY, INVALID_DURATION, null, null);
        String expectedMessage = Duration.MESSAGE_CONSTRAINTS_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, slot::toModelType);
    }

    @Test
    public void toModelType_validFields_success() throws Exception {
        JsonAdaptedSlot slot = new JsonAdaptedSlot(LEG_DAY_WEDNESDAY_1600_1800);
        assertEquals(LEG_DAY_WEDNESDAY_1600_1800, slot.toModelType());
    }
}
