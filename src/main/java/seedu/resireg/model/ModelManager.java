package seedu.resireg.model;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;

/**
 * Represents the in-memory model of ResiReg data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedResiReg versionedResiReg;
    private final UserPrefs userPrefs;
    private final Semester semester;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Room> filteredRooms;
    private final FilteredList<Allocation> filteredAllocations;

    /**
     * Initializes a ModelManager with the given ResiReg data and userPrefs.
     */
    public ModelManager(ReadOnlyResiReg readOnlyResiReg, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(readOnlyResiReg, userPrefs);

        logger.fine("Initializing with ResiReg data: " + readOnlyResiReg + " and user prefs " + userPrefs);

        versionedResiReg = new VersionedResiReg(readOnlyResiReg);
        this.userPrefs = new UserPrefs(userPrefs);
        semester = versionedResiReg.getSemester();
        filteredStudents = new FilteredList<>(versionedResiReg.getStudentList());
        filteredRooms = new FilteredList<>(versionedResiReg.getRoomList());
        filteredAllocations = new FilteredList<>(versionedResiReg.getAllocationList());
    }

    public ModelManager() {
        this(new ResiReg(), new UserPrefs());
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
    public List<CommandWordAlias> getCommandWordAliases() {
        return Collections.unmodifiableList(userPrefs.getCommandWordAliases());
    }

    @Override
    public String getCommandWordAliasesAsString() {
        return userPrefs.getCommandWordAliasesAsString();
    }

    @Override
    public boolean hasCommandWordAlias(CommandWordAlias target) {
        requireNonNull(target);
        return userPrefs.hasAlias(target);
    }

    @Override
    public void deleteCommandWordAlias(CommandWordAlias target) {
        userPrefs.deleteAlias(target);
    }

    @Override
    public void addCommandWordAlias(CommandWordAlias source) {
        requireNonNull(source);
        userPrefs.addAlias(source);
    }

    @Override
    public Path getResiRegFilePath() {
        return userPrefs.getResiRegFilePath();
    }

    @Override
    public void setResiRegFilePath(Path resiRegFilePath) {
        requireNonNull(resiRegFilePath);
        userPrefs.setResiRegFilePath(resiRegFilePath);
    }

    //=========== ResiReg ================================================================================

    @Override
    public void setResiReg(ReadOnlyResiReg resiReg) {
        versionedResiReg.resetData(resiReg);
    }

    @Override
    public ReadOnlyResiReg getResiReg() {
        return versionedResiReg;
    }


    //=========== Student  ================================================================================

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return versionedResiReg.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        versionedResiReg.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        requireNonNull(student);
        versionedResiReg.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        versionedResiReg.setStudent(target, editedStudent);
    }

    //=========== Room ================================================================================
    @Override
    public void setRoom(Room target, Room editedRoom) {
        requireAllNonNull(target, editedRoom);
        versionedResiReg.setRoom(target, editedRoom);
    }

    @Override
    public boolean hasRoom(Room room) {
        requireNonNull(room);
        return versionedResiReg.hasRoom(room);
    }

    @Override
    public void deleteRoom(Room target) {
        versionedResiReg.removeRoom(target);
    }

    @Override
    public void addRoom(Room room) {
        requireNonNull(room);
        versionedResiReg.addRoom(room);
        updateFilteredRoomList(PREDICATE_SHOW_ALL_ROOMS);
    }

    //=========== Allocation ================================================================================
    @Override
    public boolean isAllocated(Student student) {
        requireNonNull(student);
        return versionedResiReg.isAllocated(student);
    }

    /**
     * Returns true if an allocation relating to {@code room} exists in ResiReg.
     */
    @Override
    public boolean isAllocated(Room room) {
        requireNonNull(room);
        return versionedResiReg.isAllocated(room);
    }

    @Override
    public boolean hasAllocation(Allocation allocation) {
        requireNonNull(allocation);
        return versionedResiReg.hasAllocation(allocation);
    }

    @Override
    public void removeAllocation(Allocation target) {
        requireNonNull(target);
        versionedResiReg.removeAllocation(target);
    }

    @Override
    public void addAllocation(Allocation allocation) {
        requireNonNull(allocation);
        versionedResiReg.addAllocation(allocation);
    }

    @Override
    public void setAllocation(Allocation target, Allocation editedAllocation) {
        requireAllNonNull(target, editedAllocation);
        versionedResiReg.setAllocation(target, editedAllocation);
    }

    public Semester getSemester() {
        return semester;
    }
    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedResiReg}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    //=========== Filtered Room List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Room} backed by the internal list of
     * {@code versionedResiReg}
     */
    @Override
    public ObservableList<Room> getFilteredRoomList() {
        return filteredRooms;
    }

    @Override
    public ObservableList<Allocation> getFilteredAllocationList() {
        return filteredAllocations;
    }

    @Override
    public void updateFilteredRoomList(Predicate<Room> predicate) {
        requireNonNull(predicate);
        filteredRooms.setPredicate(predicate);
    }

    @Override
    public void updateFilteredAllocationList(Predicate<Allocation> predicate) {
        requireNonNull(predicate);
        filteredAllocations.setPredicate(predicate);
    }

    //=========== Undo/Redo =============================================================

    @Override
    public boolean canUndoResiReg() {
        return versionedResiReg.canUndo();
    }

    @Override
    public boolean canRedoResiReg() {
        return versionedResiReg.canRedo();
    }

    @Override
    public void undoResiReg() {
        versionedResiReg.undo();
    }

    @Override
    public void redoResiReg() {
        versionedResiReg.redo();
    }

    @Override
    public void saveStateResiReg() {
        versionedResiReg.save();
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
        return versionedResiReg.equals(other.versionedResiReg)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && filteredRooms.equals(other.filteredRooms);
    }

}
