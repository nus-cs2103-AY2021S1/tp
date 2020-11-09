package seedu.address.logic.commands.modulelistcommands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showArchivedModuleAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewArchiveCommand.
 */
public class ViewArchiveCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ModuleList(), getTypicalModuleList(), new ContactList(), new TodoList(),
                new EventList(), new UserPrefs());
        expectedModel = new ModelManager(new ModuleList(), model.getArchivedModuleList(), new ContactList(),
                new TodoList(), new EventList(), new UserPrefs());
        expectedModel.displayArchivedModules();
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewArchivedModulesCommand(), model,
                ViewArchivedModulesCommand.MESSAGE_VIEW_ARCHIVED_MODULES_SUCCESS, expectedModel);
    }
    @Test
    public void execute_listIsFiltered_showsEverything() {
        showArchivedModuleAtIndex(model, INDEX_FIRST_MODULE);
        assertCommandSuccess(new ViewArchivedModulesCommand(), model,
                ViewArchivedModulesCommand.MESSAGE_VIEW_ARCHIVED_MODULES_SUCCESS, expectedModel);
    }
}
