package seedu.taskmaster.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.record.StudentRecordList;
import seedu.taskmaster.model.record.StudentRecordListManager;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.model.student.UniqueStudentList;
import seedu.taskmaster.model.student.exceptions.StudentNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class Taskmaster implements ReadOnlyTaskmaster {

    private final UniqueStudentList students;
    private StudentRecordList studentRecordList;

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
        studentRecordList = StudentRecordListManager.of(newData.getStudentList());
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
        assert target != null;
        assert attendanceType != null;

        studentRecordList.markStudentAttendance(target.getNusnetId(), attendanceType);
    }

    /**
     * Marks the attendance of a Student given the NUSNET ID
     */
    public void markStudentWithNusnetId(NusnetId nusnetId, AttendanceType attendanceType) {
        assert nusnetId != null;
        assert attendanceType != null;

        studentRecordList.markStudentAttendance(nusnetId, attendanceType);
    }

    //// util methods

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
     * Returns an unmodifiable view of the {@code StudentRecordList} backed by the internal list of the
     * Taskmaster.
     */
    public ObservableList<StudentRecord> getStudentRecordList() {
        return studentRecordList.asUnmodifiableObservableList();
    }

    /**
     * Sets the {@code AttendanceType} of all {@code StudentRecords} to NO_RECORD
     */
    public void clearAttendance() {
        this.studentRecordList.markAllAttendance(
                studentRecordList.asUnmodifiableObservableList().stream()
                        .map(StudentRecord::getNusnetId).collect(Collectors.toList()),
                AttendanceType.NO_RECORD);
    }

    /**
     * Updates the {@code StudentRecordList} with the data in {@code studentRecords}.
     * @throws StudentNotFoundException
     */
    public void updateStudentRecords(List<StudentRecord> studentRecords) throws StudentNotFoundException {
        for (StudentRecord studentRecord: studentRecords) {
            this.studentRecordList.markStudentAttendance(
                    studentRecord.getNusnetId(),
                    studentRecord.getAttendanceType());
        }
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
