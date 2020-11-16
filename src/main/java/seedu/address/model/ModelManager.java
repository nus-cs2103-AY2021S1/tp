package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Tasker tasker;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Consultation> filteredConsults;

    /**
     * Initializes a ModelManager with the given tasker and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook tasker, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(tasker, userPrefs);

        logger.fine("Initializing with address book: " + tasker + " and user prefs " + userPrefs);

        this.tasker = new Tasker(tasker);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.tasker.getPersonList());
        filteredConsults = new FilteredList<>(this.tasker.getConsultationList());
    }

    public ModelManager() {
        this(new Tasker(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== TAsker ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook tasker) {
        this.tasker.resetData(tasker);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return tasker;
    }
    //---------- Student-related operations -------------------------------------------------------------
    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return tasker.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        tasker.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        tasker.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        tasker.setPerson(target, editedPerson);
    }
    //---------- Consultation-related operations -------------------------------------------------------------

    @Override
    public boolean hasConsult(Consultation consultation) {
        requireNonNull(consultation);
        return tasker.hasConsultation(consultation);
    }

    @Override
    public boolean hasConflictingPersonalConsultation(Consultation consultation) {
        requireNonNull(consultation);
        return tasker.hasConflictingPersonalConsultation(consultation);
    }

    @Override
    public void addConsultation(Consultation consultation) {
        tasker.addConsultation(consultation);
        updateFilteredConsultList(PREDICATE_SHOW_ALL_CONSULTS);
    }

    @Override
    public void deleteConsultation(Consultation target) {
        tasker.removeConsultation(target);
    }

    @Override
    public void setConsultation(Consultation target, Consultation editedConsultation) {
        requireAllNonNull(target, editedConsultation);

        tasker.setConsult(target, editedConsultation);
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
    //=========== Filtered Consult List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Consultation} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Consultation> getFilteredConsultList() {
        return filteredConsults;
    }

    @Override
    public void updateFilteredConsultList(Predicate<Consultation> predicate) {
        requireNonNull(predicate);
        filteredConsults.setPredicate(predicate);
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
        return tasker.equals(other.tasker)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredConsults.equals(other.filteredConsults);
    }

}
