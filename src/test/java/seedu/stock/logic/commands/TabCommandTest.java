package seedu.stock.logic.commands;

import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.logic.commands.TabCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;


public class TabCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_tab_success() {
        CommandResult expectedResult = new CommandResult(MESSAGE_SUCCESS, null, false, false,
                null, false, null, true, false);
        assertCommandSuccess(new TabCommand(), model, expectedResult, expectedModel);
    }
}
