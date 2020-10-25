package seedu.resireg.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.*;
import static seedu.resireg.testutil.TypicalAllocations.getTypicalAddressBook;
import static seedu.resireg.testutil.TypicalIndexes.*;

/**
 * Contains unit tests for {@code AllocateCommand}.
 * TypicalAddressBook is assumed to have allocated the first 3 students to the first 3 rooms, so the 4th student/room
 * is free to be allocated.
 */
public class DeallocateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Room roomToDeallocate = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        Student studentToDeallocate = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeallocateCommand deallocateCommand = new DeallocateCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeallocateCommand.MESSAGE_SUCCESS,
                roomToDeallocate.getFloor().toString() + ':' + roomToDeallocate.getRoomNumber(),
                studentToDeallocate.getName().fullName);

        List<Allocation> lastShownListAllocation = model.getFilteredAllocationList();
        Allocation toDeallocate = null;
        for (Allocation allocation : lastShownListAllocation) {
            if (studentToDeallocate.getStudentId().equals(allocation.getStudentId())) {
                toDeallocate = allocation;
            }
        }

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeAllocation(toDeallocate);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(deallocateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexStudentUnfilteredList_throwsCommandException() {
        Index outOfBoundIndexStudent = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeallocateCommand deallocateCommand = new DeallocateCommand(outOfBoundIndexStudent);
        assertCommandFailure(deallocateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Student studentToDeallocate = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Room roomToDeallocate = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());

        List<Allocation> lastShownListAllocation = model.getFilteredAllocationList();
        Allocation toDeallocate = null;
        for (Allocation allocation : lastShownListAllocation) {
            if (studentToDeallocate.getStudentId().equals(allocation.getStudentId())) {
                toDeallocate = allocation;
            }
        }

        DeallocateCommand deallocateCommand = new DeallocateCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(DeallocateCommand.MESSAGE_SUCCESS,
                roomToDeallocate.getFloor().toString() + ':' + roomToDeallocate.getRoomNumber(),
                studentToDeallocate.getName().fullName);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.removeAllocation(toDeallocate);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(deallocateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexStudentFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStudentList().size());
        DeallocateCommand deallocateCommand = new DeallocateCommand(outOfBoundIndex);
        assertCommandFailure(deallocateCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeallocateCommand deallocateFirstCommand = new DeallocateCommand(INDEX_FIRST_PERSON);
        DeallocateCommand deallocateSecondCommand = new DeallocateCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deallocateFirstCommand.equals(deallocateFirstCommand));

        // same values -> returns true
        DeallocateCommand deallocateFirstCommandCopy = new DeallocateCommand(INDEX_FIRST_PERSON);
        assertTrue(deallocateFirstCommand.equals(deallocateFirstCommandCopy));

        // different types -> returns false
        assertFalse(deallocateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deallocateFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deallocateFirstCommand.equals(deallocateSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no allocations.
     */
    private void showNoAllocation(Model model) {
        model.updateFilteredAllocationList(p -> false);
        assertTrue(model.getFilteredAllocationList().isEmpty());
    }
}
