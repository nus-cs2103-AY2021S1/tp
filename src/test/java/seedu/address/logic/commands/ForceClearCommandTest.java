package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_CLEAR_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;

import org.junit.jupiter.api.Test;

import seedu.address.model.GradPad;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ForceClearCommandTest {

    @Test
    public void execute_emptyGradPad_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ForceClearCommand(), model, MESSAGE_CLEAR_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyGradPad_success() {
        Model model = new ModelManager(getTypicalGradPad(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalGradPad(), new UserPrefs());
        expectedModel.setGradPad(new GradPad());

        assertCommandSuccess(new ForceClearCommand(), model, MESSAGE_CLEAR_SUCCESS, expectedModel);
    }

    @Test
    public void requiresStall() {
        ForceClearCommand forceClearCommand = new ForceClearCommand();
        assertFalse(forceClearCommand.requiresStall());
    }

}
