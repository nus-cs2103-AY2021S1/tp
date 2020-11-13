package seedu.jarvis.logic.commands.add;

import static java.util.Objects.requireNonNull;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.logic.commands.exceptions.CommandException;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Task;
import seedu.jarvis.model.task.Todo;

public class AddTaskCommand extends AddCommand {
    public static final String TO_ADD_TODO = "T";
    public static final String TO_ADD_EVENT = "E";
    public static final String TO_ADD_DEADLINE = "D";

    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in jarvis";
    public static final String MESSAGE_INVALID_TO_ADD_TYPE = "This object to add is unidentifiable";
    public static final String MESSAGE_SUCCESS_TODO = "New todo added: %1$s";
    public static final String MESSAGE_SUCCESS_EVENT = "New event added: %1$s";
    public static final String MESSAGE_SUCCESS_DEADLINE = "New deadline added: %1$s";

    private final Task toAdd;
    private final String toAddType;
    /**
     * Creates an AddTaskCommand to add the specified {@code Todo}
     */
    public AddTaskCommand(Todo todo) {
        requireNonNull(todo);
        toAdd = todo;
        toAddType = TO_ADD_TODO;
    }

    /**
     * Creates an AddTaskCommand to add the specified {@code Event}
     */
    public AddTaskCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
        toAddType = TO_ADD_EVENT;
    }

    /**
     * Creates an AddTaskCommand to add the specified {@code Deadline}
     */
    public AddTaskCommand(Deadline deadline) {
        requireNonNull(deadline);
        toAdd = deadline;
        toAddType = TO_ADD_DEADLINE;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch(toAddType) {
        case TO_ADD_TODO:
            Todo toAddTodo = (Todo) toAdd;
            if (model.hasTodo(toAddTodo)) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }

            model.addTodo(toAddTodo);
            return new CommandResult(String.format(MESSAGE_SUCCESS_TODO, toAddTodo), CommandTargetFeature.Tasks);

        case TO_ADD_EVENT:
            Event toAddEvent = (Event) toAdd;
            if (model.hasEvent(toAddEvent)) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }

            model.addEvent(toAddEvent);
            return new CommandResult(String.format(MESSAGE_SUCCESS_EVENT, toAddEvent), CommandTargetFeature.Tasks);

        case TO_ADD_DEADLINE:
            Deadline toAddDeadline = (Deadline) toAdd;
            if (model.hasDeadline(toAddDeadline)) {
                throw new CommandException(MESSAGE_DUPLICATE_TASK);
            }

            model.addDeadline(toAddDeadline);
            return new CommandResult(String.format(MESSAGE_SUCCESS_DEADLINE, toAddDeadline),
                    CommandTargetFeature.Tasks);

        default:
            throw new CommandException(MESSAGE_INVALID_TO_ADD_TYPE);
        }
    }

    /**
     * Returns the Task object to be added.
     * For testing purposes
     */
    public Task getTask() {
        return this.toAdd;
    }

    /**
     * Returns the Task type to be added.
     * For testing purposes
     */
    public String getTaskType() {
        return this.toAddType;
    }
}
