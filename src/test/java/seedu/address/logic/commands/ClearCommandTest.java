package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

import org.junit.jupiter.api.Test;

import seedu.address.model.GradPad;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyGradPad_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, MESSAGE_CLEAR_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyGradPad_success() {
        Model model = new ModelManager(getTypicalGradPad(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalGradPad(), new UserPrefs());
        expectedModel.setGradPad(new GradPad());

        assertCommandSuccess(new ClearCommand(), model, MESSAGE_CLEAR_SUCCESS, expectedModel);
    }

    @Test
    public void requiresStall_validTest() {
        ClearCommand testCommand = new ClearCommand();
        assertTrue(testCommand.requiresStall());
    }
}
