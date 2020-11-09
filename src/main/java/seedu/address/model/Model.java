package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.information.Job;
import seedu.address.model.information.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Job> PREDICATE_SHOW_ALL_JOBS = unused -> true;

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

    //=========== PersonAddressBook ================================================================================

    /**
     * Returns the user prefs' person address book file path.
     */
    Path getPersonAddressBookFilePath();

    /**
     * Sets the user prefs' person address book file path.
     */
    void setPersonAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces person address book data with the data in {@code personAddressBook}.
     */
    void setPersonAddressBook(ReadOnlyPersonAddressBook personAddressBook);

    /** Returns the PersonAddressBook */
    ReadOnlyPersonAddressBook getPersonAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the person address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the person address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the person address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the person address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the person
     * address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the sorted person list */
    ObservableList<Person> getSortedPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the comparator of the sorted person list to filter by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedPersonList(Comparator<Person> comparator);

    /**
     * Gets the person to be displayed.
     * The person must exist in the person address book.
     */
    Person getDisplayedPerson();

    /**
     * Sets the person to be displayed.
     * The person must exist in the person address book.
     */
    void setDisplayedPerson(Person person);

    //=========== JobAddressBook ================================================================================

    /**
     * Returns the user prefs' job address book file path.
     */
    Path getJobAddressBookFilePath();

    /**
     * Sets the user prefs' job address book file path.
     */
    void setJobAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces job address book data with the data in {@code jobAddressBook}.
     */
    void setJobAddressBook(ReadOnlyJobAddressBook jobAddressBook);

    /** Returns the JobAddressBook */
    ReadOnlyJobAddressBook getJobAddressBook();

    /**
     * Returns true if a job with the same identity as {@code job} exists in the job address book.
     */
    boolean hasJob(Job job);

    /**
     * Deletes the given job.
     * The job must exist in the job address book.
     */
    void deleteJob(Job target);

    /**
     * Adds the given job.
     * {@code job} must not already exist in the job address book.
     */
    void addJob(Job job);


    /**
     * Replaces the given job {@code target} with {@code editedJob}.
     * {@code target} must exist in the job address book.
     * The job identity of {@code editedJob} must not be the same as another existing job in the address book.
     */
    void setJob(Job target, Job editedJob);

    /** Returns an unmodifiable view of the filtered job list */
    ObservableList<Job> getFilteredJobList();

    /**
     * Updates the filter of the filtered job list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredJobList(Predicate<Job> predicate);

    /** Returns an unmodifiable view of the sorted job list */
    ObservableList<Job> getSortedJobList();

    /**
     * Updates the order of the sorted job list to sort by the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void updateSortedJobList(Comparator<Job> comparator);

    /**
     * Gets the job to be displayed.
     * The job must exist in the job address book.
     */
    Job getDisplayedJob();

    /**
     * Sets the job to be displayed.
     * The job must exist in the job address book.
     */
    void setDisplayedJob(Job job);
}
