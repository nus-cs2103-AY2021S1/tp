package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_FILTER_BY_NAME;

import java.util.function.Predicate;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Filters tasks by assignee's name, task name or deadline.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Filter and show tasks with given predicate\n"
        + "Parameters: ("
        + PREFIX_TASK_FILTER_BY_ASSIGNEE + "ASSIGNEE NAME)||("
        + PREFIX_TASK_FILTER_BY_DEADLINE + "DEADLINE)||("
        + PREFIX_TASK_FILTER_BY_NAME + "TASK NAME)\n"
        + "Example: " + COMMAND_WORD + " ("
        + PREFIX_TASK_FILTER_BY_ASSIGNEE + "Alice)||("
        + PREFIX_TASK_FILTER_BY_DEADLINE + "31-12-2020 10:00:00)||("
        + PREFIX_TASK_FILTER_BY_NAME + "group meeting)\n";
    public static final String MESSAGE_FILTER_TASK_SUCCESS = "Here are the filtered tasks:";

    private final Predicate<Task> predicate;

    /**
     * Creates a filter command with the given predicate.
     * @param predicate the predicate used to filter tasks
     */
    public FilterCommand(Predicate<Task> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        project.updateTaskFilter(predicate);
        return new CommandResult(String.format(MESSAGE_FILTER_TASK_SUCCESS));
    }

}
