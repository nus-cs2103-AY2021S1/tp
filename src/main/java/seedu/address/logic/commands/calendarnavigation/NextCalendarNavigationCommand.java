package seedu.address.logic.commands.calendarnavigation;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class NextCalendarNavigationCommand extends Command {

    public static final String COMMAND_WORD = "next";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": navigates to next month of calendar. ";

    public static final String MESSAGE_SUCCESS = "Displaying next month of Calendar.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS)).setNavigationTrue();
    }

}
