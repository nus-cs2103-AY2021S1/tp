package seedu.jarvis.model;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.mission.Mission;
import seedu.jarvis.model.quest.Quest;
import seedu.jarvis.model.student.Student;
import seedu.jarvis.model.task.Task;

/**
 * Unmodifiable view of an jarvis book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the missions list.
     * This list will not contain any duplicate missions.
     */
    ObservableList<Mission> getMissionList();

    /**
     * Returns an unmodifiable view of the quest list.
     * This list will not contain any duplicate quests.
     */
    ObservableList<Quest> getQuestList();

    /**
     * Returns an unmodifiable view of the task list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /** Returns an unmodifiable view of the consultation list.
     * This list will not contain any duplicate consultations.
     */
    ObservableList<Consultation> getConsultationList();

    /**
     * Returns an unmodifiable view of the mastery check list.
     * This list will not contain any duplicate mastery checks.
     */
    ObservableList<MasteryCheck> getMasteryChecksList();

    /**
     * Returns the greeting of the user.
     */
    StringProperty getGreeting();

    /**
     * Returns a string summary of the remaining tasks, upcoming consultations and mastery checks, as well as
     * ungraded missions and quests
     */
    StringProperty getSummary();

}
