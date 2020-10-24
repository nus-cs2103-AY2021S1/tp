package seedu.address.logic.commands.todolistcommands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Sorts the todo list based on specified comparator.
 */
public class SortTaskCommand extends Command {

    public static final String COMMAND_WORD = "sorttask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort the todo list based on a criterion. "
        + "You can sort based on the name, priority, or the date/deadline of the tasks.\n"
        + "Parameters: [REVERSE] CRITERION \n"
        + "You can choose from the criterion below: \n"
        + "  NAME     -> to sort the task lexicographic based on name \n"
        + "  PRIORITY -> to sort the task based on priority \n"
        + "  DATE     -> to sort the task based on date or deadline \n"
        + "To reverse the ordering, you can add 'r' before the criterion. \n"
        + "Example: " + COMMAND_WORD + " PRIORITY \n"
        + "Example (reversed) : " + COMMAND_WORD + " r" + " PRIORITY \n";

    public static final String MESSAGE_SUCCESS = "List has been sorted!";

    private final Comparator<Task> comparator;

    /**
     * Creates SortTaskCommand to sort the todo list based on {@code Comparator}.
     */
    public SortTaskCommand(Comparator<Task> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateSortedTodoList(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortTaskCommand)) {
            return false;
        }

        // state check
        SortTaskCommand s = (SortTaskCommand) other;

        return this.comparator.equals(s.comparator);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
