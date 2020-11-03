package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAttendance.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALEX;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Attendance;

public class JsonAdaptedAttendanceTest {

    private static final String INVALID_PARTICIPATION_SCORE = "4524324";

    private static final List<JsonAdaptedWeekNumber> DUMMY_WEEK_NUMBERS = new ArrayList<>();

    @Test
    public void toModelType_validAttendanceDetails_returnsAttendance() throws Exception {
        JsonAdaptedAttendance attendance = new JsonAdaptedAttendance(ALEX.getAttendance());
        assertEquals(ALEX.getAttendance(), attendance.toModelType());
    }

    @Test
    public void toModelType_invalidParticipationScore_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance =
                new JsonAdaptedAttendance(DUMMY_WEEK_NUMBERS, INVALID_PARTICIPATION_SCORE);
        String expectedMessage = Attendance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

    @Test
    public void toModelType_nullParticipationScore_throwsIllegalValueException() {
        JsonAdaptedAttendance attendance = new JsonAdaptedAttendance(DUMMY_WEEK_NUMBERS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Participation Score");
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
    }

}
