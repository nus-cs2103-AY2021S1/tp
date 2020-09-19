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
import seedu.address.model.project.Project;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MainCatalogue mainCatalogue;
    private final UserPrefs userPrefs;
    private final FilteredList<Project> filteredProjects;

    /**
     * Initializes a ModelManager with the given mainCatalogue and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.mainCatalogue = new MainCatalogue(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredProjects = new FilteredList<>(this.mainCatalogue.getPersonList());
    }

    public ModelManager() {
        this(new MainCatalogue(), new UserPrefs());
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

    //=========== MainCatalogue ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.mainCatalogue.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return mainCatalogue;
    }

    @Override
    public boolean hasPerson(Project project) {
        requireNonNull(project);
        return mainCatalogue.hasPerson(project);
    }

    @Override
    public void deletePerson(Project target) {
        mainCatalogue.removePerson(target);
    }

    @Override
    public void addPerson(Project project) {
        mainCatalogue.addPerson(project);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        mainCatalogue.setPerson(target, editedProject);
    }

    //=========== Filtered Project List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Project} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Project> getFilteredPersonList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
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
        return mainCatalogue.equals(other.mainCatalogue)
                && userPrefs.equals(other.userPrefs)
                && filteredProjects.equals(other.filteredProjects);
    }

}
