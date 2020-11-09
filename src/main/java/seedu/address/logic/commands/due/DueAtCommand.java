package seedu.address.logic.commands.due;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.DueAtPredicate;

/**
 * Finds all tasks (Todos and Events) that are due at a given date and time.
 */
public class DueAtCommand extends Command {
    public static final String COMMAND_WORD = "itemsDueAt";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows Todos/Events due at a certain date and time.\n"
            + "Parameters: "
            + PREFIX_DATE + "DD-MM-YYYY "
            + PREFIX_TIME + "HHmm \n"
            + "EXAMPLE: " + COMMAND_WORD + " "
            + PREFIX_DATE + "12-12-2020 "
            + PREFIX_TIME + "2359";

    public static final String MESSAGE_SUCCESS = "Here are the list of Todos/Events due at: %1$s";

    private DueAtPredicate predicate;

    public DueAtCommand(DueAtPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, predicate.getDateTime()), "TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueAtCommand // instanceof handles nulls
                && predicate.equals(((DueAtCommand) other).predicate));
    }

}
