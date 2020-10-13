package seedu.address.logic.commands.patient;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientManager;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.listmanagers.AppointmentManager;
import seedu.address.model.listmanagers.PatientManager;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Remark;
import seedu.address.model.userprefs.UserPrefs;
import seedu.address.testutil.PatientBuilder;

class RemarkPatientCommandTest {
    private static final String REMARK_STUB = "Some remark";
    private final Model model = new ModelManager(getTypicalPatientManager(),
            new AppointmentManager(), new UserPrefs());

    @Test
    void execute_addRemarkUnfilteredList_success() {
        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        Patient editedPatient = new PatientBuilder(firstPatient).withRemark(REMARK_STUB).build();

        RemarkPatientCommand remarkPatientCommand = new RemarkPatientCommand(INDEX_FIRST_PATIENT,
                new Remark(editedPatient.getRemark().value));

        String expectedMessage = String.format(RemarkPatientCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PatientManager(model.getPatientManager()),
                new AppointmentManager(), new UserPrefs());
        expectedModel.setPatient(firstPatient, editedPatient);

        assertCommandSuccess(remarkPatientCommand, model, expectedMessage, expectedModel);
    }
}
