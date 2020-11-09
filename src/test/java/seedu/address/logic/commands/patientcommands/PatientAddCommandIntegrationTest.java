package seedu.address.logic.commands.patientcommands;

import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.patientcommands.PatientCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code PatientAddCommand}.
 */
public class PatientAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPatient_success() {
        Patient validPatient = new PatientBuilder().build();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPatient(validPatient);

        assertCommandSuccess(new PatientAddCommand(validPatient), model,
                String.format(PatientAddCommand.MESSAGE_SUCCESS, validPatient), expectedModel);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient patientInList = model.getAddressBook().getPatientList().get(0);
        assertCommandFailure(new PatientAddCommand(patientInList), model, PatientAddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
