package seedu.address.logic.commands.todolistcommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class HelpTaskCommand extends Command {
    public static final String COMMAND_WORD = "helptask";

    public static final String MESSAGE_USAGE = "help: Shows program usage instructions.\n"
            + "Example: help";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }

}
