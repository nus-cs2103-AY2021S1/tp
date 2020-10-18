package seedu.address.logic.commands;

import seedu.address.logic.state.StateManager;
import seedu.address.model.Model;

/**
 * Returns the program to the Main Page.
 */
public class ReturnCommand extends Command {

    public static final String COMMAND_WORD = "return";

    public static final String MESSAGE_RETURN_SUCCESS = "You are now at the Main Page! Type 'open case [INDEX]' "
            + "to open a case!";


    @Override
    public CommandResult execute(Model model) {

        StateManager.resetState();

        return new CommandResult(MESSAGE_RETURN_SUCCESS);
    }
}
