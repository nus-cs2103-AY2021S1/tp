package seedu.tr4cker.logic.commands;

import seedu.tr4cker.model.Model;

/**
 * Terminates the program.
 */
public class HomeCommand extends Command {

    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_CHANGE_TAB_ACKNOWLEDGEMENT = "Switched to Home tab!";

    @Override
    public CommandResult execute(Model model) {
        return CommandResult.createHomeTabSwitchCommandResult(MESSAGE_CHANGE_TAB_ACKNOWLEDGEMENT);
    }
}
