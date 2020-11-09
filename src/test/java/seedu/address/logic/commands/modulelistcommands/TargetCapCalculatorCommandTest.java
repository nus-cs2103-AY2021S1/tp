package seedu.address.logic.commands.modulelistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
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

public class TargetCapCalculatorCommandTest {
    private static final double VALID_TARGET_CAP = 5.0;
    private static final double INVALID_TARGET_CAP = 6.0;
    private Model model = new ModelManager(getTypicalModuleList(), new ModuleList(),
            new ContactList(), new TodoList(), new EventList(), new UserPrefs());
    @Test
    public void execute_noCompletedModules_throwsCommandException() {
        Model blankModel = new ModelManager(new ModuleList(), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        assertThrows(CommandException.class, () -> new TargetCapCalculatorCommand(VALID_TARGET_CAP)
                        .execute(blankModel));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TargetCapCalculatorCommand(VALID_TARGET_CAP)
                .execute(null));
    }

    @Test
    public void execute_invalidTargetCap_throwsCommandException() {
        TargetCapCalculatorCommand targetCapCalculatorCommand = new TargetCapCalculatorCommand(INVALID_TARGET_CAP);
        assertCommandFailure(targetCapCalculatorCommand, model, TargetCapCalculatorCommand.MESSAGE_IMPOSSIBLE_TARGET);
    }

    @Test
    public void execute_filteredListSameAsUnfilteredList_calculateCapSuccess() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        String expectedMessage;
        try {
            expectedMessage = new TargetCapCalculatorCommand(VALID_TARGET_CAP).execute(model).getFeedbackToUser();
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
        ModelManager expectedModel = new ModelManager(model.getModuleList(), model.getArchivedModuleList(),
                model.getContactList(), model.getTodoList(), model.getEventList(), new UserPrefs());
        showModuleAtIndex(expectedModel, INDEX_FIRST_MODULE);
        assertCommandSuccess(new TargetCapCalculatorCommand(VALID_TARGET_CAP), model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        TargetCapCalculatorCommand targetCapFirstCommand = new TargetCapCalculatorCommand(VALID_TARGET_CAP);
        TargetCapCalculatorCommand targetCapSecondCommand = new TargetCapCalculatorCommand(4.5);

        // same object -> returns true
        assertTrue(targetCapFirstCommand.equals(targetCapFirstCommand));

        // same values -> returns true
        TargetCapCalculatorCommand targetCapFirstCommandCopy = new TargetCapCalculatorCommand(VALID_TARGET_CAP);
        assertTrue(targetCapFirstCommand.equals(targetCapFirstCommandCopy));

        // different types -> returns false
        assertFalse(targetCapFirstCommand.equals(8));

        // null -> returns false
        assertFalse(targetCapFirstCommand.equals(null));

        // different value -> returns false
        assertFalse(targetCapFirstCommand.equals(targetCapSecondCommand));
    }
}
