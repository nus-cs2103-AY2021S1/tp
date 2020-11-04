package seedu.address.logic.commands.modulelistcommands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.modulelistcommands.modulelistexceptions.CapCalculationException;
import seedu.address.model.*;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleLesson;
import seedu.address.testutil.Assert;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

public class CalculateCapCommandTest {
    private Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
            new ContactList(), new TodoList(), new EventList(), new UserPrefs());
    @Test
    public void execute_noCompletedModules_throwsCommandException() {
        Model blankModel = new ModelManager(new ModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        assertThrows(CommandException.class, () -> new CalculateCapCommand().execute(blankModel));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CalculateCapCommand().execute(null));
    }

    @Test
    public void execute_filteredListSameAsUnfilteredList_calculateCapSuccess() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        String expectedMessage;
        try {
            expectedMessage = new CalculateCapCommand().execute(model).getFeedbackToUser();
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
        ModelManager expectedModel = new ModelManager(model.getModuleList(), model.getArchivedModuleList(),
                model.getContactList(), model.getTodoList(), model.getEventList(), new UserPrefs());
        showModuleAtIndex(expectedModel, INDEX_FIRST_MODULE);
        assertCommandSuccess(new CalculateCapCommand(), model, expectedMessage, expectedModel);
    }
}
