package seedu.address.logic.commands.person;

import static seedu.address.logic.commands.person.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.person.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AddressBook;
import seedu.address.model.person.ModelPerson;
import seedu.address.model.person.ModelPersonManager;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_failure() {
        ModelPerson modelPerson = new ModelPersonManager();

        assertCommandFailure(
                new ClearCommand(
                ), modelPerson, String.format(Messages.MESSAGE_INVALID_CONTACT_LIST_EMPTY, ClearCommand.COMMAND_WORD));
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        ModelPerson modelPerson = new ModelPersonManager(getTypicalAddressBook(), new UserPrefs());
        ModelPerson expectedModelPerson = new ModelPersonManager(getTypicalAddressBook(), new UserPrefs());
        expectedModelPerson.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearCommand(), modelPerson, ClearCommand.MESSAGE_SUCCESS, expectedModelPerson);
    }

}
