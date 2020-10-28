package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.AppMode;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelPredicate;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.Storage;
import seedu.resireg.testutil.CommandWordAliasBuilder;

public class AddAliasCommandTest {

    private CommandHistory history = new CommandHistory();

    @Test
    public void constructor_nullAlias_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAliasCommand(null));
    }

    @Test
    public void execute_aliasAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingAliasAdded modelStub = new ModelStubAcceptingAliasAdded();
        CommandWordAlias validAlias = new CommandWordAliasBuilder().build();
        Storage storageStub = null;

        CommandResult commandResult = new AddAliasCommand(validAlias).execute(modelStub, storageStub, history);

        assertEquals(String.format(AddAliasCommand.MESSAGE_SUCCESS, validAlias), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAlias), modelStub.aliasesAdded);
    }

    @Test
    public void execute_duplicateAlias_throwsCommandException() {
        CommandWordAlias validAlias = new CommandWordAliasBuilder().build();
        AddAliasCommand addAliasCommand = new AddAliasCommand(validAlias);
        ModelStub modelStub = new ModelStubWithAlias(validAlias);
        Storage storageStub = null;

        assertThrows(CommandException.class,
            AddAliasCommand.MESSAGE_DUPLICATE_ALIAS, () -> addAliasCommand.execute(modelStub, storageStub, history));
    }

    @Test
    public void equals() {
        CommandWordAlias roomsR = new CommandWordAliasBuilder().withAlias("r").build();
        CommandWordAlias roomsRo = new CommandWordAliasBuilder().withAlias("ro").build();
        AddAliasCommand addRoomsRCommand = new AddAliasCommand(roomsR);
        AddAliasCommand addRoomsRoCommand = new AddAliasCommand(roomsRo);

        // same object -> returns true
        assertTrue(addRoomsRCommand.equals(addRoomsRCommand));

        // same values -> returns true
        AddAliasCommand addRoomsRCommandCopy = new AddAliasCommand(roomsR);
        assertTrue(addRoomsRCommand.equals(addRoomsRCommandCopy));

        // different types -> returns false
        assertFalse(addRoomsRCommandCopy.equals(1));

        // null -> returns false
        assertFalse(addRoomsRCommandCopy.equals(null));

        // different command -> returns false
        assertFalse(addRoomsRCommand.equals(addRoomsRoCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
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
        };

        @Override
        public void addCommandWordAlias(CommandWordAlias source) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getResiRegFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setResiRegFilePath(Path addressBookFilePath) {
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAllocationList(Predicate<Allocation> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAllocationList(ModelPredicate<Allocation> predicate) {
            throw new AssertionError("This method should not be called.");
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

        @Override
        public AppMode getAppMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void finalizeRooms() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithAlias extends ModelStub {
        private final CommandWordAlias commandWordAlias;

        ModelStubWithAlias(CommandWordAlias commandWordAlias) {
            requireNonNull(commandWordAlias);
            this.commandWordAlias = commandWordAlias;
        }

        @Override
        public boolean hasCommandWordAlias(CommandWordAlias commandWordAlias) {
            requireNonNull(commandWordAlias);
            return this.commandWordAlias.getAlias().equals(commandWordAlias.getAlias());
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingAliasAdded extends ModelStub {
        final ArrayList<CommandWordAlias> aliasesAdded = new ArrayList<>();

        @Override
        public boolean hasCommandWordAlias(CommandWordAlias commandWordAlias) {
            requireNonNull(commandWordAlias);
            return aliasesAdded.stream().anyMatch(commandWordAlias::equals);
        }

        @Override
        public void addCommandWordAlias(CommandWordAlias commandWordAlias) {
            requireNonNull(commandWordAlias);
            aliasesAdded.add(commandWordAlias);
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            return new UserPrefs();
        }
    }

}
