package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;

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

    @Test
    public void equals() {

        CalculateCapCommand calculateCapFirstCommand = new CalculateCapCommand();

        // same object -> returns true
        assertTrue(calculateCapFirstCommand.equals(calculateCapFirstCommand));

        // same values -> returns true
        CalculateCapCommand calculateCapFirstCommandCopy = new CalculateCapCommand();
        assertTrue(calculateCapFirstCommand.equals(calculateCapFirstCommandCopy));

        // different types -> returns false
        assertFalse(calculateCapFirstCommand.equals(8));

        // null -> returns false
        assertFalse(calculateCapFirstCommand.equals(null));
    }
}
