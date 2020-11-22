package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalClients.getTypicalTbmManager;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TbmManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTbmManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, true, false, false);

        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyTbmManager_success() {
        Model model = new ModelManager(getTypicalTbmManager(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTbmManager(), new UserPrefs());
        expectedModel.setTbmManager(new TbmManager());
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, true, false, false);

        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

}
