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
     * Marks the attendances of all {@code StudentRecords} with the given {@code attendanceType}
     */
    void markAllStudentAttendances(AttendanceType attendanceType);

    /**
     * Updates participation score of a student represented by their {@code nusnetId} to {@code score}.
     */
    void scoreStudentParticipation(NusnetId nusnetId, double score);

    /**
     * Updates the {@code ClassParticipation} of all {@code StudentRecords} which are {@code PRESENT} with the
     * given score.
     */
    void scoreAllParticipation(double score);

    /**
     * Returns the lowest score amongst all students in the student list.
     */
    double getLowestScore();

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
