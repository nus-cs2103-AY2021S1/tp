package seedu.address.testutil;

import seedu.address.model.student.Attendance;

public class AttendanceBuilder {
    public static final String DEFAULT_ATTENDANCE_WEEK = "2";

    /**
     * Creates a {@code AttendanceBuilder} with the default details.
     */
    public AttendanceBuilder() {
    }

    public Attendance build() {
        Attendance attendance = new Attendance();
        attendance.addAttendance(DEFAULT_ATTENDANCE_WEEK);
        return attendance;
    }
}
