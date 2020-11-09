package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FORTH_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalHospifyBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalHospifyBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        ModelManager expectedModel = new ModelManager(model.getHospifyBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNricUnfilteredList_success() {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(patientToDelete.getNric());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        ModelManager expectedModel = new ModelManager(model.getHospifyBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidNricUnfilteredList_throwsCommandException() {
        Nric noneExistNric = new Nric("N1298329I");
        DeleteCommand deleteCommand = new DeleteCommand(noneExistNric);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_NRIC);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PATIENT);

        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        Model expectedModel = new ModelManager(model.getHospifyBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNricFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PATIENT);

        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(patientToDelete.getNric());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        Model expectedModel = new ModelManager(model.getHospifyBook(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PATIENT);

        Index outOfBoundIndex = INDEX_SECOND_PATIENT;
        // ensures that outOfBoundIndex is still in bounds of Hospify list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHospifyBook().getPatientList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PATIENT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PATIENT);

        Patient thirdToDelete = model.getFilteredPatientList().get(INDEX_THIRD_PATIENT.getZeroBased());
        DeleteCommand deleteThirdCommand = new DeleteCommand(thirdToDelete.getNric());

        Patient forthToDelete = model.getFilteredPatientList().get(INDEX_FORTH_PATIENT.getZeroBased());
        DeleteCommand deleteForthCommand = new DeleteCommand(forthToDelete.getNric());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
        assertTrue(deleteThirdCommand.equals(deleteThirdCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PATIENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        DeleteCommand deleteThirdCommandCopy = new DeleteCommand(thirdToDelete.getNric());
        assertTrue(deleteThirdCommand.equals(deleteThirdCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));
        assertFalse(deleteThirdCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));
        assertFalse(deleteThirdCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
        assertFalse(deleteThirdCommand.equals(deleteForthCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
