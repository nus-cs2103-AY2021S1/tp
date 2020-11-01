package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalPatients.HOON;
import static seedu.address.testutil.TypicalPatients.IDA;
import static seedu.address.testutil.TypicalPatients.getTypicalHospifyBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;

public class CountCommandTest {

    private Model model = new ModelManager(getTypicalHospifyBook(), new UserPrefs());

    @Test
    void execute_defaultCount_success() {
        String expectedMessage = "There are 7 record(s).";

        CountCommand countCommand = new CountCommand();

        assertCountSuccess(countCommand, model, expectedMessage);
    }

    @Test
    void execute_removePatients_success() {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        model.deletePatient(patientToDelete);

        String expectedMessage = "There are 6 record(s).";

        CountCommand countCommand = new CountCommand();

        assertCountSuccess(countCommand, model, expectedMessage);
    }

    @Test
    void execute_addPatient_success() {
        Patient firstPatientToAdd = HOON;
        Patient secondPatientToAdd = IDA;

        model.addPatient(HOON);
        model.addPatient(IDA);

        String expectedMessage = "There are 9 record(s).";

        CountCommand countCommand = new CountCommand();

        assertCountSuccess(countCommand, model, expectedMessage);
    }

    /**
     * Simplify the testing for count, since it does not change the state of model
     * @param countCommand
     * @param model
     * @param expectedMessage
     */
    private static void assertCountSuccess(CountCommand countCommand,
                                           Model model, String expectedMessage) {
        assertCommandSuccess(countCommand, model, expectedMessage, model);
    }
}
