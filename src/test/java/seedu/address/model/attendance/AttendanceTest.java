package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {
    @Test
    public void fromDateString() {
        // null attendance
        assertThrows(NullPointerException.class, () -> Attendance.fromDateString(null));

        // invalid attendances
        assertThrows(IllegalArgumentException.class, () -> Attendance.fromDateString(""));
        assertThrows(IllegalArgumentException.class, () -> Attendance.fromDateString("hello"));

        // valid attendances
        assertEquals(Attendance.fromDateString("13/01/2020").toString(), "13/01/2020");
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Attendance.fromDateString("13/1/2020"));
        assertThrows(IllegalArgumentException.class, () -> Attendance.fromDateString("-13/01/2020"));
        assertThrows(IllegalArgumentException.class, () -> Attendance.fromDateString("13012020"));
        assertThrows(IllegalArgumentException.class, () -> Attendance.fromDateString("13 01 2020"));
        assertThrows(IllegalArgumentException.class, () -> Attendance.fromDateString("13    01    2020"));
    }

    @Test
    public void equals() {
        Attendance attendance1 = Attendance.fromDateString("13/01/2020");
        assertEquals(Attendance.fromDateString("13/01/2020"), attendance1);
        assertEquals(attendance1, Attendance.fromDateString("13/01/2020"));
        assertEquals(attendance1, attendance1);

        assertNotEquals(attendance1, null);
        assertNotEquals(attendance1, Attendance.fromDateString("12/01/2020"));
        assertNotEquals(attendance1, Attendance.fromDateString("13/02/2020"));
        assertNotEquals(attendance1, Attendance.fromDateString("13/01/2021"));
    }
}
