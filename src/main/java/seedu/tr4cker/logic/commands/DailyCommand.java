package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DAILY_DELETE;

import java.util.List;
import java.util.Objects;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.daily.Todo;

public class DailyCommand extends Command {

    public static final String COMMAND_WORD = "daily";

    public static final String MESSAGE_SWITCH_TAB_USAGE = COMMAND_WORD + ": Switches to Daily tab\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to Daily tab!";

    public static final String MESSAGE_USAGE = "Looks like you're trying to use the " + COMMAND_WORD + " command: "
            + "Switch to daily tab or delete a todo task\n"
            + "Switch to daily tab: " + COMMAND_WORD + "\n"
            + "Deletes a todo task: "
            + "Compulsory Parameter: INDEX (must be a positive integer and valid index number)\n"
            + "To delete task from Daily Todo Task List: (E.g. " + COMMAND_WORD + " " + PREFIX_DAILY_DELETE + "1)";

    public static final String MESSAGE_TODO_DELETE_SUCCESS = "Deleted todo tasks: %1$s";

    private final Index toDeleteIndex;

    /**
     * Constructor for DailyCommand when user wants to switch to Daily tab.
     */
    public DailyCommand() {
        this.toDeleteIndex = null;
    }

    /**
     * Constructor for DailyCommand when user wants to delete a todo task.
     */
    public DailyCommand(Index toDeleteIndex) {
        requireNonNull(toDeleteIndex);
        this.toDeleteIndex = toDeleteIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (toDeleteIndex == null) {
            return CommandResult.createDailyTabSwitchCommandResult(MESSAGE_SWITCH_TAB_SUCCESS);
        }

        List<Todo> lastShownList = model.getFilteredTodoList();
        if (toDeleteIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
        }

        Todo todoToDelete = lastShownList.get(toDeleteIndex.getZeroBased());
        model.deleteTodo(todoToDelete);

        return new CommandResult(String.format(MESSAGE_TODO_DELETE_SUCCESS, todoToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { // short circuit if same object
            return true;
        } else if (other instanceof DailyCommand
                && Objects.equals(toDeleteIndex, ((DailyCommand) other).toDeleteIndex)) {
            return true;
        } else {
            return false;
        }
    }
}
