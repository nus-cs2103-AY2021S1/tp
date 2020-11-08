package seedu.address.model.module;

import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleListBuilder;

public class ModuleNotFoundExceptionTest {

    @Test
    void execute_setModule_throwsModuleNotFoundException() {
        ModuleList moduleList = new ModuleListBuilder().build();
        Model model = new ModelManager(moduleList, new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        assertThrows(ModuleNotFoundException.class, () -> model
                .setModule(new ModuleBuilder().withName(VALID_MODULENAME_ES2660).build(), new ModuleBuilder().build()));
    }
}
