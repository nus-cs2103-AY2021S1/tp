package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    public ExitCommandTest() throws CommandException {
    }

    //@Test
    //public void execute_exit_success() {
    //    CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    //    assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    //}
}
