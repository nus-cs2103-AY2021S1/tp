package seedu.address.logic.commands.calendarnavigation;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class PrevCalendarNavigationCommand extends Command {

    public static final String COMMAND_WORD = "prev";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": navigates to previous month of calendar. ";

    public static final String MESSAGE_SUCCESS = "Displaying previous month of Calendar.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS)).setNavigationTrue();
    }

}