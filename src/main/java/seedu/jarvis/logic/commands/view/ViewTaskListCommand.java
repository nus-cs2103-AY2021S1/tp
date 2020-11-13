package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_DEADLINE_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_EVENT_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_TASK_LIST;
import static seedu.jarvis.logic.parser.CliSyntax.VIEW_TODO_LIST;

import seedu.jarvis.commons.core.Messages;
import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;

/**
 * Lists all tasks.
 */
public class ViewTaskListCommand extends ViewCommand {
    public static final String SAMPLE_COMMAND_TASK = "view -t";
    public static final String SAMPLE_COMMAND_TODO = "view -tt";
    public static final String SAMPLE_COMMAND_EVENT = "view -te";
    public static final String SAMPLE_COMMAND_DEADLINE = "view -td";
    public static final String MESSAGE_SUCCESS = "Listed all %1$s";
    public static final String TASK_LIST = "tasks";
    public static final String TODO_LIST = "todos under Task";
    public static final String EVENT_LIST = "events under Task";
    public static final String DEADLINE_LIST = "deadlines under Task";

    private final String viewType;

    public ViewTaskListCommand(String viewType) {
        this.viewType = viewType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch(viewType) {
        case VIEW_TASK_LIST:
            model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, TASK_LIST), CommandTargetFeature.Tasks);

        case VIEW_TODO_LIST:
            model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TODOS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, TODO_LIST), CommandTargetFeature.Tasks);

        case VIEW_EVENT_LIST:
            model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_EVENTS);
            return new CommandResult(String.format(MESSAGE_SUCCESS, EVENT_LIST), CommandTargetFeature.Tasks);

        case VIEW_DEADLINE_LIST:
            model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_DEADLINES);
            return new CommandResult(String.format(MESSAGE_SUCCESS, DEADLINE_LIST), CommandTargetFeature.Tasks);

        default:
            throw new CommandException(Messages.MESSAGE_VIEW_TYPE_NOT_FOUND);
        }
    }

}
