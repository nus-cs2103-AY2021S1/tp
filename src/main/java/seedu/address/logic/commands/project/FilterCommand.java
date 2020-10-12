package seedu.address.logic.commands.project;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_NAME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Filter command not implemented yet";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Filter and show tasks with given predicate\n"
        + "Parameters: ("
        + PREFIX_TASK_FILTER_BY_ASSIGNEE + "ASSIGNEE NAME )||("
        + PREFIX_TASK_FILTER_BY_DEADLINE + "DEADLINE )||("
        + PREFIX_TASK_FILTER_BY_NAME + "NAME)\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_TASK_FILTER_BY_ASSIGNEE + "ASSIGNEE NAME )||("
        + PREFIX_TASK_FILTER_BY_DEADLINE + "DEADLINE )||("
        + PREFIX_TASK_FILTER_BY_NAME + "NAME)\n";


    @Override
    public CommandResult execute(Model model) throws CommandException {

        return new CommandResult(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
