package seedu.address.model.attendance;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.NusnetId;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.model.student.exceptions.StudentNotFoundException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a list of students' attendance.
 */
public class AttendanceList implements Iterable<Attendance> {
    private final ObservableList<Attendance> internalList = FXCollections.observableArrayList();
    private final ObservableList<Attendance> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Initialises an {@code AttendanceList} with the given {@code students}.
     * The attendance of each student is marked as {@code NO_RECORD}.
     */
    public static AttendanceList of(List<Student> students) {
        List<Attendance> attendances = students
                .stream()
                .map(student -> new Attendance(student.getNusnetId()))
                .collect(Collectors.toList());
        
        AttendanceList attendanceList = new AttendanceList();
        attendanceList.setAttendances(attendances);
        return attendanceList;
    }

    /**
     * Marks the attendance of a student represented by their {@code nusnetId} with {@code attendanceType}.
     */
    public void markStudentAttendance(NusnetId nusnetId, AttendanceType attendanceType) {
        requireAllNonNull(nusnetId, attendanceType);

        boolean isFound = false;

        for (Attendance attendance : internalList) {
            if (attendance.getNusnetId().equals(nusnetId)) {
                attendance.setAttendanceType(attendanceType);
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
    public void markAllAttendance(List<NusnetId> nusnetIds, AttendanceType attendanceType) {
        for (NusnetId nusnetId : nusnetIds) {
            markStudentAttendance(nusnetId, attendanceType);
        }
    }

    public void setAttendances(AttendanceList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code attendances}.
     * {@code attendances} must not contain duplicate students.
     */
    public void setAttendances(List<Attendance> attendances) {
        requireAllNonNull(attendances);
        if (!studentsAreUnique(attendances)) {
            throw new DuplicateStudentException();
        }

        internalList.setAll(attendances);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}
     */
    public ObservableList<Attendance> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Attendance> iterator() {
        return internalList.iterator();
    }
    
    @Override
    public String toString() {
        String result = "";

        for (Attendance attendance : internalList) {
            result += attendance.toString() + "\n";
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttendanceList // instanceof handles nulls
                && internalList.equals(((AttendanceList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code attendances} contains only unique students.
     */
    private boolean studentsAreUnique(List<Attendance> attendances) {
        for (int i = 0; i < attendances.size() - 1; i++) {
            for (int j = i + 1; j < attendances.size(); j++) {
                if (attendances.get(i).getNusnetId().equals(attendances.get(j).getNusnetId())) {
                    return false;
                }
            }
        }
        return true;
    }
}
