package seedu.cc.logic.commands;

import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) {
        return CommandResultFactory.createCommandResultForHelpCommand(SHOWING_HELP_MESSAGE);
    }
}
