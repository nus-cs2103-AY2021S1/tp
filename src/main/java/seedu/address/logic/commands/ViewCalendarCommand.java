package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Views the list of meetings in the calendar.
 */
public class ViewCalendarCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Viewed all meetings";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
