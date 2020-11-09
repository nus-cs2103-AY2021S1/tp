package seedu.address.logic.commands.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all Tasks in the Covigent to the user.
 */
public class ListTaskCommand extends Command {

    public static final String COMMAND_WORD = "listtask";

    public static final String MESSAGE_SUCCESS = "All tasks are listed.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateTasksInFilteredRoomTaskRecords(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
