package seedu.address.logic.commands.modulelistcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalContactList;
import static seedu.address.testutil.todolist.TypicalTasks.getTypicalTodoList;

import org.junit.jupiter.api.Test;

import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.UserPrefs;

public class ClearModuleCommandTest {

    @Test
    public void execute_emptyModuleList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearModuleCommand(), model, ClearModuleCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyModuleList_success() {
        Model model = new ModelManager(
                getTypicalModuleList(),
                new ModuleList(),
                getTypicalContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        Model expectedModel = new ModelManager(
                getTypicalModuleList(),
                new ModuleList(),
                getTypicalContactList(),
                getTypicalTodoList(),
                new EventList(),
                new UserPrefs());
        expectedModel.setModuleList(new ModuleList());

        assertCommandSuccess(new ClearModuleCommand(), model, ClearModuleCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
