package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.AMY;
import static seedu.address.testutil.TypicalPatients.BENSON;
import static seedu.address.testutil.TypicalPatients.BOB;
import static seedu.address.testutil.TypicalPatients.HOON;
import static seedu.address.testutil.TypicalPatients.IDA;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class SortCommandTest {

    @Test
    public void execute_sortNameEmptyHospify_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SortCommand("name"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortNricEmptyHospify_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new SortCommand("nric"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortName_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.addPatient(HOON);
        model.addPatient(IDA);
        model.addPatient(ALICE);

        expectedModel.addPatient(ALICE);
        expectedModel.addPatient(HOON);
        expectedModel.addPatient(IDA);

        assertCommandSuccess(new SortCommand("name"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortNric_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.addPatient(AMY);
        model.addPatient(BOB);
        model.addPatient(BENSON);
        model.addPatient(HOON);

        expectedModel.addPatient(BENSON);
        expectedModel.addPatient(HOON);
        expectedModel.addPatient(AMY);
        expectedModel.addPatient(BOB);

        assertCommandSuccess(new SortCommand("nric"), model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
