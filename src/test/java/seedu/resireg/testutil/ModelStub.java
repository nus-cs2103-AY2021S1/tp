package seedu.resireg.testutil;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelPredicate;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public String getCommandWordAliasesAsString() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public List<CommandWordAlias> getCommandWordAliases() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasCommandWordAlias(CommandWordAlias target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteCommandWordAlias(CommandWordAlias target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addCommandWordAlias(CommandWordAlias source) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getResiRegFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setResiRegFilePath(Path resiRegFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setDaysStoredInBin(int daysStoredInBin) {

    }

    @Override
    public void addStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addStudent(Student student, boolean isFront) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setResiReg(ReadOnlyResiReg newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyResiReg getResiReg() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasStudent(Student student) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteStudent(Student target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean isAllocated(Student student) {
        return false;
    }

    @Override
    public boolean isAllocated(Room room) {
        return false;
    }

    @Override
    public void setRoom(Room target, Room editedRoom) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasRoom(Room room) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteRoom(Room target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addRoom(Room room) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasAllocation(Allocation allocation) {
        return false;
    }

    @Override
    public void removeAllocation(Allocation target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addAllocation(Allocation allocation) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAllocation(Allocation target, Allocation editedAllocation) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasBinItem(BinItem binItem) {
        return false;
    }

    @Override
    public void deleteBinItem(BinItem target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addBinItem(BinItem binItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setBinItem(BinItem target, BinItem editedItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteExpiredBinItems() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Semester getSemester() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Room> getFilteredRoomList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Allocation> getFilteredAllocationList() {
        return null;
    }

    @Override
    public ObservableList<BinItem> getFilteredBinItemList() {
        return null;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredStudentList(ModelPredicate<Student> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredBinItemList(Predicate<BinItem> predicate) {

    }

    @Override
    public void updateFilteredBinItemList(ModelPredicate<BinItem> predicate) {

    }

    @Override
    public void updateFilteredRoomList(Predicate<Room> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredRoomList(ModelPredicate<Room> predicate) {

    }

    @Override
    public void updateFilteredAllocationList(Predicate<Allocation> predicate) {

    }

    @Override
    public void updateFilteredAllocationList(ModelPredicate<Allocation> predicate) {

    }

    @Override
    public boolean canUndoResiReg() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean canRedoResiReg() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void undoResiReg() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void redoResiReg() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveStateResiReg() {
        throw new AssertionError("This method should not be called.");
    }
}

