package seedu.taskmaster.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.record.StudentRecordList;
import seedu.taskmaster.model.record.StudentRecordListManager;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionDateTime;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.model.student.UniqueStudentList;
import seedu.taskmaster.model.student.exceptions.StudentNotFoundException;

/**
 * Wraps all data at the Taskmaster level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Taskmaster implements ReadOnlyTaskmaster {

    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication between constructors.
     * See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are
     * other ways to avoid duplication among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    private StudentRecordList studentRecordList;

    // TODO: Current session is a placeholder for now
    private Session currentSession = new Session(
            new SessionName("Placeholder session"),
            new SessionDateTime(LocalDateTime.now()),
            StudentRecordListManager.of(students.asUnmodifiableObservableList()));

    public Taskmaster() {}

    /**
     * Creates an Taskmaster using the Students in the {@code toBeCopied}
     */
    public Taskmaster(ReadOnlyTaskmaster toBeCopied) {
        this();
        resetData(toBeCopied);
    }


    /* List Overwrite Operations */

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
        currentSession = new Session(
                new SessionName("Placeholder session"),
                new SessionDateTime(LocalDateTime.now()),
                StudentRecordListManager.of(newData.getStudentList()));
    }

    /* Student-Level Operations */

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
     * Marks the attendance of a {@code target} student with
     * {@code attendanceType} in the current session.
     */
    public void markStudent(Student target, AttendanceType attendanceType) {
        assert target != null;
        assert attendanceType != null;
        currentSession.markStudentAttendance(target.getNusnetId(), attendanceType);
    }

    /**
     * Marks the attendance of a student given the {@code nusnetId}
     * with {@code attendanceType} in the current session.
     */
    public void markStudentWithNusnetId(NusnetId nusnetId, AttendanceType attendanceType) {
        assert nusnetId != null;
        assert attendanceType != null;
        studentRecordList.markStudentAttendance(nusnetId, attendanceType);
    }

    /* Util Methods */

    @Override
    public String toString() {
        // TODO: refine later
        return students.asUnmodifiableObservableList().size() + " students";
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    /**
     * Returns an unmodifiable view of the {@code StudentRecordList} of the current session.
     */
    public ObservableList<StudentRecord> getStudentRecordList() {
        return currentSession.getStudentRecords();
    }

    /**
     * Sets the {@code AttendanceType} of all {@code StudentRecords} to NO_RECORD in the current session.
     */
    public void clearAttendance() {
        currentSession.clearAttendance();
    }

    /**
     * Updates the {@code StudentRecordList} of the current session with the data in {@code studentRecords}.
     * @throws StudentNotFoundException
     */
    public void updateStudentRecords(List<StudentRecord> studentRecords) throws StudentNotFoundException {
        currentSession.updateStudentRecords(studentRecords);
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
