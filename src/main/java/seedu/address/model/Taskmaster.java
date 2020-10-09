package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.attendance.AttendanceType;
import seedu.address.model.attendance.NamedAttendance;
import seedu.address.model.student.Name;
import seedu.address.model.student.NusnetId;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.student.exceptions.StudentNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Taskmaster implements ReadOnlyTaskmaster {

    private final UniqueStudentList students;
    private AttendanceList attendanceList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public Taskmaster() {}

    /**
     * Creates an Taskmaster using the Students in the {@code toBeCopied}
     */
    public Taskmaster(ReadOnlyTaskmaster toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code Taskmaster} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskmaster newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        attendanceList = AttendanceList.of(getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student list.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the student list.
     * The student must not already exist in the student list.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the student list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in
     * the student list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code Taskmaster}.
     * {@code key} must exist in the student list.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Marks the attendance of a {@code target} student with {@code attendanceType}
     */
    public void markStudent(Student target, AttendanceType attendanceType) {
        attendanceList.markStudentAttendance(target.getNusnetId(), attendanceType);
    }

    //// util methods

    /**
     * Returns the name of the student with the given {@code nusnetId}.
     * The student must exist in the list.
     */
    public Name getNameByNusnetId(NusnetId nusnetId) {
        for (Student student : getStudentList()) {
            if (student.getNusnetId().equals(nusnetId)) {
                return student.getName();
            }
        }

        throw new StudentNotFoundException();
    }

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    /**
     * Returns an unmodifiable view of the list of {@code Attendance} backed by the internal list of
     * {@code versionedTaskmaster}
     */
    public ObservableList<NamedAttendance> getNamedAttendanceList() {
        ObservableList<Attendance> attendances = attendanceList.asUnmodifiableObservableList();
        ObservableList<NamedAttendance> namedAttendances = FXCollections.observableArrayList();
        for (Attendance attendance : attendances) {
            try {
                Name name = getNameByNusnetId(attendance.getNusnetId());
                namedAttendances.add(new NamedAttendance(name, attendance));
            } catch (StudentNotFoundException stfe) {
                Name name = Name.getStudentNotFoundName();
                namedAttendances.add(new NamedAttendance(name, attendance));
            }
        }

        return FXCollections.unmodifiableObservableList(namedAttendances);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Taskmaster // instanceof handles nulls
                && students.equals(((Taskmaster) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
