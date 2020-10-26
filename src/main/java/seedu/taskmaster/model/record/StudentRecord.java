package seedu.taskmaster.model.record;

import seedu.taskmaster.model.student.Name;
import seedu.taskmaster.model.student.NusnetId;

/**
 * Represents a record of a student in a session.
 */
public class StudentRecord {
    private static final String STRING_FORMAT = "%s|%s|%s";

    private final Name name;
    private final NusnetId nusnetId;
    private final AttendanceType attendanceType;
    private final ClassParticipation classParticipation;

    /**
     * The student is represented by their {@code nusnetId} and initially marked with {@code NO_RECORD}.
     */
    public StudentRecord(Name name, NusnetId nusnetId) {
        this.name = name;
        this.nusnetId = nusnetId;
        this.attendanceType = AttendanceType.NO_RECORD;
        this.classParticipation = new ClassParticipation();
    }

    /**
     * Creates an StudentRecord object with the AttendanceType already specified.
     */
    public StudentRecord(Name name, NusnetId nusnetId, AttendanceType attendanceType,
                         ClassParticipation classParticipation) {
        this.name = name;
        this.nusnetId = nusnetId;
        this.attendanceType = attendanceType;
        this.classParticipation = classParticipation;
    }

    public Name getName() {
        return name;
    }

    public NusnetId getNusnetId() {
        return nusnetId;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    public ClassParticipation getClassParticipation() {
        return classParticipation;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, nusnetId, attendanceType.name(), classParticipation);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentRecord)) {
            return false;
        }

        // state check
        StudentRecord studentRecord = (StudentRecord) other;
        return this.name.equals(studentRecord.name)
                && this.nusnetId.equals(studentRecord.nusnetId)
                && this.attendanceType.equals(studentRecord.attendanceType)
                && this.classParticipation.equals(studentRecord.classParticipation);
    }
}
