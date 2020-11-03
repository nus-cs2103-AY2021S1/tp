package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAttendance.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALEX;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Attendance;

public class JsonAdaptedAttendanceTest {

    private static final String INVALID_PARTICIPATION_SCORE = "4524324";
    private static final String INVALID_WEEK_NUMBER = "365";

    private static final String VALID_PARTICIPATION_SCORE = ALEX.getAttendance().getParticipationScoreAsString();
    private static final List<JsonAdaptedWeekNumber> VALID_WEEK_NUMBERS = List.of("1", "2", "5", "11")
            .stream().map(JsonAdaptedWeekNumber::new).collect(Collectors.toList());

    private static final List<JsonAdaptedWeekNumber> DUMMY_WEEK_NUMBERS = new ArrayList<>();

    @Test
    public void toModelType_validAttendanceDetails_returnsAttendance() throws Exception {
        JsonAdaptedAttendance attendance = new JsonAdaptedAttendance(ALEX.getAttendance());
        assertEquals(ALEX.getAttendance(), attendance.toModelType());
    }

    @Test
    public void toModelType_invalidWeekNumbers_throwsIllegalValueException() {
        List<JsonAdaptedWeekNumber> invalidWeekNumbers = new ArrayList<>(VALID_WEEK_NUMBERS);
        invalidWeekNumbers.add(new JsonAdaptedWeekNumber(INVALID_WEEK_NUMBER));
        JsonAdaptedAttendance attendance =
                new JsonAdaptedAttendance(invalidWeekNumbers, VALID_PARTICIPATION_SCORE);
        String expectedMessage = String.format((Attendance.WEEK_NUMBER_CONSTRAINTS),
                Attendance.MIN_WEEK, Attendance.MAX_WEEK);
        assertThrows(IllegalValueException.class, expectedMessage, attendance::toModelType);
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
