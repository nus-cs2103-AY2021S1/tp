package seedu.taskmaster.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import seedu.taskmaster.commons.core.GuiSettings;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluates to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /** {@code Predicate} that always evaluates to true */
    Predicate<Session> PREDICATE_SHOW_ALL_SESSIONS = unused -> true;

    /** {@code Predicate} that always evaluates to true */
    Predicate<StudentRecord> PREDICATE_SHOW_ALL_STUDENT_RECORDS = unused -> true;

    /** {@code Predicate} that shows present students */
    Predicate<StudentRecord> PREDICATE_SHOW_ALL_PRESENT_STUDENT_RECORDS = studentRecord ->
            studentRecord.getAttendanceType().equals(AttendanceType.PRESENT);

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' student list file path.
     */
    Path getTaskmasterFilePath();

    /**
     * Sets the user prefs' student list file path.
     */
    void setTaskmasterFilePath(Path taskmasterFilePath);

    /**
     * Replaces student list data with the data in {@code taskmaster}.
     */
    void setTaskmaster(ReadOnlyTaskmaster taskmaster);

    /** Returns the Taskmaster */
    ReadOnlyTaskmaster getTaskmaster();

    /**
     * Replaces the contents of the session list with {@code sessions}.
     * {@code sessions} must not contain duplicate sessions.
     */
    void setSessions(List<Session> sessions);

    /**
     * Deletes the session with {@code sessionName}.
     * {@code sessionName} must already exist in the session list.
     */
    void deleteSession(SessionName sessionName);

    /**
     * Adds the given session.
     * {@code session} must not already exist in the session list.
     */
    void addSession(Session session);

    /**
     * Changes the currentSession to a Session with that name.
     */
    void changeSession(SessionName sessionName);

    /**
     * Switches TAskmaster to student list view.
     */
    void showStudentList();

    /**
     * Returns true if {@code session} exists in the session list.
     */
    boolean hasSession(Session session);

    /**
     * Returns true if a session with {@code sessionName} exists in the session list.
     */
    boolean hasSession(SessionName sessionName);

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student list.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the student list.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the student list.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the student list.
     * The student identity of {@code editedStudent} must not be the same as another existing student in
     * the student list.
     */
    void setStudent(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered attendance list */
    ObservableList<StudentRecord> getFilteredStudentRecordList();

    /** Returns an unmodifiable view of the filtered session list */
    ObservableList<Session> getFilteredSessionList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered session list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredSessionList(Predicate<Session> predicate);

    /**
     * Updates the filter of the filtered student record list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentRecordList(Predicate<StudentRecord> predicate);

    /**
     * Updates the filter of the filtered student list to show the students with the lowest score.
     */
    void showLowestScoringStudents();

    /**
     * Marks the attendance of the given student record {@code target} with the given {@code attendanceType}.
     * The student must exist in the student list.
     */
    void markStudentRecord(StudentRecord target, AttendanceType attendanceType);

    void markStudentWithNusnetId(NusnetId nusnetId, AttendanceType attendanceType);

    /**
     * Marks the attendances of all {@code StudentRecords} with the given {@code attendanceType}.
     */
    void markAllStudents(AttendanceType attendanceType);

    /**
     * Marks the attendance of the given student {@code target} with the given {@code attendanceType}.
     * The student must exist in the student list.
     */
    void scoreStudent(StudentRecord target, double score);

    void scoreStudentWithNusnetId(NusnetId nusnetId, double score);

    /**
     * Updates the {@code ClassParticipation} of all {@code StudentRecords} which are {@code PRESENT} with the
     * given score.
     */
    void scoreAllStudents(double score);

    /**
     * Updates the filter of the filtered student list to show a random student.
     */
    void showRandomStudent(Random random);

    /**
     * Returns the currently displayed session.
     */
    SimpleObjectProperty<Session> getCurrentSession();
}
