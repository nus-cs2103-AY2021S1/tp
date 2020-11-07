package seedu.address.logic.commands.patient;

//@author LeeMingDe

import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientRecords;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.command.GeneralCommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RoomList;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;

//@author LeeMingDe
/**
 * Contains integration tests (interaction with the Model) for {@code EditPatientCommand}.
 */
public class EditPatientCommandIntegrationTest {

    //patient records -> [ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE]
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPatientRecords(), new RoomList(), new UserPrefs());
    }

    @AfterEach
    public void reset() {
        model = new ModelManager(getTypicalPatientRecords(), new RoomList(), new UserPrefs());
    }

    @Test
    public void execute_editPatient_success() {
        Patient editedPatient = new PatientBuilder().withName("John Doe").build();

        EditPatientCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(ALICE.getName(), descriptor);
        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);
        ModelManager expectedModel =
            new ModelManager(model.getPatientRecords(), new RoomList(), new UserPrefs());
        expectedModel.setPatient(ALICE, editedPatient);
        assertCommandSuccess(editPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient alice = model.getFilteredPatientList().get(0);
        Patient benson = model.getFilteredPatientList().get(1);
        EditPatientCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(alice).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(benson.getName(), descriptor);

        assertCommandFailure(editPatientCommand, model, EditPatientCommand.MESSAGE_DUPLICATE_PATIENT);
    }
}
