package seedu.taskmaster.model;

import javafx.collections.ObservableList;
import seedu.taskmaster.model.attendance.Attendance;
import seedu.taskmaster.model.attendance.NamedAttendance;
import seedu.taskmaster.model.student.Student;

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
