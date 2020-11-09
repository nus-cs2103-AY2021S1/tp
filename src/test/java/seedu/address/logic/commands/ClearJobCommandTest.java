package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.JobAddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearJobCommandTest {

    @Test
    public void execute_emptyJobAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearJobCommand(), model, ClearJobCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyJobAddressBook_success() {
        Model model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPersonAddressBook(),
                getTypicalJobAddressBook(), new UserPrefs());
        expectedModel.setJobAddressBook(new JobAddressBook());

        assertCommandSuccess(new ClearJobCommand(), model, ClearJobCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

