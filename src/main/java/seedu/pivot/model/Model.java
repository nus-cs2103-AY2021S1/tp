package seedu.pivot.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.pivot.commons.core.GuiSettings;
import seedu.pivot.model.investigationcase.ArchiveStatus;
import seedu.pivot.model.investigationcase.Case;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Case> PREDICATE_SHOW_DEFAULT_CASES = investigationCase ->
            investigationCase.getArchiveStatus().equals(ArchiveStatus.DEFAULT);

    Predicate<Case> PREDICATE_SHOW_ARCHIVED_CASES = investigationCase ->
            investigationCase.getArchiveStatus().equals(ArchiveStatus.ARCHIVED);

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
     * Returns the user prefs' address book file path.
     */
    Path getPivotFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setPivotFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setPivot(ReadOnlyPivot addressBook);

    /** Returns the AddressBook */
    ReadOnlyPivot getPivot();

    /**
     * Returns true if a case with the same identity as {@code case} exists in the address book.
     */
    boolean hasCase(Case investigationCase);

    /**
     * Deletes the given case.
     * The case must exist in PIVOT.
     */
    void deleteCase(Case target);

    /**
     * Adds the given case.
     * {@code case} must not already exist in PIVOT.
     */
    void addCase(Case investigationCase);

    /**
     * Replaces the given case {@code target} with {@code editedCase}.
     * {@code target} must exist in the address book.
     * The case identity of {@code editedCase} must not be the same as another existing case in the address book.
     */
    void setCase(Case target, Case editedCase);

    /**
     * Returns an unmodifiable view of the filtered case list
     */
    default ObservableList<Case> getFilteredCaseList() {
        return null;
    }

    /**
     * Updates the filter of the filtered case list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCaseList(Predicate<Case> predicate);

    void commitPivot(String command, boolean isMainPageCommand);

    boolean canRedoPivot();

    void redoPivot();

    boolean canUndoPivot();

    void undoPivot();

    String getStateCommand();

    boolean isMainPageCommand();
}
