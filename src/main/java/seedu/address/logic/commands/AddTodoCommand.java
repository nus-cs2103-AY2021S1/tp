package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.ToDo;

/**
 * Adds a todo to the task manager.
 */
public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a todo-type task to the task manager. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_PRIORITY + "PRIORITY "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "tP Tasks "
            + PREFIX_DESCRIPTION + "Refactor tP Code "
            + PREFIX_PRIORITY + "High "
            + PREFIX_TAG + "cs2103 "
            + PREFIX_TAG + "project";

    public static final String MESSAGE_SUCCESS = "New todo added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager";

    private final ToDo toAdd;

    /**
     * Creates an AddTodoCommand to add the specified {@code Task}
     */
    public AddTodoCommand(ToDo task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTodoCommand // instanceof handles nulls
                && toAdd.equals(((AddTodoCommand) other).toAdd));
    }
}
