package seedu.address.logic.commands.budget;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.budget.HelpExpenditureCommand.HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

class HelpExpenditureCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(HELP_MESSAGE, false, false);
        assertCommandSuccess(new HelpExpenditureCommand(), model, expectedCommandResult, expectedModel);
    }

}
