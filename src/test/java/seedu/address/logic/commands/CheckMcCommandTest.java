package seedu.address.logic.commands;

import static seedu.address.commons.core.Messages.MESSAGE_CHECKMC_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.getTypicalGradPad;
import static seedu.address.testutil.TypicalModules.getTypicalTotalMc;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class CheckMcCommandTest {
    private Model model = new ModelManager(getTypicalGradPad(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalGradPad(), new UserPrefs());

    @Test
    public void execute_checkmc_success() {
        // with modules
        assertCommandSuccess(new CheckMcCommand(), model, String.format(MESSAGE_CHECKMC_SUCCESS,
                getTypicalTotalMc()), expectedModel);

        // no modules
        assertCommandSuccess(new CheckMcCommand(), new ModelManager(),
            String.format(MESSAGE_CHECKMC_SUCCESS, 0.0), new ModelManager());
    }
}
