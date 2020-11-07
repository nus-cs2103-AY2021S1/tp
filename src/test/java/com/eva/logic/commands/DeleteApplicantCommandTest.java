package com.eva.logic.commands;

import static com.eva.logic.commands.CommandTestUtil.assertChangePanelCommandSuccess;
import static com.eva.logic.commands.CommandTestUtil.assertCommandFailure;
import static com.eva.logic.commands.CommandTestUtil.showApplicantAtIndex;
import static com.eva.logic.commands.DeleteApplicantCommand.MESSAGE_WRONG_PANEL;
import static com.eva.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;
import static com.eva.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static com.eva.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static com.eva.testutil.TypicalPersons.getTypicalApplicantDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static com.eva.testutil.TypicalPersons.getTypicalStaffDatabase;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.eva.commons.core.Messages;
import com.eva.commons.core.PanelState;
import com.eva.commons.core.index.Index;
import com.eva.model.Model;
import com.eva.model.ModelManager;
import com.eva.model.UserPrefs;
import com.eva.model.person.applicant.Applicant;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteApplicantCommandTest {

    private Model model = new ModelManager(getTypicalPersonDatabase(), getTypicalStaffDatabase(),
            getTypicalApplicantDatabase(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteApplicantCommand deleteCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteApplicantCommand.MESSAGE_DELETE_APPLICANT_SUCCESS,
                applicantToDelete.getName());

        ModelManager expectedModel = new ModelManager(model.getPersonDatabase(), model.getStaffDatabase(),
                model.getApplicantDatabase(), new UserPrefs());
        expectedModel.deleteApplicant(applicantToDelete);

        model.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);

        assertChangePanelCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_validIndexUnfilteredWrongListPanel_failure() {
        DeleteApplicantCommand deleteCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_AT_PANEL,
                MESSAGE_WRONG_PANEL);

        model.setPanelState(PanelState.STAFF_LIST);

        assertCommandFailure(deleteCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteApplicantCommand deleteCommand = new DeleteApplicantCommand(outOfBoundIndex);
        model.setPanelState(PanelState.APPLICANT_LIST);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Applicant applicantToDelete = model.getFilteredApplicantList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteApplicantCommand deleteCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteApplicantCommand.MESSAGE_DELETE_APPLICANT_SUCCESS,
                applicantToDelete.getName());

        Model expectedModel = new ModelManager(model.getPersonDatabase(), model.getStaffDatabase(),
                model.getApplicantDatabase(), new UserPrefs());

        expectedModel.deleteApplicant(applicantToDelete);
        showNoApplicant(expectedModel);
        model.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.setPanelState(PanelState.APPLICANT_LIST);
        expectedModel.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);

        assertChangePanelCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicantAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of eva database list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPersonDatabase().getPersonList().size());
        model.setPanelState(PanelState.APPLICANT_LIST);
        DeleteApplicantCommand deleteCommand = new DeleteApplicantCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void equals() {
        DeleteApplicantCommand deleteFirstCommand = new DeleteApplicantCommand(INDEX_FIRST_PERSON);
        DeleteApplicantCommand deleteSecondCommand = new DeleteApplicantCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteApplicantCommand deleteFirstCommandCopy = new DeleteApplicantCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoApplicant(Model model) {
        model.updateFilteredApplicantList(p -> false);

        assertTrue(model.getFilteredApplicantList().isEmpty());
    }
}
