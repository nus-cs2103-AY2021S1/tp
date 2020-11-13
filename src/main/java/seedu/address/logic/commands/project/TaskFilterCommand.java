package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Filters tasks by assignee's name, task name, deadline, progress, or whether the task is done.
 */
public class TaskFilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters tasks in the task list by various task attributes\n"
            + "Parameters: ("
            + PREFIX_TASK_ASSIGNEE + "ASSIGNEE_GITHUB_USERNAME)||("
            + PREFIX_TASK_NAME + "KEYWORD [MORE_KEYWORDS]...)||("
            + PREFIX_TASK_DEADLINE + "DEADLINE)||("
            + PREFIX_START_DATE + "START_DATE "
            + PREFIX_END_DATE + "END_DATE)||("
            + PREFIX_TASK_PROGRESS + "TASK_PROGRESS)||("
            + PREFIX_TASK_IS_DONE + "DONE_STATUS)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_ASSIGNEE + "T-Fang\n";
    public static final String MESSAGE_FILTER_TASK_SUCCESS = "Here are the filtered tasks:";
    public static final String MESSAGE_INVALID_TIME_RANGE = "The end date should not be earlier than the start date.";
    public static final String MESSAGE_BLANK_KEYWORDS = "The keywords for task name should not be blank.";
    private static final Logger logger = Logger.getLogger("TaskFilterCommandLogger");
    private final Predicate<Task> predicate;

    /**
     * Creates a filter command with the given predicate.
     *
     * @param predicate the predicate used to filter tasks.
     */
    public TaskFilterCommand(Predicate<Task> predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert model.getProjectToBeDisplayedOnDashboard().isPresent();

        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        project.updateTaskFilter(predicate);
        logger.log(Level.INFO, "Task list filtered using new predicate");

        return new CommandResult(MESSAGE_FILTER_TASK_SUCCESS);
    }

}
