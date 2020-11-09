package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ForceDeleteCommand}.
 */
public class ForceDeleteCommandTest {

    private Model model = new ModelManager(getTypicalGradPad(), new UserPrefs());

    @Test
    public void execute_validModuleCodeUnfilteredList_success() {
        Module moduleToDelete = model.getFilteredModuleList().stream()
            .filter(module -> module.getModuleCode().equals(CODE_FIRST_MODULE)).findFirst().get();
        ForceDeleteCommand forceDeleteCommand = new ForceDeleteCommand(CODE_FIRST_MODULE);

        String expectedMessage = String.format(MESSAGE_DELETE_SUCCESS, moduleToDelete);

        ModelManager expectedModel = new ModelManager(model.getGradPad(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(forceDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModuleCodeUnfilteredList_throwsCommandException() {
        ModuleCode invalidModuleCode = new ModuleCode("AA0000");
        ForceDeleteCommand forceDeleteCommand = new ForceDeleteCommand(invalidModuleCode);
        assertCommandFailure(forceDeleteCommand, model,
            String.format(Messages.MESSAGE_INVALID_MODULE, invalidModuleCode.toString()));
    }

    @Test
    public void equals() {
        ForceDeleteCommand forceDeleteFirstCommand = new ForceDeleteCommand(CODE_FIRST_MODULE);
        ForceDeleteCommand forceDeleteSecondCommand = new ForceDeleteCommand(CODE_SECOND_MODULE);

        // same object -> returns true
        assertEquals(forceDeleteFirstCommand, forceDeleteFirstCommand);

        // same values -> returns true
        ForceDeleteCommand forceDeleteFirstCommandCopy = new ForceDeleteCommand(CODE_FIRST_MODULE);
        assertEquals(forceDeleteFirstCommand, forceDeleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, forceDeleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, forceDeleteFirstCommand);

        // different module -> returns false
        assertNotEquals(forceDeleteFirstCommand, forceDeleteSecondCommand);
    }

    @Test
    public void requiresStall() {
        ForceDeleteCommand forceDeleteCommand = new ForceDeleteCommand(CODE_FIRST_MODULE);
        assertFalse(forceDeleteCommand.requiresStall());
    }
}
