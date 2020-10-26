package seedu.resireg.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@Code Predicate} that always evaluate to true
     */
    Predicate<Room> PREDICATE_SHOW_ALL_ROOMS = unused -> true;

    /**
     * {@Code Predicate} that always evaluate to true
     */
    Predicate<Allocation> PREDICATE_SHOW_ALL_ALLOCATIONS = unused -> true;

    /**
     * {@Code Predicate} that always evaluate to true
     */
    Predicate<BinItem> PREDICATE_SHOW_ALL_BIN_ITEMS = unused -> true;

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
     * Returns the user prefs' command aliases.
     */
    List<CommandWordAlias> getCommandWordAliases();

    /**
     * Returns the user prefs' command aliases.
     */
    String getCommandWordAliasesAsString();

    /**
     * Returns true if a command alias with the same data as {@code target} exists
     * in user preferences.
     */
    boolean hasCommandWordAlias(CommandWordAlias target);

    /**
     * Deletes the given alias.
     * The alias must exist in user prefs.
     */
    void deleteCommandWordAlias(CommandWordAlias target);

    /**
     * Adds the given alias.
     * {@code alias} must not already exist in user prefs.
     */
    void addCommandWordAlias(CommandWordAlias source);

    /**
     * Returns the user prefs' ResiReg file path.
     */
    Path getResiRegFilePath();

    /**
     * Sets the user prefs' ResiReg file path.
     */
    void setResiRegFilePath(Path resiRegFilePath);

    void setDaysStoredInBin(int daysStoredInBin);

    /**
     * Replaces ResiReg data with the data in {@code resiReg}.
     */
    void setResiReg(ReadOnlyResiReg resiReg);

    /**
     * Returns a read-only copy of ResiReg data
     */
    ReadOnlyResiReg getResiReg();

    /**
     * Returns true if a student with the same identity as {@code student} exists
     * in ResiReg.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in ResiReg.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student at the back of the list.
     * {@code student} must not already exist in ResiReg.
     */
    void addStudent(Student student);

    /**
     * Adds the given student at the front of the list depending on the boolean value supplied.
     * {@code student} must not already exist in ResiReg.
     */
    void addStudent(Student student, boolean isFront);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in ResiReg.
     * The student identity of {@code editedStudent} must not be the same as
     * another existing student in ResiReg.
     */
    void setStudent(Student target, Student editedStudent);

    // === ROOMS ====

    /**
     * Returns true if a room with the same data as {@code room} exists in
     * ResiReg.
     */
    boolean hasRoom(Room room);

    /**
     * Deletes the given room.
     * The room must exist in ResiReg.
     */
    void deleteRoom(Room target);

    /**
     * Adds the given room.
     * {@code room} must already exist in ResiReg.
     */
    void addRoom(Room room);

    /**
     * Replaces the given room {@code target} with {@code editedRoom}.
     * {@code target} must exist in ResiReg.
     * The room identity of {@code editedStudent} must not be the same as another
     * existing room in ResiReg.
     */
    void setRoom(Room target, Room editedRoom);

    // === ALLOCATIONS ====

    /**
     * Returns true if an allocation with the {@code student} exists in ResiReg.
     */
    boolean isAllocated(Student student);

    /**
     * Returns true if an allocation with the {@code room} exists in the address
     * book.
     */
    boolean isAllocated(Room room);

    /**
     * Returns true if an allocation with the same identity as {@code allocation}
     * exists in ResiReg.
     */
    boolean hasAllocation(Allocation allocation);

    /**
     * Removes the given allocation.
     * The allocation must exist in ResiReg.
     */
    void removeAllocation(Allocation target);

    /**
     * Adds the given allocation.
     * {@code allocation} must not already exist in ResiReg.
     */
    void addAllocation(Allocation allocation);

    /**
     * Replaces the given allocation {@code target} with {@code editedAllocation}.
     * {@code target} must exist in ResiReg.
     * The allocation identity of {@code editedAllocation} must not be the same as
     * another existing allocation in ResiReg.
     */
    void setAllocation(Allocation target, Allocation editedAllocation);

    // Bin Items
    /**
     * Returns true if a bin item with the same data as {@code binitem} exists in
     * ResiReg.
     */
    boolean hasBinItem(BinItem binItem);

    /**
     * Deletes the given bin item.
     * The bin item must exist in ResiReg.
     */
    void deleteBinItem(BinItem target);

    /**
     * Adds the given bin item.
     * {@code binItem} must already exist in ResiReg.
     */
    void addBinItem(BinItem binItem);

    /**
     * Replaces the given room {@code target} with {@code editedItem}.
     * {@code target} must exist in ResiReg.
     * The bin item identity of {@code editedItem} must not be the same as another
     * existing bin item in ResiReg.
     */
    void setBinItem(BinItem target, BinItem editedItem);

    // Semester

    void deleteExpiredBinItems();

    /** Returns the current semester */
    Semester getSemester();

    // Lists for UI
    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /**
     * Returns an unmodifiable view of the filtered room list
     */
    ObservableList<Room> getFilteredRoomList();

    /**
     * Returns an unmodifiable view of the filtered allocation list
     */
    ObservableList<Allocation> getFilteredAllocationList();

    /**
     * Returns an unmodifiable view of the bin item list
     */
    ObservableList<BinItem> getFilteredBinItemList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * If the predicate needs to use methods from {@code Model}, use
     * {@link Model#updateFilteredStudentList(ModelPredicate)} instead to ensure the list is automatically
     * updated when the {@code Model}'s contents changes.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * @see Model#updateFilteredStudentList(Predicate)
     */
    void updateFilteredStudentList(ModelPredicate<Student> predicate);

    /**
     * Updates the filter of the filtered bin item list to filter by the given {@code predicate}.
     * If the predicate needs to use methods from {@code Model}, use
     * {@link Model#updateFilteredBinItemList(ModelPredicate)} instead to ensure the list is automatically
     * updated when the {@code Model}'s contents changes.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBinItemList(Predicate<BinItem> predicate);

    /**
     * @see Model#updateFilteredBinItemList(Predicate)
     */
    void updateFilteredBinItemList(ModelPredicate<BinItem> predicate);

    /**
     * Updates the filter of the filtered room list to filter by the given {@code predicate}.
     * If the predicate needs to use methods from {@code Model}, use
     * {@link Model#updateFilteredRoomList(ModelPredicate)} instead to ensure the list is automatically
     * updated when the {@code Model}'s contents changes.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRoomList(Predicate<Room> predicate);

    /**
     * @see Model#updateFilteredRoomList(Predicate)
     */
    void updateFilteredRoomList(ModelPredicate<Room> predicate);

    /**
     * Updates the filter of the filtered allocation list to filter by the given {@code predicate}.
     * If the predicate needs to use methods from {@code Model}, use
     * {@link Model#updateFilteredAllocationList(ModelPredicate)} instead to ensure the list is automatically
     * updated when the {@code Model}'s contents changes.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAllocationList(Predicate<Allocation> predicate);

    /**
     * @see Model#updateFilteredAllocationList(Predicate)
     */
    void updateFilteredAllocationList(ModelPredicate<Allocation> predicate);

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
