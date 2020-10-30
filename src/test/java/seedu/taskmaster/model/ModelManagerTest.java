package seedu.taskmaster.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskmaster.logic.commands.CommandTestUtil.VALID_SCORE_INT;
import static seedu.taskmaster.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.taskmaster.testutil.Assert.assertThrows;
import static seedu.taskmaster.testutil.TypicalStudents.ALICE;
import static seedu.taskmaster.testutil.TypicalStudents.BENSON;
import static seedu.taskmaster.testutil.TypicalStudents.BOB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.commons.core.GuiSettings;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.session.Session;
import seedu.taskmaster.model.session.SessionDateTime;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.model.session.exceptions.NoSessionException;
import seedu.taskmaster.model.session.exceptions.NoSessionSelectedException;
import seedu.taskmaster.model.student.NameContainsKeywordsPredicate;
import seedu.taskmaster.model.student.Student;
import seedu.taskmaster.testutil.TaskmasterBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();
    private ModelManager modelManager2 = new ModelManager(new Taskmaster(), new UserPrefs());

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new Taskmaster(), new Taskmaster(modelManager.getTaskmaster()));
        assertEquals(new UserPrefs(), modelManager2.getUserPrefs());
        assertEquals(new Taskmaster(), new Taskmaster(modelManager2.getTaskmaster()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTaskmasterFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTaskmasterFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setTaskmasterFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTaskmasterFilePath(null));
    }

    @Test
    public void setTaskmasterFilePath_validPath_setsTaskmasterFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setTaskmasterFilePath(path);
        assertEquals(path, modelManager.getTaskmasterFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInStudentList_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInStudentList_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void equals() {
        Taskmaster taskmaster = new TaskmasterBuilder().withStudent(ALICE).withStudent(BENSON).build();
        Taskmaster differentTaskmaster = new Taskmaster();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(taskmaster, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(taskmaster, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different taskmaster -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTaskmaster, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(taskmaster, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTaskmasterFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(taskmaster, differentUserPrefs)));
    }

    @Test
    void markStudent_success() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        modelManager.changeSession(sName);
        modelManager.markStudent(ALICE, AttendanceType.PRESENT);
        assertTrue(s.getStudentRecords().toString().equals("[e0123456|PRESENT|Class Participation Score: 0]"));
        modelManager.markStudent(ALICE, AttendanceType.ABSENT);
        assertTrue(s.getStudentRecords().toString().equals("[e0123456|ABSENT|Class Participation Score: 0]"));
    }

    @Test
    void markStudent_noSession_failure() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        assertThrows(NoSessionException.class, () -> modelManager.markStudent(ALICE, AttendanceType.PRESENT));
    }

    @Test
    void markStudent_sessionNotSelected_failure() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        assertThrows(NoSessionSelectedException.class, () -> modelManager.markStudent(ALICE, AttendanceType.PRESENT));
    }

    @Test
    void markStudentWithNusnetId() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        modelManager.changeSession(sName);
        modelManager.markStudentWithNusnetId(ALICE.getNusnetId(), AttendanceType.PRESENT);
        assertTrue(s.getStudentRecords().toString().equals("[e0123456|PRESENT|Class Participation Score: 0]"));
        modelManager.markStudentWithNusnetId(ALICE.getNusnetId(), AttendanceType.ABSENT);
        assertTrue(s.getStudentRecords().toString().equals("[e0123456|ABSENT|Class Participation Score: 0]"));
    }

    @Test
    void markStudentWithNusnetId_noSession_failure() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        assertThrows(NoSessionException.class, () -> modelManager
                .markStudentWithNusnetId(ALICE.getNusnetId(), AttendanceType.PRESENT));
    }

    @Test
    void markStudentWithNusnetId_sessionNotSelected_failure() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        assertThrows(NoSessionSelectedException.class, () -> modelManager
                .markStudentWithNusnetId(ALICE.getNusnetId(), AttendanceType.PRESENT));
    }

    @Test
    void scoreStudent() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        modelManager.changeSession(sName);
        modelManager.scoreStudent(ALICE, VALID_SCORE_INT);
        assertTrue(s.getStudentRecords().toString().equals("[e0123456|NO_RECORD|Class Participation Score: 0]"));
        modelManager.scoreStudent(ALICE, (VALID_SCORE_INT + 2));
        assertTrue(s.getStudentRecords().toString().equals("[e0123456|NO_RECORD|Class Participation Score: 2]"));
    }

    @Test
    void scoreStudent_noSession_failure() {
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        assertThrows(NoSessionException.class, () -> modelManager.scoreStudent(ALICE, VALID_SCORE_INT));
    }

    @Test
    void scoreStudent_sessionNotSelected_failure() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        assertThrows(NoSessionSelectedException.class, () -> modelManager.scoreStudent(ALICE, VALID_SCORE_INT));
    }

    @Test
    void scoreStudentWithNusnetId() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        modelManager.changeSession(sName);
        modelManager.scoreStudentWithNusnetId(ALICE.getNusnetId(), VALID_SCORE_INT);
        assertTrue(s.getStudentRecords().toString().equals("[e0123456|NO_RECORD|Class Participation Score: 0]"));
        modelManager.scoreStudentWithNusnetId(ALICE.getNusnetId(), (VALID_SCORE_INT + 1));
        assertTrue(s.getStudentRecords().toString().equals("[e0123456|NO_RECORD|Class Participation Score: 1]"));
    }

    @Test
    void scoreStudentWithNusnetId_noSession_failure() {
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        assertThrows(NoSessionException.class, () -> modelManager
                .scoreStudentWithNusnetId(ALICE.getNusnetId(), VALID_SCORE_INT));
    }

    @Test
    void scoreStudentWithNusnetId_sessionNotSelected_failure() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        assertThrows(NoSessionSelectedException.class, () -> modelManager
                .scoreStudentWithNusnetId(ALICE.getNusnetId(), VALID_SCORE_INT));
    }

    @Test
    void scoreAllStudents() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        stds.add(BOB);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        modelManager.changeSession(sName);
        modelManager.scoreAllStudents(stds, VALID_SCORE_INT);

        assertTrue(s.getStudentRecords().toString()
                .equals("[e0123456|NO_RECORD|Class Participation Score: 0,"
                        + " e0456789|NO_RECORD|Class Participation Score: 0]"));
        modelManager.scoreAllStudents(stds, (VALID_SCORE_INT + 2));
        assertTrue(s.getStudentRecords().toString()
                .equals("[e0123456|NO_RECORD|Class Participation Score: 2,"
                + " e0456789|NO_RECORD|Class Participation Score: 2]"));
    }

    @Test
    void scoreAllStudents_noSession_failure() {
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        stds.add(BOB);
        assertThrows(NoSessionException.class, () -> modelManager.scoreAllStudents(stds, VALID_SCORE_INT));
    }

    @Test
    void scoreAllStudents_sessionNotSelected_failure() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        stds.add(BOB);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        assertThrows(NoSessionSelectedException.class, () -> modelManager.scoreAllStudents(stds, VALID_SCORE_INT));
    }

    @Test
    void markAllStudents() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        stds.add(BOB);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        modelManager.changeSession(sName);
        modelManager.markAllStudents(stds, AttendanceType.PRESENT);

        assertTrue(s.getStudentRecords().toString()
                .equals("[e0123456|PRESENT|Class Participation Score: 0,"
                        + " e0456789|PRESENT|Class Participation Score: 0]"));
        modelManager.markAllStudents(stds, AttendanceType.ABSENT);
        assertTrue(s.getStudentRecords().toString()
                .equals("[e0123456|ABSENT|Class Participation Score: 0,"
                        + " e0456789|ABSENT|Class Participation Score: 0]"));
    }

    @Test
    void markAllStudents_noSession_failure() {
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        stds.add(BOB);
        assertThrows(NoSessionException.class, () -> modelManager.markAllStudents(stds, AttendanceType.PRESENT));
    }

    @Test
    void markAllStudents_sessionNotSelected_failure() {
        SessionName sName = new SessionName("Test Session");
        ArrayList<Student> stds = new ArrayList<Student>();
        stds.add(ALICE);
        stds.add(BOB);
        Session s = new Session(sName,
                new SessionDateTime(LocalDateTime.now()),
                stds);
        modelManager.addSession(s);
        assertThrows(NoSessionSelectedException.class, () -> modelManager
                .markAllStudents(stds, AttendanceType.PRESENT));
    }
}
