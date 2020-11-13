package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDays.getTypicalMyFitnessBuddy;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyMyFitnessBuddy_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, false,
                false, false, false, true, false, 0, false, false, 0, false);
        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_overloadedConstructorSuccess() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, true);
        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_nonEmptyMyFitnessBuddy_success() {
        Model model = new ModelManager(getTypicalMyFitnessBuddy(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalMyFitnessBuddy(), new UserPrefs());
        expectedModel.setMyFitnessBuddy(new MyFitnessBuddy());
        CommandResult expectedCommandResult = new CommandResult(ClearCommand.MESSAGE_SUCCESS, false, false,
                false, false, false, true, false, 0, false, false, 0, false);
        assertCommandSuccess(new ClearCommand(), model, expectedCommandResult, expectedModel);
    }

}
