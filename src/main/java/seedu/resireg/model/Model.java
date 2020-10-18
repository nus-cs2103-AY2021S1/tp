package seedu.resireg.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@Code Predicate} that always evaluate to true */
    Predicate<Room> PREDICATE_SHOW_ALL_ROOMS = unused -> true;

    /**
     * {@Code Predicate} that evaluate to true only if the room is vacant, meaning no student is occupying it.
     */
    Predicate<Room> PREDICATE_SHOW_VACANT_ROOMS = room -> !room.hasStudent();

    /**
     * {@Code Predicate} that evaluate to true only if the room is occupied, meaning there is a student occupying it.
     */
    Predicate<Room> PREDICATE_SHOW_ALLOCATED_ROOMS = Room::hasStudent;

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
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student
     * in the address book.
     */
    void setStudent(Student target, Student editedStudent);

    /**
     * Replaces the given room {@code target} with {@code editedRoom}.
     * {@code target} must exist in the address book.
     * The room identity of {@code editedStudent} must not be the same as another existing room
     * in the address book.
     */
    void setRoom(Room target, Room editedRoom);

    /**
     * Returns true if a room with the same data as {@code room} exists in the address book.
     */
    boolean hasRoom(Room room);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered room list */
    ObservableList<Room> getFilteredRoomList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered room list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRoomList(Predicate<Room> predicate);

    /**
     * Returns true if the model has previous ResiReg states to restore.
     */
    boolean canUndoResiReg();

    /**
     * Returns true if the model has undone ResiReg states to restore.
     */
    boolean canRedoResiReg();

    /**
     * Restores the model's ResiReg to its previous state.
     */
    void undoResiReg();

    /**
     * Restores the mode's ResiReg to its previously undone state.
     */
    void redoResiReg();

    /**
     * Saves the current ResiReg state for undo/redo.
     */
    void saveStateResiReg();
}
