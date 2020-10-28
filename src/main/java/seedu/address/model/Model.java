package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.task.Task;
import seedu.address.timetable.TimetableData;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Assignment> PREDICATE_SHOW_ALL_ASSIGNMENT = unused -> true;
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    Predicate<Assignment> PREDICATE_SHOW_ALL_REMINDED_ASSIGNMENTS = assignment -> assignment.isReminded();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    void setPreviousModel(Model previousModel);

    /**
     * return previous model
     */
    Model getPreviousModel();

    /**
     * before update model
     */
    void preUpdateModel();

    /**
     * Goes to previous model
     */
    void goToPreviousModel();

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
     * Returns the filtered assignment list
     */
    FilteredList<Assignment> getFilteredAssignments();

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
     * Adds lessons based on NUSMods Timetable data.
     */
    void importTimetable(TimetableData data);

    /**
     * Returns true if an assignment with the same identity as {@code assignment} exists in the address book.
     */
    boolean hasAssignment(Assignment assignment);

    /**
     * Deletes the given assignment.
     * The assignment must exist in the address book.
     */
    void deleteAssignment(Assignment target);

    /**
     * Adds the given assignment.
     * {@code assignment} must not already exist in the address book.
     */
    void addAssignment(Assignment assignment);

    /**
     * Replaces the given assignment {@code target} with {@code editedAssignment}.
     * {@code target} must exist in the address book.
     * The assignment identity of {@code editedAssignment} must not be the same as another
     * existing assignment in the address book.
     */
    void setAssignment(Assignment target, Assignment editedAssignment);

    /** Returns an unmodifiable view of the filtered assignment list */
    ObservableList<Assignment> getFilteredAssignmentList();

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered assignment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAssignmentList(Predicate<Assignment> predicate);

    /** Returns an unmodifiable view of the reminded assignments list */
    ObservableList<Assignment> getRemindedAssignmentsList();
}
