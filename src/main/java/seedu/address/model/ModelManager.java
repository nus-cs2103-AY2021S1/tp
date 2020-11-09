package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.information.Job;
import seedu.address.model.information.Person;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final PersonAddressBook personAddressBook;
    private final JobAddressBook jobAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Job> filteredJobs;
    private final SortedList<Person> sortedPersons;
    private final SortedList<Job> sortedJobs;
    private Person displayedPerson;
    private Job displayedJob;

    /**
     * Initializes a ModelManager with the given addressBook, jobAddressBook and userPrefs.
     */
    public ModelManager(ReadOnlyPersonAddressBook personAddressBook, ReadOnlyJobAddressBook jobAddressBook,
            ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(personAddressBook, jobAddressBook, userPrefs);

        logger.fine("Initializing with person address book: " + personAddressBook
                + "Initializing with job address book: " + jobAddressBook + " and user prefs " + userPrefs);

        this.personAddressBook = new PersonAddressBook(personAddressBook);
        this.jobAddressBook = new JobAddressBook(jobAddressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.personAddressBook.getPersonList());
        filteredJobs = new FilteredList<>(this.jobAddressBook.getJobList());
        sortedPersons = new SortedList<>(this.personAddressBook.getPersonList());
        sortedJobs = new SortedList<>(this.jobAddressBook.getJobList());
    }

    public ModelManager() {
        this(new PersonAddressBook(), new JobAddressBook(), new UserPrefs());
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
    public Path getPersonAddressBookFilePath() {
        return userPrefs.getPersonAddressBookFilePath();
    }

    @Override
    public void setPersonAddressBookFilePath(Path personAddressBookFilePath) {
        requireNonNull(personAddressBookFilePath);
        userPrefs.setPersonAddressBookFilePath(personAddressBookFilePath);
    }

    @Override
    public Path getJobAddressBookFilePath() {
        return userPrefs.getJobAddressBookFilePath();
    }

    @Override
    public void setJobAddressBookFilePath(Path jobAddressBookFilePath) {
        requireNonNull(jobAddressBookFilePath);
        userPrefs.setJobAddressBookFilePath(jobAddressBookFilePath);
    }

    //=========== Person AddressBook ================================================================================

    @Override
    public void setPersonAddressBook(ReadOnlyPersonAddressBook personAddressBook) {
        this.personAddressBook.resetData(personAddressBook);
    }

    @Override
    public ReadOnlyPersonAddressBook getPersonAddressBook() {
        return personAddressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return personAddressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        personAddressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        personAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        personAddressBook.setPerson(target, editedPerson);
    }

    //=========== JobAddressBook ================================================================================

    @Override
    public void setJobAddressBook(ReadOnlyJobAddressBook jobAddressBook) {
        this.jobAddressBook.resetData(jobAddressBook);
    }

    @Override
    public void deleteJob(Job target) {
        jobAddressBook.removeJob(target);
    }

    @Override
    public ReadOnlyJobAddressBook getJobAddressBook() {
        return jobAddressBook;
    }

    @Override
    public boolean hasJob(Job job) {
        requireNonNull(job);
        return jobAddressBook.hasJob(job);
    }

    @Override
    public void addJob(Job job) {
        jobAddressBook.addJob(job);
        updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
    }

    @Override
    public void setJob(Job target, Job editedJob) {
        requireAllNonNull(target, editedJob);

        jobAddressBook.setJob(target, editedJob);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Job List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Job} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Job> getFilteredJobList() {
        return filteredJobs;
    }

    @Override
    public void updateFilteredJobList(Predicate<Job> predicate) {
        requireNonNull(predicate);
        filteredJobs.setPredicate(predicate);
    }

    //=========== Sorted Person List Accessors ===============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getSortedPersonList() {
        return sortedPersons;
    }

    @Override
    public void updateSortedPersonList(Comparator<Person> comparator) {
        requireAllNonNull(comparator);
        sortedPersons.setComparator(comparator);
        personAddressBook.setPersons(sortedPersons);
    }

    //=========== Sorted Job List Accessors ===============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Job} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Job> getSortedJobList() {
        return sortedJobs;
    }

    @Override
    public void updateSortedJobList(Comparator<Job> comparator) {
        requireNonNull(comparator);
        sortedJobs.setComparator(comparator);
        jobAddressBook.setJobs(sortedJobs);
    }

    //=========== Displayed Person ===============================================================

    @Override
    public Person getDisplayedPerson() {
        return displayedPerson;
    }

    @Override
    public void setDisplayedPerson(Person person) {
        requireAllNonNull(person);
        displayedPerson = person;
    }

    //=========== Displayed Job ===============================================================

    @Override
    public Job getDisplayedJob() {
        return displayedJob;
    }

    @Override
    public void setDisplayedJob(Job job) {
        requireAllNonNull(job);
        displayedJob = job;
    }

    //=========== Others =============================================================

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
        return personAddressBook.equals(other.personAddressBook)
                && jobAddressBook.equals(other.jobAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
