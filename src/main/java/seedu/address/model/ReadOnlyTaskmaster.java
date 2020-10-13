package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.NamedAttendance;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of an student list
 */
public interface ReadOnlyTaskmaster {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the list of students' attendances, represented as {@code Attendance}.
     */
    ObservableList<Attendance> getUnmodifiableAttendanceList();

    /**
     * Returns an unmodifiable view of the list of students' attendances, represented as {@code NamedAttendance}.
     */
    ObservableList<NamedAttendance> getNamedAttendanceList();
}
