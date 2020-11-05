package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.AllocateCommand.MESSAGE_ALLOCATION_EXISTS;
import static seedu.resireg.logic.commands.AllocateCommand.MESSAGE_ROOM_ALREADY_ALLOCATED;
import static seedu.resireg.logic.commands.AllocateCommand.MESSAGE_STUDENT_ALREADY_ALLOCATED;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.showRoomAtIndex;
import static seedu.resireg.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.resireg.testutil.TypicalAllocations.getTypicalResiReg;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIFTH_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIFTH_STUDENT;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FOURTH_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FOURTH_STUDENT;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * Contains unit tests for {@code AllocateCommand}.
 * TypicalResiReg is assumed to have allocated the first 3 students to the first 3 rooms, so the 4th student/room
 * is free to be allocated.
 */
public class AllocateCommandTest {

    private CommandHistory history = new CommandHistory();

    private Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToAllocate = model.getFilteredStudentList().get(INDEX_FOURTH_STUDENT.getZeroBased());
        Room roomToAllocate = model.getFilteredRoomList().get(INDEX_FOURTH_ROOM.getZeroBased());
        Allocation toAllocate = new Allocation(roomToAllocate.getFloor(),
                roomToAllocate.getRoomNumber(), studentToAllocate.getStudentId());

        AllocateCommand allocateCommand = new AllocateCommand(INDEX_FOURTH_STUDENT, INDEX_FOURTH_ROOM);
        String expectedMessage = String.format(AllocateCommand.MESSAGE_SUCCESS, roomToAllocate.getRoomLabel(),
                studentToAllocate.getNameAsString());

        ModelManager expectedModel = new ModelManager(model.getResiReg(), new UserPrefs());
        expectedModel.addAllocation(toAllocate);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(allocateCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexStudentUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexStudent = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AllocateCommand allocateCommand = new AllocateCommand(outOfBoundIndexStudent, INDEX_FOURTH_ROOM);
        assertCommandFailure(allocateCommand, model, history, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexRoomUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexRoom = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        AllocateCommand allocateCommand = new AllocateCommand(INDEX_FOURTH_ROOM, outOfBoundIndexRoom);
        assertCommandFailure(allocateCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexStudentFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getStudentList().size());
        AllocateCommand allocateCommand = new AllocateCommand(outOfBoundIndex, INDEX_FIRST_ROOM);
        assertCommandFailure(allocateCommand, model, history, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexRoomFilteredList_throwsCommandException() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);
        Index outOfBoundIndex = INDEX_SECOND_ROOM;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getRoomList().size());
        AllocateCommand allocateCommand = new AllocateCommand(INDEX_FIRST_STUDENT, outOfBoundIndex);
        assertCommandFailure(allocateCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_studentAlreadyAllocated_throwsCommandException() {
        AllocateCommand allocateCommand = new AllocateCommand(INDEX_FIRST_STUDENT, INDEX_FOURTH_ROOM);
        assertCommandFailure(allocateCommand, model, history, MESSAGE_STUDENT_ALREADY_ALLOCATED);
    }

    @Test
    public void execute_roomAlreadyAllocated_throwsCommandException() {
        AllocateCommand allocateCommand = new AllocateCommand(INDEX_FOURTH_STUDENT, INDEX_FIRST_ROOM);
        assertCommandFailure(allocateCommand, model, history, MESSAGE_ROOM_ALREADY_ALLOCATED);
    }

    @Test
    public void execute_allocationAlreadyExists_throwsCommandException() {
        AllocateCommand allocateCommand = new AllocateCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_ROOM);
        assertCommandFailure(allocateCommand, model, history, MESSAGE_ALLOCATION_EXISTS);
    }

    @Test
    public void equals() {
        AllocateCommand allocateFirstCommand = new AllocateCommand(INDEX_FOURTH_STUDENT, INDEX_FOURTH_ROOM);
        AllocateCommand allocateSecondCommand = new AllocateCommand(INDEX_FIFTH_STUDENT, INDEX_FIFTH_ROOM);

        // same object -> returns true
        assertTrue(allocateFirstCommand.equals(allocateFirstCommand));

        // same values -> returns true
        AllocateCommand allocateFirstCommandCopy = new AllocateCommand(INDEX_FOURTH_STUDENT, INDEX_FOURTH_ROOM);
        assertTrue(allocateFirstCommand.equals(allocateFirstCommandCopy));

        // different types -> returns false
        assertFalse(allocateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(allocateFirstCommand.equals(null));

        // different allocation -> returns false
        assertFalse(allocateFirstCommand.equals(allocateSecondCommand));

        AllocateCommand allocateDifferentStudentCommand = new AllocateCommand(INDEX_FIFTH_STUDENT, INDEX_FOURTH_ROOM);
        AllocateCommand allocateDifferentRoomCommand = new AllocateCommand(INDEX_FOURTH_STUDENT, INDEX_FIFTH_ROOM);

        // different student -> returns false
        assertFalse(allocateFirstCommand.equals(allocateDifferentStudentCommand));
        assertFalse(allocateFirstCommand.equals(allocateDifferentRoomCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no allocations.
     */
    private void showNoAllocation(Model model) {
        model.updateFilteredAllocationList(p -> false);
        assertTrue(model.getFilteredAllocationList().isEmpty());
    }
}
