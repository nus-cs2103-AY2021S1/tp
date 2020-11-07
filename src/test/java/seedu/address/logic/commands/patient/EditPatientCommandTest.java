package seedu.address.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.command.GeneralCommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.command.PatientCommandTestUtil.DESC_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.DESC_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_BESON;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_NAME_GEORGE;
import static seedu.address.testutil.command.PatientCommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.patient.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientRecords;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.TypicalPatients;

//@author LeeMingDe
/**
 * Contains integration tests (interaction with only PatientRecords in the Model) and unit tests for
 * {@code EditPatientCommand}.
 */
public class EditPatientCommandTest {

    private Model model = new ModelManager(getTypicalPatientRecords(), new RoomList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Patient editedPatient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(new Name(VALID_NAME_ALICE), descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PatientRecords(model.getPatientRecords()),
                new RoomList(), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);

        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        PatientBuilder patientInList = new PatientBuilder(TypicalPatients.GEORGE);
        Patient editedPatient = patientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        EditPatientCommand editCommand = new EditPatientCommand(new Name(VALID_NAME_GEORGE), descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PatientRecords(model.getPatientRecords()),
                new RoomList(), new UserPrefs());
        expectedModel.setPatient(TypicalPatients.GEORGE, editedPatient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPatientCommand editCommand = new EditPatientCommand(new Name(VALID_NAME_ALICE),
            new EditPatientDescriptor());
        Patient editedPatient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PatientRecords(model.getPatientRecords()),
                new RoomList(), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);

        Patient patientInFilteredList = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        Patient editedPatient = new PatientBuilder(patientInFilteredList).withName(VALID_NAME_BOB).build();
        EditPatientCommand editCommand = new EditPatientCommand(new Name(VALID_NAME_ALICE),
                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PatientRecords(model.getPatientRecords()),
                new RoomList(), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePatientUnfilteredList_failure() {
        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(firstPatient).build();
        EditPatientCommand editCommand = new EditPatientCommand(new Name(VALID_NAME_BESON), descriptor);

        assertCommandFailure(editCommand, model, EditPatientCommand.MESSAGE_DUPLICATE_PATIENT);
    }

    @Test
    public void execute_duplicatePatientFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);

        // Edit patient in filtered list into a duplicate in address book
        Patient patientInList = model.getPatientRecords().getReadOnlyList().get(INDEX_SECOND_PATIENT.getZeroBased());
        EditPatientCommand editCommand = new EditPatientCommand(new Name(VALID_NAME_ALICE),
                new EditPatientDescriptorBuilder(patientInList).build());

        assertCommandFailure(editCommand, model, EditPatientCommand.MESSAGE_DUPLICATE_PATIENT);
    }

    @Test
    public void execute_invalidPatientNameUnfilteredList_failure() {
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditPatientCommand editCommand = new EditPatientCommand(new Name(VALID_NAME_AMY), descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PATIENT_NAME);
    }

    @Test
    public void equals() {
        final EditPatientCommand standardCommand = new EditPatientCommand(new Name(VALID_NAME_AMY), DESC_AMY);

        // Same values -> returns true
        EditPatientDescriptor copyDescriptor = new EditPatientDescriptor(DESC_AMY);
        EditPatientCommand commandWithSameValues = new EditPatientCommand(new Name(VALID_NAME_AMY), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // Same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // Different types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // Different index -> returns false
        assertFalse(standardCommand.equals(new EditPatientCommand(new Name(VALID_NAME_BOB), DESC_AMY)));

        // Different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPatientCommand(new Name(VALID_NAME_AMY), DESC_BOB)));
    }
}
//@author LeeMingDe
