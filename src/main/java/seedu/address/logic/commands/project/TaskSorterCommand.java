package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASCENDING_SORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCENDING_SORT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Sorts the task list by task name, deadline, progress, or whether the task is done.
 */
public class TaskSorterCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sort the task list by one of task's attributes in ascending/descending order\n"
        + "Parameters: ("
        + PREFIX_ASCENDING_SORT + ")||("
        + PREFIX_DESCENDING_SORT + ")  ("
        + PREFIX_TASK_DEADLINE + "DEADLINE)||("
        + PREFIX_TASK_NAME + "TASK NAME)||("
        + PREFIX_TASK_PROGRESS + "PROGRESS)||("
        + PREFIX_TASK_IS_DONE + "DONE STATUS)\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_ASCENDING_SORT + " "
        + PREFIX_TASK_NAME;
    public static final String MESSAGE_SORT_TASK_SUCCESS = "Here is the sorted task list:";
    private static final Logger logger = Logger.getLogger("TaskSorterCommandLogger");
    private final Comparator<Task> comparator;

    /**
     * Creates a sort command with the given comparator.
     * @param comparator the comparator used to sort tasks
     */
    public TaskSorterCommand(Comparator<Task> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert model.getProjectToBeDisplayedOnDashboard().isPresent();
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        project.updateTaskComparator(comparator);
        logger.log(Level.INFO, "Task list sorted using new comparator");
        return new CommandResult(String.format(MESSAGE_SORT_TASK_SUCCESS));
    }

}
