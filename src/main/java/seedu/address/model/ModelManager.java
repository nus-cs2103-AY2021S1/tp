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
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final McGymmy mcGymmy;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given mcGymmy and userPrefs.
     */
    public ModelManager(ReadOnlyMcGymmy mcGymmy, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(mcGymmy, userPrefs);

        logger.fine("Initializing with address book: " + mcGymmy + " and user prefs " + userPrefs);

        this.mcGymmy = new McGymmy(mcGymmy);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.mcGymmy.getPersonList());
    }

    public ModelManager() {
        this(new McGymmy(), new UserPrefs());
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

    //=========== McGymmy ================================================================================

    @Override
    public void setMcGymmy(ReadOnlyMcGymmy mcGymmy) {
        this.mcGymmy.resetData(mcGymmy);
    }

    @Override
    public ReadOnlyMcGymmy getMcGymmy() {
        return mcGymmy;
    }

    @Override
    public boolean hasFood(Person person) {
        requireNonNull(person);
        return mcGymmy.hasFood(person);
    }

    @Override
    public void deleteFood(Person target) {
        mcGymmy.removeFood(target);
    }

    @Override
    public void addFood(Person person) {
        mcGymmy.addFood(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setFood(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        mcGymmy.setFood(target, editedPerson);
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
        return mcGymmy.equals(other.mcGymmy)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
