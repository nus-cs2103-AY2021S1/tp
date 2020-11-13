package seedu.jarvis.model;

import java.beans.PropertyChangeListener;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.jarvis.commons.core.GuiSettings;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.mission.Mission;
import seedu.jarvis.model.quest.Quest;
import seedu.jarvis.model.student.Student;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Task;
import seedu.jarvis.model.task.Todo;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true to show all students*/
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /** {@code Predicate} that evaluate to true if mission is ongoing */
    Predicate<Mission> PREDICATE_SHOW_ALL_MISSIONS = mission -> mission.getDeadline().contains("Due");

    /** {@code Predicate} that always evaluate to true if it is a consultation*/
    Predicate<Consultation> PREDICATE_SHOW_ALL_CONSULTATIONS = unused -> true;

    /** {@code Predicate} that evaluates to true when the consultation has taken place in the past. */
    Predicate<Consultation> PREDICATE_SHOW_PAST_CONSULTATIONS = unused ->
            unused.getDateAndTime().isBefore(LocalDateTime.now());

    /** {@code Predicate} that evaluates to true when the consultation will take place in the future. */
    Predicate<Consultation> PREDICATE_SHOW_UPCOMING_CONSULTATIONS = unused ->
            unused.getDateAndTime().isAfter(LocalDateTime.now());

    /** {@code Predicate} that always evaluate to true if it is a mastery check */
    Predicate<MasteryCheck> PREDICATE_SHOW_ALL_MASTERY_CHECKS = unused -> true;

    /** {@code Predicate} that evaluates to true when the mastery check has taken place in the past. */
    Predicate<MasteryCheck> PREDICATE_SHOW_PAST_MASTERY_CHECKS = unused ->
            unused.getDateAndTime().isBefore(LocalDateTime.now());

    /** {@code Predicate} that evaluates to true when the mastery check will take place in the future. */
    Predicate<MasteryCheck> PREDICATE_SHOW_UPCOMING_MASTERY_CHECKS = unused ->
            unused.getDateAndTime().isAfter(LocalDateTime.now());

    /** {@code Predicate} that always evaluate to true if quest is ongoing */
    Predicate<Quest> PREDICATE_SHOW_ALL_QUESTS = quest -> quest.getDeadline().contains("Due");

    /** {@code Predicate} that always evaluate to true to show all tasks */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /** {@code Predicate} that evaluates to true when task is a Todo */
    Predicate<Task> PREDICATE_SHOW_ALL_TODOS = task -> Task.getType(task).equals("T");

    /** {@code Predicate} that evaluates to true when task is an Event */
    Predicate<Task> PREDICATE_SHOW_ALL_EVENTS = task -> Task.getType(task).equals("E");

    /** {@code Predicate} that evaluates to true when task is a Deadline */
    Predicate<Task> PREDICATE_SHOW_ALL_DEADLINES = task -> Task.getType(task).equals("D");

    //=========== UserPrefs ==================================================================================

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
     * Returns the user prefs' jarvis book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' jarvis book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    //=========== UserLogin ==================================================================================

    /**
     * Returns user login details.
     * @return a UserLogin object
     */
    UserLogin getUserLogin();

    /**
     * Sets user login with new details.
     * @param userLogin updated user login details to be set
     */
    void setUserLogin(ReadOnlyUserLogin userLogin);

    /**
     * Returns whether the login.json has username specified.
     * @return a boolean
     */
    boolean hasUsername();

    /**
     * Returns whether the password.json has password specified
     * @return a boolean
     */
    boolean hasPassword();

    //=========== Greeting ===================================================================================
    void setGreeting(String greeting);

    boolean hasGreeting();

    StringProperty getGreeting();
    //=========== AddressBook ================================================================================

    /**
     * Replaces jarvis book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    //================ Students ==============================================================================

    /**
     * Returns true if a student with the same identity as {@code student} exists in the jarvis book.
     */
    boolean hasPerson(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the jarvis book.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in the jarvis book.
     */
    void setPerson(Student target, Student editedStudent);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Student> predicate);

    /**
     * Returns true if students exist.
     */
    boolean hasStudents();

    /**
     * Deletes the given student.
     * The student must exist in the jarvis book.
     */
    void deletePerson(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the jarvis book.
     */
    void addPerson(Student student);

    /**
     * Adds the student list.
     * @param students
     */
    void setStudents(List<Student> students);

    //============================== Task ====================================================================

    /**
     * Adds the given todo.
     * {@code todo} must not already exist in the jarvis book.
     */
    void addTodo(Todo todo);

    /**
     * Adds the given event.
     * {@code event} must not already exist in the jarvis book.
     */
    void addEvent(Event event);

    /**
     * Adds the given deadline.
     * {@code deadline} must not already exist in the jarvis book.
     */
    void addDeadline(Deadline deadline);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the jarvis book.
     */
    void addTask(Task task);

    /**
     * Returns true if a todo with the same identity as {@code todo} exists in the jarvis book.
     */
    boolean hasTodo(Todo todo);

    /**
     * Returns true if an event with the same identity as {@code event} exists in the jarvis book.
     */
    boolean hasEvent(Event event);

    /**
     * Returns true if a deadline with the same identity as {@code deadline} exists in the jarvis book.
     */
    boolean hasDeadline(Deadline deadline);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the jarvis book.
     */
    boolean hasTask(Task task);

    /** Returns an unmodifiable view of the filtered mission list. */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Deletes the given task.
     * The task must exist in the jarvis book.
     */
    void deleteTask(Task target);

    //================== Consultations  ======================================================

    /**
     * Adds the given consultation.
     * {@code Consultation} must not already exist in the jarvis book.
     */
    void addConsultation(Consultation consultation);

    /**
     * Deletes the given consultation.
     */
    void deleteConsultation(Consultation consultation);

    void setConsultations(List<Consultation> consultations);

    /**
     * Returns an unmodifiable view of the filtered consultation list.
     */
    ObservableList<Consultation> getFilteredConsultationsList();

    void updateConsultationsList(Predicate<Consultation> predicate);

    // boolean isConsultationInList(String identifier);

    boolean hasConsultation(Consultation toAddConsultation);

    /**
     * Updates the filter of the filtered consultation list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredConsultationList(Predicate<Consultation> predicate);

    //================== Mastery Checks  ======================================================

    void addMasteryCheck(MasteryCheck masteryCheck);

    void setMasteryChecks(List<MasteryCheck> masteryChecks);

    ObservableList<MasteryCheck> getFilteredMasteryChecksList();

    void updateMasteryChecksList(Predicate<MasteryCheck> predicate);

    // boolean isMasteryCheckInList(String identifier);

    boolean hasMasteryCheck(MasteryCheck toAddMasteryCheck);

    /**
     * Updates the filter of the filtered mastery check list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredMasteryCheckList(Predicate<MasteryCheck> predicate);

    /**
     * Replaces the given mastery check {@code target} with {@code editedMasteryCheck}.
     * {@code target} must exist in the jarvis book.
     */
    void setMasteryCheck(MasteryCheck target, MasteryCheck editedMasteryCheck);

    /**
     * Deletes the given mastery check.
     */
    void deleteMasteryCheck(MasteryCheck masteryCheck);

    //=========== Filtered Mission List Accessors =============================================================

    /**
     * Updates the filter of the filtered mission list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateMissionsList(Predicate<Mission> predicate);

    /** Returns an unmodifiable view of the filtered mission list. */
    ObservableList<Mission> getFilteredMissionList();

    //=========== Missions ===================================================================================

    /**
     * Adds the given mission.
     * @param mission The mission to be added
     */
    void addMission(Mission mission);

    /**
     * Sets the entire list of missions.
     * @param missions
     */
    void setMissions(List<Mission> missions);

    boolean isMissionInList(String title);

    boolean updateMission(String name);

    //=========== Quests ===================================================================================

    /**
     * Adds the given quest.
     * @param quest The quest to be added
     */
    void addQuest(Quest quest);

    /**
     * Sets the entire list of quests.
     * @param quests
     */
    void setQuests(List<Quest> quests);

    /** Returns an unmodifiable view of the filtered quest list */
    ObservableList<Quest> getFilteredQuestList();

    /**
     * Updates the filter of the filtered quest list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateQuestsList(Predicate<Quest> predicate);

    boolean isQuestInList(String title);

    boolean updateQuest(String name);

    //=========== EventSupport ===================================================================================
    void addPropertyChangeListener(PropertyChangeListener pcv);

    //=========== Summary ===================================================================================
    void updateAllSummaryDetails();

    void updateUngradedMissionsSummaryDetail();

    void updateUngradedQuestsSummaryDetail();

    void updateUpcomingConsultationsSummaryDetail();

    void updateUpcomingMasteryChecksSummaryDetail();

    void updateTasksSummaryDetail();

    StringProperty getSummary();

}
