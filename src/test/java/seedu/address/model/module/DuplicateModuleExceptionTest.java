package seedu.address.model.module;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleListBuilder;


public class DuplicateModuleExceptionTest {

    @Test
    void execute_addModule_throwsDuplicateModuleException() {
        ModuleList moduleList = new ModuleListBuilder().build();
        Module module = new ModuleBuilder().build();
        moduleList.addModule(module);
        Model model = new ModelManager(moduleList, new ModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        assertThrows(DuplicateModuleException.class, () -> model.addModule(module));
    }

}
