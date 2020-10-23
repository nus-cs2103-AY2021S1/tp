package seedu.taskmaster.model.session;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.model.student.exceptions.DuplicateStudentException;
import seedu.taskmaster.model.student.exceptions.StudentNotFoundException;

/**
 * Represents a list of students' attendance.
 */
public class StudentRecordListManager implements StudentRecordList {
    private final ObservableList<StudentRecord> internalList = FXCollections.observableArrayList();
    private final ObservableList<StudentRecord> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Initialises an {@code StudentRecordListManager} with the given {@code students}.
     * The attendance of each student is marked as {@code NO_RECORD}.
     */
    public static StudentRecordList of(List<Student> students) {
        List<StudentRecord> studentRecords = students
                .stream()
                .map(student -> new StudentRecord(student.getName(), student.getNusnetId()))
                .collect(Collectors.toList());

        StudentRecordList studentRecordList = new StudentRecordListManager();
        studentRecordList.setStudentRecords(studentRecords);
        return studentRecordList;
    }

    /**
     * Marks the attendance of a student represented by their {@code nusnetId} with {@code attendanceType}.
     */
    @Override
    public void markStudentAttendance(NusnetId nusnetId, AttendanceType attendanceType) {
        requireAllNonNull(nusnetId, attendanceType);

        boolean isFound = false;

        for (StudentRecord studentRecord : internalList) {
            if (studentRecord.getNusnetId().equals(nusnetId)) {
                studentRecord.setAttendanceType(attendanceType);
                isFound = true;
                break;
            }
        }

        if (!isFound) {
            throw new StudentNotFoundException();
        }
    }

    /**
     * Marks the attendance of students represented by the list of {@code nusnetIds} with {@code attendanceType}.
     */
    @Override
    public void markAllStudents(List<NusnetId> nusnetIds, AttendanceType attendanceType) {
        for (NusnetId nusnetId : nusnetIds) {
            markStudentAttendance(nusnetId, attendanceType);
        }
    }

    @Override
    public void setStudentRecords(StudentRecordListManager replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code attendances}.
     * {@code attendances} must not contain duplicate students.
     */
    @Override
    public void setStudentRecords(List<StudentRecord> studentRecords) {
        requireAllNonNull(studentRecords);
        if (!studentsAreUnique(studentRecords)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(studentRecords);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}
     */
    @Override
    public ObservableList<StudentRecord> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<StudentRecord> iterator() {
        return internalList.iterator();
    }

    @Override
    public String toString() {
        String result = "";

        for (StudentRecord studentRecord : internalList) {
            result += studentRecord.toString() + "\n";
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentRecordListManager // instanceof handles nulls
                && internalList.equals(((StudentRecordListManager) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code studentRecords} contains only unique students.
     */
    private boolean studentsAreUnique(List<StudentRecord> studentRecords) {
        for (int i = 0; i < studentRecords.size() - 1; i++) {
            for (int j = i + 1; j < studentRecords.size(); j++) {
                if (studentRecords.get(i).getNusnetId().equals(studentRecords.get(j).getNusnetId())) {
                    return false;
                }
            }
        }
        return true;
    }
}
