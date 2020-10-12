package seedu.address.logic.commands.project;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Remark command not implemented yet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Filter and show tasks with given predicate\n"
        + "Parameters: INDEX (must be a positive integer), NAME (must be present in the project)\n"
        + "Example: " + COMMAND_WORD + " 1 Lucas";

    @Override
    public CommandResult execute(Model model) throws CommandException {

        return new CommandResult(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
