package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.DateUtil.parseToDate;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.academic.Attendance;
import seedu.address.model.student.academic.Feedback;

public class JsonAdaptedAttendanceTest {

    @Test
    public void toModelType_invalidDate_throwsException() {
        JsonAdaptedAttendance test = new JsonAdaptedAttendance("", true, null);
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedAttendance(null, true, null);
        assertThrows(IllegalValueException.class, test::toModelType);

        test = new JsonAdaptedAttendance("abc", true, null);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_invalidFeedback_throwsException() {
        String dateInput = "10/10/20";
        String invalidFeedback = "!n_val!d";
        JsonAdaptedAttendance test = new JsonAdaptedAttendance(dateInput, true, invalidFeedback);
        assertThrows(IllegalValueException.class, test::toModelType);
    }

    @Test
    public void toModelType_noFeedback_success() throws Exception {
        String dateInput = "10/10/20";
        LocalDate date = parseToDate(dateInput);
        Attendance expected = new Attendance(date, true);

        JsonAdaptedAttendance test = new JsonAdaptedAttendance(dateInput, true, null);
        assertEquals(expected, test.toModelType());

        test = new JsonAdaptedAttendance(dateInput, true, "");
        assertEquals(expected, test.toModelType());
    }

    @Test
    public void toModelType_withFeedback_success() throws Exception {
        String dateInput = "10/10/20";
        LocalDate date = parseToDate(dateInput);
        String feedbackInput = "sleepy";
        Feedback feedback = new Feedback(feedbackInput);
        Attendance expected = new Attendance(date, true, feedback);

        JsonAdaptedAttendance test = new JsonAdaptedAttendance(dateInput, true, feedbackInput);
        assertEquals(expected, test.toModelType());

        test = new JsonAdaptedAttendance(dateInput, true, feedbackInput);
        assertEquals(expected, test.toModelType());
    }
}
