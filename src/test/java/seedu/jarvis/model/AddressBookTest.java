package seedu.jarvis.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalMissions.MUSICAL_NOTES;
import static seedu.jarvis.testutil.TypicalMissions.STREAMS;
import static seedu.jarvis.testutil.TypicalQuests.COLORFUL_CARPETS;
import static seedu.jarvis.testutil.TypicalStudents.ALICE;
import static seedu.jarvis.testutil.TypicalStudents.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.mission.Mission;
import seedu.jarvis.model.quest.Quest;
import seedu.jarvis.model.student.Student;
import seedu.jarvis.model.student.exceptions.DuplicateStudentException;
import seedu.jarvis.model.summary.Summary;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Task;
import seedu.jarvis.model.task.Todo;
import seedu.jarvis.testutil.MissionBuilder;
import seedu.jarvis.testutil.QuestBuilder;
import seedu.jarvis.testutil.StudentBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicatePersonException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStudent(null));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTask(null));
    }

    @Test
    public void hasGreeting() {
        // Have not set greeting
        assertFalse(addressBook.hasGreeting());

        // After setting greeting
        addressBook.setGreeting("Alex Yeoh");
        assertTrue(addressBook.getGreeting().getValue().equals("Welcome, Alex Yeoh!"));
        assertTrue(addressBook.hasGreeting());
    }

    @Test
    public void hasStudent_studentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        assertTrue(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).build();
        assertTrue(addressBook.hasStudent(editedAlice));
    }

    @Test
    public void hasStudents_addressBookPopulated_returnsTrue() {
        addressBook.addStudent(ALICE);
        assertTrue(addressBook.hasStudents());
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStudentList().remove(0));
    }

    @Test
    public void isMissionInList() {
        // Mission not in list
        assertFalse(addressBook.isMissionInList("Streams"));

        // Mission in list
        addressBook.addMission(STREAMS);
        assertTrue(addressBook.isMissionInList("Streams"));
    }

    @Test
    public void updateMission_returnsTrue() {
        addressBook.addMission(MUSICAL_NOTES);
        addressBook.updateMission("Musical Notes");
        Mission updatedMission = new MissionBuilder(MUSICAL_NOTES).withIsGraded(false).build();
        assertTrue(addressBook.getMissionList().contains(updatedMission));
    }

    @Test
    void isQuestInList() {
        // Quest not in list
        assertFalse(addressBook.isQuestInList("Colorful Carpets"));

        // Quest in list
        addressBook.addQuest(COLORFUL_CARPETS);
        assertTrue(addressBook.isQuestInList("Colorful Carpets"));
    }

    @Test
    public void updateQuest_returnsTrue() {
        addressBook.addQuest(COLORFUL_CARPETS);
        addressBook.updateQuest("Colorful Carpets");
        Quest updatedQuest = new QuestBuilder(COLORFUL_CARPETS).withIsGraded(false).build();
        assertTrue(addressBook.getQuestList().contains(updatedQuest));
    }

    //============================== Task ====================================================================

    @Test
    public void hasTodo_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTodo(null));
    }

    @Test
    public void addTodo_nullTodo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addTodo(null));
    }

    @Test
    public void isTodoInAddressBook() {
        Todo todo = new Todo("TestTodo");
        assertFalse(addressBook.hasTodo(todo));

        addressBook.addTodo(todo);
        assertTrue(addressBook.hasTodo(todo));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEvent(null));
    }

    @Test
    public void addEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addEvent(null));
    }

    @Test
    public void isEventInAddressBook() {
        Event event = new Event("TestEvent", LocalDateTime.now());
        assertFalse(addressBook.hasEvent(event));

        addressBook.addEvent(event);
        assertTrue(addressBook.hasEvent(event));
    }

    @Test
    public void hasDeadline_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasDeadline(null));
    }

    @Test
    public void addDeadline_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addDeadline(null));
    }

    @Test
    public void isDeadlineInAddressBook() {
        Deadline deadline = new Deadline("TestDeadline", LocalDateTime.now());
        assertFalse(addressBook.hasDeadline(deadline));

        addressBook.addDeadline(deadline);
        assertTrue(addressBook.hasDeadline(deadline));
    }

    //========================= Consultations ================================================================

    @Test
    public void hasConsultation_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasConsultation(null));
    }

    @Test
    public void addConsultation_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addConsultation(null));
    }

    @Test
    public void isConsultationInAddressBook() {
        Consultation consultation = new Consultation("Adam", LocalDateTime.now());
        assertFalse(addressBook.hasConsultation(consultation));

        addressBook.addConsultation(consultation);
        assertTrue(addressBook.hasConsultation(consultation));
    }

    @Test
    public void deleteConsultation() {
        Consultation consultation = new Consultation("Eve", LocalDateTime.now());
        addressBook.addConsultation(consultation);
        assertTrue(addressBook.hasConsultation(consultation));
        addressBook.removeConsultation(consultation);
        assertFalse(addressBook.hasConsultation(consultation));
    }

    //========================= Mastery Checks ================================================================

    @Test
    public void hasMasteryCheck_nullMasteryCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasMasteryCheck(null));
    }

    @Test
    public void addMasteryCheck_nullMasteryCheck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.addMasteryCheck(null));
    }

    @Test
    public void isMasteryCheckInAddressBook() {
        MasteryCheck masteryCheck = new MasteryCheck("Jacob", LocalDateTime.now());
        assertFalse(addressBook.hasMasteryCheck(masteryCheck));

        addressBook.addMasteryCheck(masteryCheck);
        assertTrue(addressBook.hasMasteryCheck(masteryCheck));
    }

    @Test
    public void setMasteryCheck_validMasteryCheck_copiesMasteryCheck() {
        MasteryCheck oldMasteryCheck = new MasteryCheck("Abraham", LocalDateTime.now());
        MasteryCheck newMasteryCheck = new MasteryCheck("Igausus", LocalDateTime.now());
        addressBook.addMasteryCheck(oldMasteryCheck);
        addressBook.setMasteryCheck(oldMasteryCheck, newMasteryCheck);
        assertTrue(addressBook.hasMasteryCheck(newMasteryCheck));
    }

    @Test
    public void setNumUngradedMissions_negativeValue_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> addressBook.setNumUngradedMissions(-2));
    }

    @Test
    public void setNumUngradedQuests_negativeValue_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> addressBook.setNumUngradedQuests(-2));
    }
    @Test
    public void setNumUpcomingConsultations_negativeValue_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> addressBook.setNumUpcomingConsultations(-2));
    }

    @Test
    public void setNumUpcomingMasteryChecks_negativeValue_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> addressBook.setNumUpcomingMasteryChecks(-2));
    }
    @Test
    public void setNumTasks_negativeValue_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> addressBook.setNumTasks(-2));
    }


    @Test
    public void setNumUngradedMissions_twenty_success() {
        int valueToSet = 20;
        addressBook.setNumUngradedMissions(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_MISSIONS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertTrue(summaryDetails.contains(stringToBeContain));
    }

    @Test
    public void setNumUngradedQuests_twenty_success() {
        int valueToSet = 20;
        addressBook.setNumUngradedQuests(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_QUESTS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertTrue(summaryDetails.contains(stringToBeContain));
    }

    @Test
    public void setNumUpcomingConsultations_twenty_success() {
        int valueToSet = 20;
        addressBook.setNumUpcomingConsultations(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_CONSULTATIONS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertTrue(summaryDetails.contains(stringToBeContain));
    }

    @Test
    public void setNumUpcomingMasteryChecks_twenty_success() {
        int valueToSet = 20;
        addressBook.setNumUpcomingMasteryChecks(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_MASTERY_CHECKS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertTrue(summaryDetails.contains(stringToBeContain));
    }

    @Test
    public void setNumTasks_twenty_success() {
        int valueToSet = 20;
        addressBook.setNumTasks(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_TASKS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertTrue(summaryDetails.contains(stringToBeContain));
    }

    @Test
    public void setNumUngradedMissions_zero_notIncludedInSummaryString() {
        int valueToSet = 0;
        addressBook.setNumUngradedMissions(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_MISSIONS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertFalse(summaryDetails.contains(stringToBeContain));
    }

    @Test
    public void setNumUngradedQuests_zero_notIncludedInSummaryString() {
        int valueToSet = 0;
        addressBook.setNumUngradedQuests(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_QUESTS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertFalse(summaryDetails.contains(stringToBeContain));
    }

    @Test
    public void setNumUpcomingConsultations_zero_notIncludedInSummaryString() {
        int valueToSet = 0;
        addressBook.setNumUpcomingConsultations(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_CONSULTATIONS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertFalse(summaryDetails.contains(stringToBeContain));
    }

    @Test
    public void setNumUpcomingMasteryChecks_zero_notIncludedInSummaryString() {
        int valueToSet = 0;
        addressBook.setNumUpcomingMasteryChecks(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_MASTERY_CHECKS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertFalse(summaryDetails.contains(stringToBeContain));
    }

    @Test
    public void setNumTasks_zero_notIncludedInSummaryString() {
        int valueToSet = 0;
        addressBook.setNumTasks(valueToSet);
        String summaryDetails = addressBook.getSummary().get();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Summary.MESSAGE_TASKS).append(": ").append(valueToSet);
        String stringToBeContain = stringBuilder.toString();
        assertFalse(summaryDetails.contains(stringToBeContain));
    }


    /**
     * A stub ReadOnlyAddressBook whose students list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        AddressBookStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Mission> getMissionList() {
            return null;
        }

        @Override
        public ObservableList<Quest> getQuestList() {
            return null;
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return null;
        }

        @Override
        public ObservableList<Consultation> getConsultationList() {
            return null;
        }

        @Override
        public ObservableList<MasteryCheck> getMasteryChecksList() {
            return null;
        }

        @Override
        public StringProperty getGreeting() {
            return null;
        }

        @Override
        public StringProperty getSummary() {
            return null;
        }
    }

}
