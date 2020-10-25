package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.showRoomAtIndex;
import static seedu.resireg.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.resireg.testutil.TypicalAllocations.getTypicalAddressBook;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIFTH_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FOURTH_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_ROOM;

import org.junit.jupiter.api.Test;
import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * Contains unit tests for {@code AllocateCommand}.
 * TypicalAddressBook is assumed to have allocated the first 3 students to the first 3 rooms, so the 4th student/room
 * is free to be allocated.
 */
public class AllocateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToAllocate = model.getFilteredStudentList().get(INDEX_FOURTH_PERSON.getZeroBased());
        Room roomToAllocate = model.getFilteredRoomList().get(INDEX_FOURTH_ROOM.getZeroBased());
        Allocation toAllocate = new Allocation(roomToAllocate.getFloor(),
                roomToAllocate.getRoomNumber(), studentToAllocate.getStudentId());

        AllocateCommand allocateCommand = new AllocateCommand(INDEX_FOURTH_PERSON, INDEX_FOURTH_ROOM);
        String expectedMessage = String.format(AllocateCommand.MESSAGE_SUCCESS, roomToAllocate.getRoomLabel(),
                studentToAllocate.getName().fullName);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addAllocation(toAllocate);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(allocateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexStudentUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexStudent = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        AllocateCommand allocateCommand = new AllocateCommand(outOfBoundIndexStudent, INDEX_FOURTH_ROOM);
        assertCommandFailure(allocateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexRoomUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexRoom = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        AllocateCommand allocateCommand = new AllocateCommand(INDEX_FOURTH_ROOM, outOfBoundIndexRoom);
        assertCommandFailure(allocateCommand, model, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexStudentFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());
        AllocateCommand allocateCommand = new AllocateCommand(outOfBoundIndex, INDEX_FIRST_ROOM);
        assertCommandFailure(allocateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexRoomFilteredList_throwsCommandException() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);
        Index outOfBoundIndex = INDEX_SECOND_ROOM;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getRoomList().size());
        AllocateCommand allocateCommand = new AllocateCommand(INDEX_FIRST_PERSON, outOfBoundIndex);
        assertCommandFailure(allocateCommand, model, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AllocateCommand allocateFirstCommand = new AllocateCommand(INDEX_FOURTH_PERSON, INDEX_FOURTH_ROOM);
        AllocateCommand allocateSecondCommand = new AllocateCommand(INDEX_FIFTH_PERSON, INDEX_FIFTH_ROOM);

        // same object -> returns true
        assertTrue(allocateFirstCommand.equals(allocateFirstCommand));

        // same values -> returns true
        AllocateCommand allocateFirstCommandCopy = new AllocateCommand(INDEX_FOURTH_PERSON, INDEX_FOURTH_ROOM);
        assertTrue(allocateFirstCommand.equals(allocateFirstCommandCopy));

        // different types -> returns false
        assertFalse(allocateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(allocateFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(allocateFirstCommand.equals(allocateSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no allocations.
     */
    private void showNoAllocation(Model model) {
        model.updateFilteredAllocationList(p -> false);
        assertTrue(model.getFilteredAllocationList().isEmpty());
    }
}
