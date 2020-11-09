package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PersonAddressBook;
import seedu.address.model.UserPrefs;

public class ClearPersonCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearPersonCommand(), model, ClearPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPersonAddressBook(),
                getTypicalJobAddressBook(), new UserPrefs());
        expectedModel.setPersonAddressBook(new PersonAddressBook());

        assertCommandSuccess(new ClearPersonCommand(), model, ClearPersonCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
