package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Formats full help instructions for every command for display.
 */
public class CalendarCommand extends Command {

    public static final String COMMAND_WORD = "calendar";

    public static final String SHOWING_HELP_MESSAGE = "Opened calendar";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false, false, false, true);
    }
}
