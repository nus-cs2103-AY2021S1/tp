package seedu.address.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

//@@author chiamyunqing
/**
 * Contains integration tests (interaction with only PatientRecords in the Model) and unit tests for
 * {@code DeletePatientCommand}.
 */
public class DeletePatientCommandTest {

    private Model model = new ModelManager(getTypicalPatientRecords(), new RoomList(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        Name patientToDeleteName = patientToDelete.getName();
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(patientToDeleteName);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        ModelManager expectedModel =
                new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());
        expectedModel.deletePatient(patientToDelete);

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        Name invalidPatientName = new Name("Obviously invalid name");
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(invalidPatientName);
        assertCommandFailure(deletePatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_NAME);
    }

    @Test
    public void equals() {
        Name jane1 = new Name("Jane Doe");
        Name jane2 = new Name("jane doe");
        Name john = new Name("John Doe");

        DeletePatientCommand deleteFirstCommand = new DeletePatientCommand(jane1);
        DeletePatientCommand deleteSecondCommand = new DeletePatientCommand(john);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same names of patient -> returns true
        DeletePatientCommand deleteFirstCommandCopy = new DeletePatientCommand(jane2);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
