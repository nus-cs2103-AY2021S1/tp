package seedu.taskmaster.model;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.taskmaster.commons.core.GuiSettings;
import seedu.taskmaster.commons.core.LogsCenter;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.model.student.NusnetId;
import seedu.taskmaster.model.student.Student;

/**
 * Represents the in-memory model of the student list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Taskmaster taskmaster;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Session> filteredSessions;

    /**
     * Initializes a ModelManager with the given taskmaster and userPrefs.
     */
    public ModelManager(ReadOnlyTaskmaster taskmaster, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(taskmaster, userPrefs);

        logger.fine("Initializing with student list: " + taskmaster + " and user prefs " + userPrefs);

        this.taskmaster = new Taskmaster(taskmaster);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.taskmaster.getStudentList());
        filteredSessions = new FilteredList<>(this.taskmaster.getSessionList());
    }

    public ModelManager() {
        this(new Taskmaster(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTaskmasterFilePath() {
        return userPrefs.getTaskmasterFilePath();
    }

    @Override
    public void setTaskmasterFilePath(Path taskmasterFilePath) {
        requireNonNull(taskmasterFilePath);
        userPrefs.setTaskmasterFilePath(taskmasterFilePath);
    }

    //=========== Taskmaster ================================================================================

    @Override
    public void setTaskmaster(ReadOnlyTaskmaster taskmaster) {
        this.taskmaster.resetData(taskmaster);
    }

    @Override
    public ReadOnlyTaskmaster getTaskmaster() {
        return taskmaster;
    }

    @Override
    public void setSessions(List<Session> sessions) {
        taskmaster.setSessions(sessions);
    }

    @Override
    public void addSession(Session session) {
        taskmaster.addSession(session);
        updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
    }

    @Override
    public void changeSession(SessionName sessionName) {
        taskmaster.changeSession(sessionName);
    }

    @Override
    public boolean hasSession(Session session) {
        requireNonNull(session);
        return taskmaster.hasSession(session);
    }

    @Override
    public boolean hasSession(SessionName sessionName) {
        requireNonNull(sessionName);
        return taskmaster.hasSession(sessionName);
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return taskmaster.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        taskmaster.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        taskmaster.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        taskmaster.setStudent(target, editedStudent);
    }

    @Override
    public void markStudent(Student target, AttendanceType attendanceType) {
        requireAllNonNull(target, attendanceType);
        taskmaster.markStudent(target, attendanceType);
    }

    @Override
    public void markStudentWithNusnetId(NusnetId nusnetId, AttendanceType attendanceType) {
        requireAllNonNull(nusnetId, attendanceType);
        taskmaster.markStudentWithNusnetId(nusnetId, attendanceType);
    }

    @Override
    public void markAllStudents(List<Student> students, AttendanceType attendanceType) {
        List<NusnetId> nusnetIds = students
                .stream()
                .map(Student::getNusnetId)
                .collect(Collectors.toList());

        taskmaster.markAllStudents(nusnetIds, attendanceType);
    }

    @Override
    public void clearAttendance() {
        taskmaster.clearAttendance();
    }

    @Override
    public void updateStudentRecords(List<StudentRecord> studentRecords) {
        taskmaster.updateStudentRecords(studentRecords);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedTaskmaster}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    /**
     * Returns an unmodifiable view of the list of {@code StudentRecord} backed by the internal list of
     * {@code versionedTaskmaster}
     */
    @Override
    public ObservableList<StudentRecord> getFilteredStudentRecordList() {
        return new FilteredList<>(this.taskmaster.getStudentRecordList());
    }

    @Override
    public ObservableList<Session> getFilteredSessionList() {
        return filteredSessions;
    }

    @Override
    public void updateFilteredSessionList(Predicate<Session> predicate) {
        requireNonNull(predicate);
        filteredSessions.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return taskmaster.equals(other.taskmaster)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
