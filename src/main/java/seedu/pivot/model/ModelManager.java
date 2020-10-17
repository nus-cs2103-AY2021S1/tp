package seedu.pivot.model;

import static java.util.Objects.requireNonNull;
import static seedu.pivot.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.pivot.commons.core.GuiSettings;
import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Case;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Pivot pivot;
    private final UserPrefs userPrefs;
    private final FilteredList<Case> filteredCases;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyPivot addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.pivot = new Pivot(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCases = new FilteredList<>(this.pivot.getCaseList());
    }

    public ModelManager() {
        this(new Pivot(), new UserPrefs());
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
        return userPrefs.getPivotFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setPivotFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyPivot addressBook) {
        this.pivot.resetData(addressBook);
    }

    @Override
    public ReadOnlyPivot getAddressBook() {
        return pivot;
    }

    @Override
    public boolean hasCase(Case investigationCase) {
        requireNonNull(investigationCase);
        return pivot.hasCase(investigationCase);
    }

    @Override
    public void deleteCase(Case target) {
        pivot.removeCase(target);
    }

    @Override
    public void addCase(Case investigationCase) {
        pivot.addCase(investigationCase);
        updateFilteredCaseList(PREDICATE_SHOW_ALL_CASES);
    }

    @Override
    public void setCase(Case target, Case editedCase) {
        requireAllNonNull(target, editedCase);
        pivot.setCase(target, editedCase);
        StateManager.refresh();
    }

    //=========== Filtered Case List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Case} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Case> getFilteredCaseList() {
        return filteredCases;
    }

    @Override
    public void updateFilteredCaseList(Predicate<Case> predicate) {
        requireNonNull(predicate);
        filteredCases.setPredicate(predicate);
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
        return pivot.equals(other.pivot)
                && userPrefs.equals(other.userPrefs)
                && filteredCases.equals(other.filteredCases);
    }

}
