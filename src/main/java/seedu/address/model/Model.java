package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.log.Log;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Log> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a log with the same identity as {@code log} exists in the address book.
     */
    boolean hasPerson(Log log);

    /**
     * Deletes the given log.
     * The log must exist in the address book.
     */
    void deletePerson(Log target);

    /**
     * Adds the given log.
     * {@code log} must not already exist in the address book.
     */
    void addPerson(Log log);

    /**
     * Replaces the given log {@code target} with {@code editedLog}.
     * {@code target} must exist in the address book.
     * The log identity of {@code editedLog} must not be the same as another existing log in the address book.
     */
    void setPerson(Log target, Log editedLog);

    /** Returns an unmodifiable view of the filtered log list */
    ObservableList<Log> getFilteredPersonList();

    /**
     * Updates the filter of the filtered log list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Log> predicate);
}
