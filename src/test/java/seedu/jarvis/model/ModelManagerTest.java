package seedu.jarvis.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalMissions.MUSICAL_NOTES;
import static seedu.jarvis.testutil.TypicalMissions.STREAMS;
import static seedu.jarvis.testutil.TypicalQuests.COLORFUL_CARPETS;
import static seedu.jarvis.testutil.TypicalStudents.ALICE;
import static seedu.jarvis.testutil.TypicalStudents.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.jarvis.commons.core.GuiSettings;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.login.Username;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.mission.Mission;
import seedu.jarvis.model.quest.Quest;
import seedu.jarvis.model.student.NameContainsKeywordsPredicate;
import seedu.jarvis.model.summary.Summary;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Todo;
import seedu.jarvis.testutil.AddressBookBuilder;
import seedu.jarvis.testutil.MissionBuilder;
import seedu.jarvis.testutil.QuestBuilder;
import seedu.jarvis.testutil.TypicalAddressBook;
import seedu.jarvis.testutil.TypicalManagers;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    //=========== UserPrefs ==================================================================================

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("jarvis/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/jarvis/book/file/path"));
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
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("jarvis/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    //=========== UserLogin ==================================================================================

    @Test
    public void setUserLogin_nullUserLogin_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserLogin(null));
    }

    @Test
    public void setUserLogin_validUserLogin_copiesUserLogin() {
        UserLogin userLogin = new UserLogin();
        userLogin.setUsername(new Username("nusstu\\e1234567"));
        userLogin.setPassword("testCaseOld132");
        modelManager.setUserLogin(userLogin);
        assertEquals(userLogin, modelManager.getUserLogin());

        // Modifying userLogin should not modify modelManager's userLogin
        UserLogin oldUserLogin = new UserLogin(userLogin);
        userLogin.setPassword("testCaseNew132");
        assertEquals(oldUserLogin, modelManager.getUserLogin());
    }

    //=========== AddressBook ================================================================================

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPersons_addressBookPopulated_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasStudents());
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = TypicalManagers.getUserPrefs();
        UserLogin userLogin = TypicalManagers.getUserLogin();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs, userLogin);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs, userLogin);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs, userLogin)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs, userLogin)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs, userLogin)));
    }

    //=========== Greeting ==================================================================================

    @Test
    public void hasGreeting() {
        // Have not set greeting
        assertFalse(modelManager.hasGreeting());

        // After setting greeting
        modelManager.setGreeting("Alex Yeoh");
        assertTrue(modelManager.getGreeting().getValue().equals("Welcome, Alex Yeoh!"));
        assertTrue(modelManager.hasGreeting());
    }

    //=========== Missions ===================================================================================

    @Test
    public void isMissionInList() {
        // Mission not in list
        assertFalse(modelManager.isMissionInList("Streams"));

        // Mission in list
        modelManager.addMission(STREAMS);
        assertTrue(modelManager.isMissionInList("Streams"));
    }

    @Test
    public void updateMission_returnsTrue() {
        modelManager.addMission(MUSICAL_NOTES);
        modelManager.updateMission("Musical Notes");
        Mission updatedMission = new MissionBuilder(MUSICAL_NOTES).withIsGraded(false).build();
        assertTrue(modelManager.getFilteredMissionList().contains(updatedMission));
    }

    //=========== Quests ===================================================================================

    @Test
    public void isQuestInList() {
        // Quest not in list
        assertFalse(modelManager.isQuestInList("Colorful Carpets"));

        // Quest in list
        modelManager.addQuest(COLORFUL_CARPETS);
        assertTrue(modelManager.isQuestInList("Colorful Carpets"));
    }

    @Test
    public void updateQuest_returnsTrue() {
        modelManager.addQuest(COLORFUL_CARPETS);
        modelManager.updateQuest("Colorful Carpets");
        Quest updatedQuest = new QuestBuilder(COLORFUL_CARPETS).withIsGraded(false).build();
        assertTrue(modelManager.getFilteredQuestList().contains(updatedQuest));
    }

    //============================== Task ====================================================================

    @Test
    public void hasTodo_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasTodo(null));
    }

    @Test
    public void addTodo_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addTodo(null));
    }

    @Test
    public void isTodoInAddressBook() {
        Todo todo = new Todo("TestTodo");
        assertFalse(modelManager.hasTodo(todo));

        modelManager.addTodo(todo);
        assertTrue(modelManager.hasTodo(todo));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasEvent(null));
    }

    @Test
    public void addEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addEvent(null));
    }

    @Test
    public void isEventInAddressBook() {
        Event event = new Event("TestEvent", LocalDateTime.now());
        assertFalse(modelManager.hasEvent(event));

        modelManager.addEvent(event);
        assertTrue(modelManager.hasEvent(event));
    }

    @Test
    public void hasDeadline_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasDeadline(null));
    }

    @Test
    public void addDeadline_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addDeadline(null));
    }

    @Test
    public void isDeadlineInAddressBook() {
        Deadline deadline = new Deadline("TestDeadline", LocalDateTime.now());
        assertFalse(modelManager.hasDeadline(deadline));

        modelManager.addDeadline(deadline);
        assertTrue(modelManager.hasDeadline(deadline));
    }

    //========================= Consultations ================================================================

    @Test
    public void hasConsultation_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasConsultation(null));
    }

    @Test
    public void addConsultation_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addConsultation(null));
    }

    @Test
    public void isConsultationInAddressBook() {
        Consultation consultation = new Consultation("Adam", LocalDateTime.now());
        assertFalse(modelManager.hasConsultation(consultation));

        modelManager.addConsultation(consultation);
        assertTrue(modelManager.hasConsultation(consultation));
    }

    @Test
    public void deleteConsultation() {
        Consultation consultation = new Consultation("Eve", LocalDateTime.now());
        modelManager.addConsultation(consultation);
        assertTrue(modelManager.hasConsultation(consultation));
        modelManager.deleteConsultation(consultation);
        assertFalse(modelManager.hasConsultation(consultation));
    }

    //========================= Mastery Checks ================================================================

    @Test
    public void hasMasteryCheck_nullMasteryCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasMasteryCheck(null));
    }

    @Test
    public void addMasteryCheck_nullMasteryCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.addMasteryCheck(null));
    }

    @Test
    public void isMasteryCheckInAddressBook() {
        MasteryCheck masteryCheck = new MasteryCheck("Jacob", LocalDateTime.now());
        assertFalse(modelManager.hasMasteryCheck(masteryCheck));

        modelManager.addMasteryCheck(masteryCheck);
        assertTrue(modelManager.hasMasteryCheck(masteryCheck));
    }

    @Test
    public void setMasteryCheck_validMasteryCheck_copiesMasteryCheck() {
        MasteryCheck oldMasteryCheck = new MasteryCheck("Abraham", LocalDateTime.now());
        MasteryCheck newMasteryCheck = new MasteryCheck("Igausus", LocalDateTime.now());
        modelManager.addMasteryCheck(oldMasteryCheck);
        modelManager.setMasteryCheck(oldMasteryCheck, newMasteryCheck);
        assertTrue(modelManager.hasMasteryCheck(newMasteryCheck));
    }

    //========================= Summary Feature ================================================================
    @Test
    public void updateUngradedMissionsSummaryDetail_summaryDetailsAccurate() {
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBookWithAllValues();
        ModelManager typicalModelManager = new ModelManager(addressBook, TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());
        int valueExpected = 3;
        typicalModelManager.updateUngradedMissionsSummaryDetail();
        String summaryDetails = typicalModelManager.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Remaining - ").append(Summary.MESSAGE_MISSIONS).append(": ").append(valueExpected);
        String stringExpected = stringBuilder.toString();
        assertEquals(stringExpected, summaryDetails);
    }

    @Test
    public void updateUngradedQuestsSummaryDetail_summaryDetailsAccurate() {
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBookWithAllValues();
        ModelManager typicalModelManager = new ModelManager(addressBook, TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());

        int valueExpected = 3;
        typicalModelManager.updateUngradedQuestsSummaryDetail();
        String summaryDetails = typicalModelManager.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Remaining - ").append(Summary.MESSAGE_QUESTS).append(": ").append(valueExpected);
        String stringExpected = stringBuilder.toString();
        assertEquals(stringExpected, summaryDetails);
    }

    @Test
    public void updateUpcomingConsultationsSummaryDetail_summaryDetailsAccurate() {
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBookWithAllValues();
        ModelManager typicalModelManager = new ModelManager(addressBook, TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());

        int valueExpected = 3;
        typicalModelManager.updateUpcomingConsultationsSummaryDetail();
        String summaryDetails = typicalModelManager.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Remaining - ").append(Summary.MESSAGE_CONSULTATIONS).append(": ").append(valueExpected);
        String stringExpected = stringBuilder.toString();
        assertEquals(stringExpected, summaryDetails);
    }

    @Test
    public void updateUpcomingMasteryChecksSummaryDetail_summaryDetailsAccurate() {
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBookWithAllValues();
        ModelManager typicalModelManager = new ModelManager(addressBook, TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());

        int valueExpected = 3;
        typicalModelManager.updateUpcomingMasteryChecksSummaryDetail();
        String summaryDetails = typicalModelManager.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Remaining - ").append(Summary.MESSAGE_MASTERY_CHECKS).append(": ").append(valueExpected);
        String stringExpected = stringBuilder.toString();
        assertEquals(stringExpected, summaryDetails);
    }

    @Test
    public void updateOutstandingTasksSummaryDetail_summaryDetailsAccurate() {
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBookWithAllValues();
        ModelManager typicalModelManager = new ModelManager(addressBook, TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());

        int valueExpected = 6;
        typicalModelManager.updateTasksSummaryDetail();
        String summaryDetails = typicalModelManager.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Remaining - ").append(Summary.MESSAGE_TASKS).append(": ").append(valueExpected);
        String stringExpected = stringBuilder.toString();
        assertEquals(stringExpected, summaryDetails);
    }

    @Test
    public void updateAllSummaryDetails_summaryDetailsAccurate() {
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBookWithAllValues();
        ModelManager typicalModelManager = new ModelManager(addressBook, TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());

        int expectedMissions = 3;
        int expectedQuests = 3;
        int expectedConsultations = 3;
        int expectedMasteryChecks = 3;
        int expectedTasks = 6;
        typicalModelManager.updateAllSummaryDetails();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_REMAINING).append(" - ");
        stringBuilder.append(Summary.MESSAGE_MISSIONS).append(": ").append(expectedMissions)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_QUESTS).append(": ").append(expectedQuests)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_CONSULTATIONS).append(": ").append(expectedConsultations)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_MASTERY_CHECKS).append(": ").append(expectedMasteryChecks)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_TASKS).append(": ").append(expectedTasks);
        String summaryDetails = typicalModelManager.getSummary().get();
        String stringExpected = stringBuilder.toString();
        assertEquals(stringExpected, summaryDetails);
    }

    @Test
    public void updateUngradedMissionsAndQuestsDetails_summaryDetailsAccurate() {
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBookWithAllValues();
        ModelManager typicalModelManager = new ModelManager(addressBook, TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());

        int expectedMissions = 3;
        int expectedQuests = 3;
        typicalModelManager.updateUngradedMissionsSummaryDetail();
        typicalModelManager.updateUngradedQuestsSummaryDetail();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_REMAINING).append(" - ");
        stringBuilder.append(Summary.MESSAGE_MISSIONS).append(": ").append(expectedMissions)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_QUESTS).append(": ").append(expectedQuests);
        // To ensure that there is no comma at the end of the generated string.
        String summaryDetails = typicalModelManager.getSummary().get();
        String stringExpected = stringBuilder.toString();
        assertEquals(stringExpected, summaryDetails);
    }

    @Test
    public void updateUpcomingConsultationsMcDetails_summaryDetailsAccurate() {
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBookWithAllValues();
        ModelManager typicalModelManager = new ModelManager(addressBook, TypicalManagers.getUserPrefs(),
                TypicalManagers.getUserLogin());

        int expectedConsultations = 3;
        int expectedMasteryChecks = 3;
        typicalModelManager.updateUpcomingConsultationsSummaryDetail();
        typicalModelManager.updateUpcomingMasteryChecksSummaryDetail();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_REMAINING).append(" - ");
        stringBuilder.append(Summary.MESSAGE_CONSULTATIONS).append(": ").append(expectedConsultations)
                .append(", ");
        stringBuilder.append(Summary.MESSAGE_MASTERY_CHECKS).append(": ").append(expectedMasteryChecks);
        String summaryDetails = typicalModelManager.getSummary().get();
        String stringExpected = stringBuilder.toString();
        assertEquals(stringExpected, summaryDetails);
    }
}
