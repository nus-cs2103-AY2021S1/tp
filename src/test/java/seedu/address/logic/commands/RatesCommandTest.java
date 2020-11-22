package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RatesCommand.SUCCESSFUL_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExpenseModelManager;
import seedu.address.model.Model;

public class RatesCommandTest {
    private Model model = new ExpenseModelManager();
    private Model expectedModel = new ExpenseModelManager();

    @Test
    public void execute_rates_success() {
        CommandResult expectedCommandResult = new CommandResult(SUCCESSFUL_MESSAGE,
                false, false,
                false, false,
                false, true);
        assertCommandSuccess(new RatesCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_rates_failure() {
        CommandResult expectedCommandResult = new CommandResult("WRONG MESSAGE",
                false, false,
                false, false,
                false, true);
        CommandResult actualCommandResult = new RatesCommand().execute(model);
        assertFalse(expectedCommandResult.equals(actualCommandResult));
    }
}
