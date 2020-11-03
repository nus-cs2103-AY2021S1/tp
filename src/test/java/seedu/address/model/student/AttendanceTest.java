package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.student.Attendance.MAX_SCORE;
import static seedu.address.model.student.Attendance.MIN_SCORE;
import static seedu.address.model.student.Attendance.MIN_VALUE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void isValidWeekNumber() {
        // null attendance week number
        assertThrows(NullPointerException.class, () -> Attendance.isValidWeekNumber(null));

        // invalid attendance week numbers
        assertFalse(Attendance.isValidWeekNumber("0")); // 1 less than lower bound
        assertFalse(Attendance.isValidWeekNumber("14")); // 1 more than upper bound
        assertFalse(Attendance.isValidWeekNumber("ab")); // non-numeric
        assertFalse(Attendance.isValidWeekNumber("one"));
        assertFalse(Attendance.isValidWeekNumber("Week 1")); // contains alphabets
        assertFalse(Attendance.isValidWeekNumber("1 1")); // contains space

        // valid attendance week numbers
        assertTrue(Attendance.isValidWeekNumber("1"));
        assertTrue(Attendance.isValidWeekNumber("13"));
        assertTrue(Attendance.isValidWeekNumber("10"));


    }

    @Test
    public void isValidParticipation() {
        // null participation score
        assertThrows(NullPointerException.class, () -> Attendance.isValidParticipation(null));

        // invalid participation score
        assertFalse(Attendance.isValidParticipation("-101")); // 1 less than lower bound
        assertFalse(Attendance.isValidParticipation("101")); // 1 more than upper bound
        assertFalse(Attendance.isValidParticipation("two")); // non-numeric
        assertFalse(Attendance.isValidParticipation("score 10")); // contains alphabets
        assertFalse(Attendance.isValidParticipation("10 0")); // contains space

        // valid participation score
        assertTrue(Attendance.isValidParticipation("-100"));
        assertTrue(Attendance.isValidParticipation("100"));
        assertTrue(Attendance.isValidParticipation("-49"));
        assertTrue(Attendance.isValidParticipation("61"));
    }

    @Test
    public void addAttendance_throwsIllegalArgumentException() {
        Attendance attendance = new Attendance();
        String invalidAttendance = "-1";
        assertThrows(IllegalArgumentException.class, () -> attendance.addAttendance(invalidAttendance));
    }

    @Test
    public void deleteAttendance_throwsIllegalArgumentException() {
        Attendance attendance = new Attendance();
        String invalidAttendance = "14";
        assertThrows(IllegalArgumentException.class, () -> attendance.deleteAttendance(invalidAttendance));
    }

    @Test
    public void editParticipation_throwsIllegalArgumentException() {
        Attendance attendance = new Attendance();
        String invalidParticipation = "105";
        assertThrows(IllegalArgumentException.class, () -> attendance.editParticipation(invalidParticipation));
    }

    /**
     * Edit participation always add the given score to the value. To reduce the score, provide a negative number.
     */
    @Test
    public void editParticipation() {
        Attendance attendance = new Attendance();

        // null participation score
        assertThrows(NullPointerException.class, () -> attendance.editParticipation(null));

        // doesn't exceed max score
        attendance.editParticipation(Integer.toString(MAX_SCORE));
        attendance.editParticipation(Integer.toString(MAX_SCORE));
        assertEquals(MAX_SCORE, attendance.getParticipationScore());

        // doesn't fall below min score
        attendance.editParticipation("0");
        attendance.editParticipation(Integer.toString(MIN_VALUE));
        assertEquals(MIN_SCORE, attendance.getParticipationScore());

        // valid scores
        attendance.editParticipation("49");
        assertEquals(49, attendance.getParticipationScore());
        attendance.editParticipation("-15");
        assertEquals(34, attendance.getParticipationScore());
    }
}
