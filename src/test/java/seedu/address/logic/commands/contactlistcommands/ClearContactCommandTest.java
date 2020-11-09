package seedu.address.logic.commands.contactlistcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalContactList;

import org.junit.jupiter.api.Test;

import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;

public class ClearContactCommandTest {

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        ClearContactCommand command = new ClearContactCommand();
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_emptyContactList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearContactCommand(), model, ClearContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyContactList_success() {
        Model model = new ModelManager(new ModuleList(), new ModuleList(),
                getTypicalContactList(), new TodoList(), new EventList(), new UserPrefs());
        Model expectedModel = new ModelManager(new ModuleList(), new ModuleList(),
                getTypicalContactList(), new TodoList(), new EventList(), new UserPrefs());
        expectedModel.setContactList(new ContactList());

        assertCommandSuccess(new ClearContactCommand(), model, ClearContactCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
