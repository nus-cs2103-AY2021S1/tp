package seedu.address.model.attendance;

import seedu.address.model.student.NusnetId;

/**
 * Represents a student's attendance.
 */
public class Attendance {
    private static final String STRING_FORMAT = "%s|%s";

    private final NusnetId nusnetId;
    private AttendanceType attendanceType;

    /**
     * The student is represented by their {@code nusnetId} and initially marked with {@code NO_RECORD}.
     */
    public Attendance(NusnetId nusnetId) {
        this.nusnetId = nusnetId;
        this.attendanceType = AttendanceType.NO_RECORD;
    }

    public NusnetId getNusnetId() {
        return nusnetId;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, nusnetId, attendanceType.name());
    }
}
