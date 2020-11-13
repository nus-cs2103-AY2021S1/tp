package seedu.jarvis.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.consultation.ConsultationList;
import seedu.jarvis.model.greeting.Greeting;
import seedu.jarvis.model.masterycheck.MasteryCheck;
import seedu.jarvis.model.masterycheck.MasteryCheckList;
import seedu.jarvis.model.mission.Mission;
import seedu.jarvis.model.mission.MissionList;
import seedu.jarvis.model.quest.Quest;
import seedu.jarvis.model.quest.QuestList;
import seedu.jarvis.model.student.Student;
import seedu.jarvis.model.student.UniqueStudentsList;
import seedu.jarvis.model.summary.Summary;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Task;
import seedu.jarvis.model.task.Todo;
import seedu.jarvis.model.task.UniqueTasksList;

/**
 * Wraps all data at the jarvis-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private final UniqueStudentsList students;

    private final MissionList missions;

    private final QuestList quests;

    private final ConsultationList consultations;

    private final MasteryCheckList masteryChecks;

    private final UniqueTasksList tasks;

    private Greeting greeting;

    private Summary summary;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentsList();
        missions = new MissionList();
        tasks = new UniqueTasksList();
        quests = new QuestList();
        consultations = new ConsultationList();
        masteryChecks = new MasteryCheckList();
        greeting = new Greeting();
        summary = new Summary();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setPersons(students);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setMissions(newData.getMissionList());
        setQuests(newData.getQuestList());
        setTasks(newData.getTaskList());
        setConsultations(newData.getConsultationList());
        setMasteryChecks(newData.getMasteryChecksList());
        setGreeting(newData.getGreeting().getValue());

    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the jarvis book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the jarvis book.
     * The student must not already exist in the jarvis book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the jarvis book.
     * The student identity of {@code editedStudent} must not be the same as another existing
     * student in the jarvis book.
     */
    public void setPerson(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setPerson(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the jarvis book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// util methods

    /**
     * Returns whether student list has students already.
     * @return a boolean
     */
    public boolean hasStudents() {
        return students.hasStudents();
    }

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && students.equals(((AddressBook) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }

    //======================== Quests =======================================================

    @Override
    public ObservableList<Quest> getQuestList() {
        return this.quests.asObservableList();
    }

    public void addQuest(Quest quest) {
        this.quests.add(quest);
    }

    public void setQuests(List<Quest> quests) {
        this.quests.setQuests(quests);
    }

    public boolean isQuestInList(String name) {
        return this.quests.isQuestInList(name);
    }

    public boolean updateQuest(String name) {
        return this.quests.updateQuest(name);
    }

    //======================== Missions =======================================================

    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    public void setMissions(List<Mission> missions) {
        this.missions.setMissions(missions);
    }

    @Override
    public ObservableList<Mission> getMissionList() {
        return this.missions.asObservableList();
    }

    public boolean isMissionInList(String name) {
        return this.missions.isMissionInList(name);
    }

    public boolean updateMission(String name) {
        return this.missions.updateMission(name);
    }

    //======================== Consultations =======================================================

    public void addConsultation(Consultation consultation) {
        this.consultations.add(consultation);
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations.setConsultations(consultations);
    }

    @Override
    public ObservableList<Consultation> getConsultationList() {
        return this.consultations.asObservableList();
    }

    /**
     * Returns whether consultation is in consultations list already.
     * @return a boolean
     */
    public boolean hasConsultation(Consultation toAddConsultation) {
        requireNonNull(toAddConsultation);
        return consultations.contains(toAddConsultation);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the jarvis book.
     */
    public void removeConsultation(Consultation key) {
        consultations.remove(key);
    }

    //======================== MasteryChecks =======================================================

    public void addMasteryCheck(MasteryCheck masteryCheck) {
        this.masteryChecks.add(masteryCheck);
    }

    public void setMasteryChecks(List<MasteryCheck> masteryChecks) {
        this.masteryChecks.setMasteryChecks(masteryChecks);
    }

    @Override
    public ObservableList<MasteryCheck> getMasteryChecksList() {
        return this.masteryChecks.asObservableList();
    }

    /**
     * Returns whether mastery check is in mastery check list already.
     * @return a boolean
     */
    public boolean hasMasteryCheck(MasteryCheck toAddMasteryCheck) {
        requireNonNull(toAddMasteryCheck);
        return masteryChecks.contains(toAddMasteryCheck);
    }

    /**
     * Replaces the given mastery check {@code target} in the list with {@code editedMasteryCheck}.
     * {@code target} must exist in the jarvis book.
     */
    public void setMasteryCheck(MasteryCheck target, MasteryCheck editedMasteryCheck) {
        requireNonNull(editedMasteryCheck);

        masteryChecks.setMasteryCheck(target, editedMasteryCheck);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the jarvis book.
     */
    public void removeMasteryCheck(MasteryCheck key) {
        masteryChecks.remove(key);
    }

    //======================== Tasks ==========================================================================
    /**
     * Returns true if a todo with the same identity as {@code todo} exists in the jarvis book.
     */
    public boolean hasTodo(Todo todo) {
        requireNonNull(todo);
        return tasks.contains(todo);
    }

    /**
     * Adds a todo to the jarvis book.
     * The todo must not already exist in the jarvis book.
     */
    public void addTodo(Todo todo) {
        this.tasks.add(todo);
    }

    /**
     * Returns true if a event with the same identity as {@code event} exists in the jarvis book.
     */
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return tasks.contains(event);
    }

    /**
     * Adds an event to the jarvis book.
     * The event must not already exist in the jarvis book.
     */
    public void addEvent(Event event) {
        this.tasks.add(event);
    }

    /**
     * Returns true if a deadline with the same identity as {@code deadline} exists in the jarvis book.
     */
    public boolean hasDeadline(Deadline deadline) {
        requireNonNull(deadline);
        return tasks.contains(deadline);
    }

    /**
     * Adds a deadline to the jarvis book.
     * The deadline must not already exist in the jarvis book.
     */
    public void addDeadline(Deadline deadline) {
        this.tasks.add(deadline);
    }

    /**
     * Returns true if a task with the same identity as {@code task} exists in the jarvis book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the jarvis book.
     * The task must not already exist in the jarvis book.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the jarvis book.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return this.tasks.asObservableList();
    }

    public StringProperty getGreeting() {
        return greeting.getGreeting();
    }

    public void setGreeting(String greeting) {
        this.greeting.setGreeting(greeting);
    }

    /**
     * Checks whether the name of the user exists.
     * @return true if it exists
     */
    public boolean hasGreeting() {
        return !greeting.isEmpty();
    }

    public void setNumUngradedMissions(int numUm) {
        summary.setNumUngradedMissions(numUm);
    }

    public void setNumUngradedQuests(int numUq) {
        summary.setNumUngradedQuests(numUq);
    }

    public void setNumUpcomingConsultations(int numConsult) {
        summary.setNumUpcomingConsultations(numConsult);
    }

    public void setNumUpcomingMasteryChecks(int numMc) {
        summary.setNumUpcomingMasteryChecks(numMc);
    }

    public void setNumTasks(int numT) {
        summary.setNumTasks(numT);
    }

    @Override
    public StringProperty getSummary() {
        return summary.getSummaryDetails();
    }

    public boolean hasSummary() {
        return !summary.isEmpty();
    }

}
