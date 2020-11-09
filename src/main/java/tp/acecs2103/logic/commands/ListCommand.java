package tp.acecs2103.logic.commands;

import static java.util.Objects.requireNonNull;

import tp.acecs2103.commons.core.Messages;
import tp.acecs2103.model.Model;
import tp.acecs2103.model.task.WeekNumber;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all tasks for week ";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": List all tasks for the week indicated.\n"
            + "Parameters: WEEK_NUMBER (an integer in range [1,13])\n"
            + "Example: " + COMMAND_WORD + " 7";

    private final WeekNumber weekNumber;

    /**
     * Creates a {@code ListCommand} with given Week number.
     */
    public ListCommand(WeekNumber weekNumber) {
        requireNonNull(weekNumber);
        this.weekNumber = weekNumber;
    }

    public int getWeekNumber() {
        return weekNumber.getWeekValueInt();
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.listTasks(weekNumber);
        return new CommandResult(MESSAGE_SUCCESS + this.weekNumber.value + "\n"
        + String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getUiTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ListCommand)) {
            return false;
        } else {
            return this.weekNumber.equals(((ListCommand) other).weekNumber);
        }
    }
}
