package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.showRoomAtIndex;
import static seedu.resireg.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.resireg.logic.commands.ReallocateCommand.MESSAGE_ROOM_ALREADY_ALLOCATED;
import static seedu.resireg.logic.commands.ReallocateCommand.MESSAGE_SAME_ROOM_ALLOCATED;
import static seedu.resireg.logic.commands.ReallocateCommand.MESSAGE_STUDENT_NOT_ALLOCATED;
import static seedu.resireg.testutil.TypicalAllocations.getTypicalResiReg;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIFTH_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FOURTH_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FOURTH_STUDENT;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;

import java.util.List;

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
 * Contains unit tests for {@code ReallocateCommand}.
 * TypicalResiReg is assumed to have allocated the first 3 students to the first 3 rooms, so the 4th student/room
 * is free to be allocated.
 */
public class ReallocateCommandTest {

    private CommandHistory history = new CommandHistory();

    private Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToReallocate = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Room roomToReallocate = model.getFilteredRoomList().get(INDEX_FOURTH_ROOM.getZeroBased());
        List<Allocation> lastShownListAllocation = model.getFilteredAllocationList();

        Allocation toReallocate = null;
        for (Allocation allocation : lastShownListAllocation) {
            if (studentToReallocate.getStudentId().equals(allocation.getStudentId())) {
                toReallocate = allocation;
            }
        }

        Allocation editedAllocate = new Allocation(roomToReallocate.getFloor(),
                roomToReallocate.getRoomNumber(), studentToReallocate.getStudentId());

        ReallocateCommand allocateCommand = new ReallocateCommand(INDEX_FIRST_STUDENT, INDEX_FOURTH_ROOM);
        String expectedMessage = String.format(ReallocateCommand.MESSAGE_SUCCESS,
                studentToReallocate.getNameAsString(),
                roomToReallocate.getRoomLabel());

        ModelManager expectedModel = new ModelManager(model.getResiReg(), new UserPrefs());
        expectedModel.setAllocation(toReallocate, editedAllocate);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(allocateCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexStudentUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexStudent = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        ReallocateCommand reallocateCommand = new ReallocateCommand(outOfBoundIndexStudent, INDEX_FOURTH_ROOM);
        assertCommandFailure(reallocateCommand, model, history, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexRoomUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexRoom = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        ReallocateCommand reallocateCommand = new ReallocateCommand(INDEX_FIRST_STUDENT, outOfBoundIndexRoom);
        assertCommandFailure(reallocateCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexStudentFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getStudentList().size());
        ReallocateCommand reallocateCommand = new ReallocateCommand(outOfBoundIndex, INDEX_FOURTH_ROOM);
        assertCommandFailure(reallocateCommand, model, history, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexRoomFilteredList_throwsCommandException() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);
        Index outOfBoundIndex = INDEX_SECOND_ROOM;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getRoomList().size());
        ReallocateCommand reallocateCommand = new ReallocateCommand(INDEX_FIRST_STUDENT, outOfBoundIndex);
        assertCommandFailure(reallocateCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_studentNotAllocated_throwsCommandException() {
        ReallocateCommand reallocateCommand = new ReallocateCommand(INDEX_FOURTH_STUDENT, INDEX_FIRST_ROOM);
        assertCommandFailure(reallocateCommand, model, history, MESSAGE_STUDENT_NOT_ALLOCATED);
    }

    @Test
    public void execute_sameRoomReallocated_throwsCommandException() {
        ReallocateCommand reallocateCommand = new ReallocateCommand(INDEX_FIRST_STUDENT, INDEX_FIRST_ROOM);
        assertCommandFailure(reallocateCommand, model, history, MESSAGE_SAME_ROOM_ALLOCATED);
    }

    @Test
    public void execute_roomAlreadyAllocated_throwsCommandException() {
        ReallocateCommand reallocateCommand = new ReallocateCommand(INDEX_FIRST_STUDENT, INDEX_SECOND_ROOM);
        assertCommandFailure(reallocateCommand, model, history, MESSAGE_ROOM_ALREADY_ALLOCATED);
    }

    @Test
    public void equals() {
        ReallocateCommand reallocateFirstCommand = new ReallocateCommand(INDEX_FIRST_STUDENT, INDEX_FOURTH_ROOM);
        ReallocateCommand reallocateSecondCommand = new ReallocateCommand(INDEX_SECOND_STUDENT, INDEX_FIFTH_ROOM);

        // same object -> returns true
        assertTrue(reallocateFirstCommand.equals(reallocateFirstCommand));

        // same values -> returns true
        ReallocateCommand reallocateFirstCommandCopy = new ReallocateCommand(INDEX_FIRST_STUDENT, INDEX_FOURTH_ROOM);
        assertTrue(reallocateFirstCommand.equals(reallocateFirstCommandCopy));

        // different types -> returns false
        assertFalse(reallocateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(reallocateFirstCommand.equals(null));

        // different reallocate -> returns false
        assertFalse(reallocateFirstCommand.equals(reallocateSecondCommand));

        ReallocateCommand reallocateDifferentStudentCommand =
                new ReallocateCommand(INDEX_SECOND_STUDENT, INDEX_FOURTH_ROOM);
        ReallocateCommand reallocateDifferentRoomCommand =
                new ReallocateCommand(INDEX_FIRST_STUDENT, INDEX_FIFTH_ROOM);

        // different student -> returns false
        assertFalse(reallocateFirstCommand.equals(reallocateDifferentStudentCommand));
        assertFalse(reallocateFirstCommand.equals(reallocateDifferentRoomCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no allocations.
     */
    private void showNoAllocation(Model model) {
        model.updateFilteredAllocationList(p -> false);
        assertTrue(model.getFilteredAllocationList().isEmpty());
    }
}
