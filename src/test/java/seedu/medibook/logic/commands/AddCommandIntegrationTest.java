package seedu.medibook.logic.commands;

import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.medibook.model.Model;
import seedu.medibook.model.ModelManager;
import seedu.medibook.model.UserPrefs;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMediBook(), new UserPrefs());
    }

    @Test
    public void execute_newPatient_success() {
        Patient validPatient = new PatientBuilder().build();

        Model expectedModel = new ModelManager(model.getMediBook(), new UserPrefs());
        expectedModel.addPatient(validPatient);

        assertCommandSuccess(new AddCommand(validPatient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPatient), expectedModel);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient patientInList = model.getMediBook().getPatientList().get(0);
        assertCommandFailure(new AddCommand(patientInList), model, AddCommand.MESSAGE_DUPLICATE_PATIENT);
    }

}
