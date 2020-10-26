package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelPredicate;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.alias.CommandWordAlias;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.Storage;
import seedu.resireg.testutil.StudentBuilder;

public class AddCommandTest {

    private CommandHistory history = new CommandHistory();

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStudentAdded modelStub = new ModelStubAcceptingStudentAdded();
        StorageStub storageStub = new StorageStub();
        Student validStudent = new StudentBuilder().build();

        CommandResult commandResult = new AddCommand(validStudent).execute(modelStub, storageStub, history);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validStudent.getNameAsString(),
            validStudent.getStudentId().value), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudent), modelStub.studentsAdded);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student validStudent = new StudentBuilder().build();
        AddCommand addCommand = new AddCommand(validStudent);
        ModelStub modelStub = new ModelStubWithStudent(validStudent);
        StorageStub storageStub = new StorageStub();

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () ->
            addCommand.execute(modelStub, storageStub, history));
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withName("Alice").build();
        Student bob = new StudentBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different student -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithStudent extends ModelStub {
        private final Student student;

        ModelStubWithStudent(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSameStudent(student);
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingStudentAdded extends ModelStub {
        final ArrayList<Student> studentsAdded = new ArrayList<>();

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return studentsAdded.stream().anyMatch(student::isSameStudent);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            studentsAdded.add(student);
        }

        @Override
        public void saveStateResiReg() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyResiReg getResiReg() {
            return new ResiReg();
        }
    }

    /**
     * A stub class for Storage.
     */
    private static class StorageStub implements Storage {
        @Override
        public Path getUserPrefsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getResiRegFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<ReadOnlyResiReg> readResiReg() throws DataConversionException, IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<ReadOnlyResiReg> readResiReg(Path filePath) throws DataConversionException, IOException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveResiReg(ReadOnlyResiReg resiReg) throws IOException {

            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void saveResiReg(ReadOnlyResiReg resiReg, Path filePath) throws IOException {


            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void archiveResiReg(ReadOnlyResiReg resiReg) throws IOException {
            throw new AssertionError("This method should not be called.");
        }
    }
}
