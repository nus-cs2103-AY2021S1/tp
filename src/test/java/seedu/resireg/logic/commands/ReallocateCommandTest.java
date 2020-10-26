package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.showRoomAtIndex;
import static seedu.resireg.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.resireg.testutil.TypicalAllocations.getTypicalResiReg;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIFTH_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FOURTH_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_ROOM;

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
        Student studentToReallocate = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
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

        ReallocateCommand allocateCommand = new ReallocateCommand(INDEX_FIRST_PERSON, INDEX_FOURTH_ROOM);
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
        assertCommandFailure(reallocateCommand, model, history, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexRoomUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexRoom = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        ReallocateCommand reallocateCommand = new ReallocateCommand(INDEX_FIRST_PERSON, outOfBoundIndexRoom);
        assertCommandFailure(reallocateCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexStudentFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getStudentList().size());
        ReallocateCommand reallocateCommand = new ReallocateCommand(outOfBoundIndex, INDEX_FOURTH_ROOM);
        assertCommandFailure(reallocateCommand, model, history, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexRoomFilteredList_throwsCommandException() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);
        Index outOfBoundIndex = INDEX_SECOND_ROOM;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getRoomList().size());
        ReallocateCommand reallocateCommand = new ReallocateCommand(INDEX_FIRST_PERSON, outOfBoundIndex);
        assertCommandFailure(reallocateCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ReallocateCommand reallocateFirstCommand = new ReallocateCommand(INDEX_FIRST_PERSON, INDEX_FOURTH_ROOM);
        ReallocateCommand reallocateSecondCommand = new ReallocateCommand(INDEX_SECOND_PERSON, INDEX_FIFTH_ROOM);

        // same object -> returns true
        assertTrue(reallocateFirstCommand.equals(reallocateFirstCommand));

        // same values -> returns true
        ReallocateCommand reallocateFirstCommandCopy = new ReallocateCommand(INDEX_FIRST_PERSON, INDEX_FOURTH_ROOM);
        assertTrue(reallocateFirstCommand.equals(reallocateFirstCommandCopy));

        // different types -> returns false
        assertFalse(reallocateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(reallocateFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(reallocateFirstCommand.equals(reallocateSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no allocations.
     */
    private void showNoAllocation(Model model) {
        model.updateFilteredAllocationList(p -> false);
        assertTrue(model.getFilteredAllocationList().isEmpty());
    }
}
