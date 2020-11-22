package seedu.forgetfulnus.logic.commands;

import seedu.forgetfulnus.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static final CommandType type = CommandType.ANY_MODE;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }

    @Override
    public String getQuizModeReminder() {
        return "";
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }
}
