package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModuleCodes.CODE_FIRST_MODULE;
import static seedu.address.testutil.TypicalModuleCodes.CODE_SECOND_MODULE;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalGradPad(), new UserPrefs());

    @Test
    public void execute_validModuleCodeUnfilteredList_success() {
        Module moduleToDelete = model.getFilteredModuleList().stream()
                .filter(module -> module.getModuleCode().equals(CODE_FIRST_MODULE)).findFirst().get();
        DeleteCommand deleteCommand = new DeleteCommand(CODE_FIRST_MODULE);

        String expectedMessage = String.format(MESSAGE_DELETE_SUCCESS, moduleToDelete);

        ModelManager expectedModel = new ModelManager(model.getGradPad(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleCodeUnfilteredList_throwsCommandException() {
        ModuleCode invalidModuleCode = new ModuleCode("AA0000");
        DeleteCommand deleteCommand = new DeleteCommand(invalidModuleCode);
        assertCommandFailure(deleteCommand, model,
                String.format(Messages.MESSAGE_INVALID_MODULE, invalidModuleCode.toString()));
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(CODE_FIRST_MODULE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(CODE_SECOND_MODULE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(CODE_FIRST_MODULE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoModule(Model model) {
        model.updateFilteredModuleList(p -> false);

        assertTrue(model.getFilteredModuleList().isEmpty());
    }
}
