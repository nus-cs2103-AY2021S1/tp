package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;

public class CountCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_defaultCount_success() {
        String expectedMessage = "There are 7 records.";

        CountCommand countCommand = new CountCommand();

        assertCountSuccess(countCommand, model, expectedMessage);
    }

    @Test
    void execute_removePatients_success() {
        Patient patientToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.deletePerson(patientToDelete);

        String expectedMessage = "There are 6 records.";

        CountCommand countCommand = new CountCommand();

        assertCountSuccess(countCommand, model, expectedMessage);
    }

    @Test
    void execute_addPatient_success() {
        Patient firstPatientToAdd = HOON;
        Patient secondPatientToAdd = IDA;

        model.addPerson(HOON);
        model.addPerson(IDA);

        String expectedMessage = "There are 9 records.";

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
