package tp.acecs2103.logic.commands;

import static java.util.Objects.requireNonNull;

import tp.acecs2103.logic.commands.exceptions.CommandException;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.task.Task;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the task identified by the index number used in the displayed task list.\n"
            + "Parameters: i/INDEX (0 + week number in [1,13] + two-digit task number e.g. 01205) "
            + "w/WEEK_NUMBER d/DESCRIPTION c/CUSTOMISED_DEADLINE r/REMARK a/CATEGORY\n"
            + "Example: " + COMMAND_WORD + " i/0109 w/1 d/update documentation c/2020-08-15 r/check dashboard a/Ip";

    public static final String MESSAGE_SUCCESS = "New task added: \n%1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "Task with this index already exists in the task list. "
                                                          + "Try another index please.";
    public static final String MESSAGE_INVALID_TASK = "Invalid task added. Consider following rules:\n"
            + "1. A customised task should not have an official deadline.\n"
            + "2. Index should be consistent with week number. E.g. 01201 for week 12";

    private final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Task}
     */
    public AddCommand(Task task) throws CommandException {
        requireNonNull(task);
        if (!task.isValid()) {
            throw new CommandException(MESSAGE_INVALID_TASK);
        }
        toAdd = task;
    }

    public Task getTaskToAdd() {
        return this.toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        model.getUiTaskList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.isSameTask(((AddCommand) other).toAdd));
    }
}
