package seedu.taskmaster.model.record;

import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.taskmaster.model.student.NusnetId;

public interface StudentRecordList extends Iterable<StudentRecord> {

    /**
     * Marks the attendance of a student represented by their {@code nusnetId} with {@code attendanceType}.
     */
    void markStudentAttendance(NusnetId nusnetId, AttendanceType attendanceType);

    /**
     * Marks the attendance of students represented by the list of {@code nusnetIds} with {@code attendanceType}.
     */
    void markAllStudents(List<NusnetId> nusnetIds, AttendanceType attendanceType);

    void setStudentRecords(StudentRecordListManager replacement);

    /**
     * Replaces the contents of this list with {@code studentRecords}.
     * {@code studentRecords} must not contain duplicate students.
     */
    void setStudentRecords(List<StudentRecord> studentRecords);

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}
     */
    ObservableList<StudentRecord> asUnmodifiableObservableList();

    @Override
    Iterator<StudentRecord> iterator();
}
