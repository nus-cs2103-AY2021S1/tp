package seedu.address.logic.commands.todolistcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalContactList;
import static seedu.address.testutil.todolist.TypicalTasks.getTypicalTodoList;

import org.junit.jupiter.api.Test;

import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;

public class ClearTaskCommandTest {

    @Test
    public void execute_emptyTodoList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearTaskCommand(), model, ClearTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTodoList_success() {
        Model model = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        Model expectedModel = new ModelManager(
                new ModuleList(),
                new ModuleList(),
                new ContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        expectedModel.setTodoList(new TodoList());

        assertCommandSuccess(new ClearTaskCommand(), model, ClearTaskCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
