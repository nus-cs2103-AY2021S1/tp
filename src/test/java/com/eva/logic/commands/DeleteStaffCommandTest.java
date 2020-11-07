package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.assertCommandSuccess;
import static com.eva.logic.commands.CommandTestUtil.showStaffAtIndex;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_STAFFS;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.staff.TypicalStaffs.getTypicalStaffDatabase;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.staff.Staff;

public class DeleteStaffCommandTest {

    private Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
            getTypicalApplicantDatabase(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Staff staffToDelete = model.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStaffCommand.MESSAGE_DELETE_STAFF_SUCCESS, staffToDelete);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        ModelManager expectedModel = new ModelManager(model.getPersonDatabase(), model.getStaffDatabase(),
                model.getApplicantDatabase(), new UserPrefs());
        expectedModel.deleteStaff(staffToDelete);

        assertCommandSuccess(deleteStaffCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStaffList().size() + 1);
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(outOfBoundIndex);

        assertCommandFailure(deleteStaffCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);

        Staff staffToDelete = model.getFilteredStaffList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStaffCommand.MESSAGE_DELETE_STAFF_SUCCESS, staffToDelete);
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(model.getPersonDatabase(), model.getStaffDatabase(),
                model.getApplicantDatabase(), new UserPrefs());
        expectedModel.deleteStaff(staffToDelete);
        expectedModel.setPanelState(PanelState.STAFF_LIST);
        expectedModel.updateFilteredStaffList(PREDICATE_SHOW_ALL_STAFFS);

        assertCommandSuccess(deleteStaffCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStaffAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of eva database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStaffDatabase().getPersonList().size());

        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(outOfBoundIndex);

        assertCommandFailure(deleteStaffCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteStaffCommand deleteStaffFirstCommand = new DeleteStaffCommand(INDEX_FIRST_PERSON);
        DeleteStaffCommand deleteStaffSecondCommand = new DeleteStaffCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteStaffFirstCommand.equals(deleteStaffFirstCommand));

        // same values -> returns true
        DeleteStaffCommand deleteStaffFirstCommandCopy = new DeleteStaffCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteStaffFirstCommand.equals(deleteStaffFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteStaffFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteStaffFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteStaffFirstCommand.equals(deleteStaffSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStaff(Model model) {
        model.updateFilteredStaffList(p -> false);

        assertTrue(model.getFilteredStaffList().isEmpty());
    }
}
