package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Gives number of patients recorded.
 */
public class CountCommand extends Command {
    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_UASGE = COMMAND_WORD
            + ": Shows the total number of patients in record.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("There are "
            + String.valueOf(model.count()) + " record(s).");
    }
}
