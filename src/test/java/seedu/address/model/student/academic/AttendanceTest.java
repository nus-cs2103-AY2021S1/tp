package seedu.address.model.student.academic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.util.DateUtil.parseToDate;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    private static final LocalDate VALID_DATE = parseToDate("14/04/1998");
    private static final Feedback VALID_FEEDBACK = new Feedback("sleepy");
    private static final Attendance VALID_ATTENDANCE = new Attendance(VALID_DATE, "present",
            new Feedback("sleepy"));

    @Test
    public void constructor_null_throwsNullPointerException() {
        // All fields null
        assertThrows(NullPointerException.class, () -> new Attendance(null, null,
                new Feedback(null)));

        // All but one null
        assertThrows(NullPointerException.class, () -> new Attendance(VALID_DATE, null,
                new Feedback(null)));
        assertThrows(NullPointerException.class, () -> new Attendance(null, "present",
                new Feedback(null)));
        assertThrows(NullPointerException.class, () -> new Attendance(null, null,
                VALID_FEEDBACK));

        // Only one null
        assertThrows(NullPointerException.class, () -> new Attendance(VALID_DATE, "present",
                new Feedback(null)));
        assertThrows(NullPointerException.class, () -> new Attendance(null, "present",
                VALID_FEEDBACK));
        assertThrows(NullPointerException.class, () -> new Attendance(VALID_DATE, null,
                VALID_FEEDBACK));
    }

    @Test
    public void constructor_invalidAttendanceFields_throwsIllegalArgumentException() {
        String invalidAttendanceStatus = "hey";
        String invalidAttendanceFeedback = "";

        // 2 invalid
        assertThrows(IllegalArgumentException.class, () -> new Attendance(VALID_DATE,
                invalidAttendanceStatus, new Feedback(invalidAttendanceFeedback)));

        // 1 invalid
        assertThrows(IllegalArgumentException.class, () -> new Attendance(VALID_DATE,
                "present", new Feedback(invalidAttendanceFeedback)));
        assertThrows(IllegalArgumentException.class, () -> new Attendance(VALID_DATE,
                invalidAttendanceStatus, new Feedback("attentive")));
    }

    @Test
    public void equals_test() {

        // different object
        assertNotEquals(VALID_ATTENDANCE, "hey");

        // diff fields
        assertNotEquals(VALID_ATTENDANCE, new Attendance(parseToDate("17/04/1998"), "present",
                VALID_FEEDBACK));
        assertNotEquals(VALID_ATTENDANCE, new Attendance(VALID_DATE, "absent",
                VALID_FEEDBACK));
        assertNotEquals(VALID_ATTENDANCE, new Attendance(VALID_DATE, "present",
                new Feedback("sleepayy son")));

        // same object
        assertEquals(VALID_ATTENDANCE, VALID_ATTENDANCE);

        // same fields
        assertEquals(VALID_ATTENDANCE, new Attendance(VALID_DATE, "present",
                VALID_FEEDBACK));

    }

}
