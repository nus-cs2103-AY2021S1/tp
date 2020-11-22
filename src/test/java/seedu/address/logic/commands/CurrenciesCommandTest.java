package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CurrenciesCommand.SUCCESSFUL_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.model.ExpenseModelManager;
import seedu.address.model.Model;

public class CurrenciesCommandTest {
    private Model model = new ExpenseModelManager();
    private Model expectedModel = new ExpenseModelManager();

    @Test
    public void execute_code_success() {
        CommandResult expectedCommandResult = new CommandResult(SUCCESSFUL_MESSAGE,
                false, false,
                false, false,
                true, false);
        assertCommandSuccess(new CurrenciesCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_code_failure() {
        CommandResult expectedCommandResult = new CommandResult("WRONG MESSAGE",
                false, false,
                false, false,
                true, false);
        CommandResult actualCommandResult = new CurrenciesCommand().execute(model);
        assertFalse(expectedCommandResult.equals(actualCommandResult));
    }
}
