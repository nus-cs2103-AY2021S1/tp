package seedu.taskmaster.model.session;

import javafx.collections.ObservableList;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.record.StudentRecordList;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.exceptions.StudentNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a tutorial session conducted by a teaching assistant.
 */
public class Session {

    private static final String STRING_FORMAT = "%s|%s";

    private final SessionName sessionName;
    private final SessionDateTime sessionDateTime;
    private final StudentRecordList studentRecords;

    /**
     * A session is represented by its {@code sessionName},
     * stores date and time data and a list of student records.
     */
    public Session(SessionName sessionName,
                   SessionDateTime sessionDateTime,
                   StudentRecordList studentRecords) {
        this.sessionName = sessionName;
        this.sessionDateTime = sessionDateTime;
        this.studentRecords = studentRecords;
    }

    public SessionName getSessionName() {
        return sessionName;
    }

    public SessionDateTime getSessionDateTime() {
        return sessionDateTime;
    }

    /**
     * Returns list of student records as an unmodifiable {@code ObservableList}
     */
    public ObservableList<StudentRecord> getStudentRecords() {
        return studentRecords.asUnmodifiableObservableList();
    }

    /**
     * Marks the attendance of a student with the given {@code nusnetId} in the
     * student record list with {@code attendanceType}.
     */
    public void markStudentAttendance(NusnetId nusnetId, AttendanceType attendanceType) {
        assert nusnetId != null;
        assert attendanceType != null;
        studentRecords.markStudentAttendance(nusnetId, attendanceType);
    }

    /**
     * Sets the {@code AttendanceType} of all {@code StudentRecords} to NO_RECORD.
     */
    public void clearAttendance() {
        studentRecords.markAllAttendance(
                studentRecords.asUnmodifiableObservableList().stream()
                        .map(StudentRecord::getNusnetId).collect(Collectors.toList()),
                AttendanceType.NO_RECORD);
    }

    /**
     * Updates the {@code StudentRecordList} with the data in {@code studentRecords}.
     * @throws StudentNotFoundException
     */
    public void updateStudentRecords(List<StudentRecord> studentRecords) throws StudentNotFoundException {
        for (StudentRecord studentRecord: studentRecords) {
            this.studentRecords.markStudentAttendance(
                    studentRecord.getNusnetId(),
                    studentRecord.getAttendanceType());
        }
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT, sessionName.toString(), sessionDateTime);
    }

    /**
     * Returns true if both sessions have the same identity and data fields.
     * This defines a stronger notion of equality between two sessions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Session)) {
            return false;
        }
        Session otherSession = (Session) other;
        return otherSession.getSessionName().equals(getSessionName())
                && otherSession.getSessionDateTime().equals(getSessionDateTime())
                && otherSession.getStudentRecords().equals(getStudentRecords());
    }
}
