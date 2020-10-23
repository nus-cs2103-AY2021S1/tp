package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.deadline.Deadline;


/**
 * Adds a task to the PlaNus task list.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to PlaNus.\n"
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DATE_TIME + "DEADLINE_DATE_TIME "
            + PREFIX_DESCRIPTION + "DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Do work "
            + PREFIX_DATE_TIME + "01-01-2020 12:00 "
            + PREFIX_DESCRIPTION + "Science experiment ";

    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";
    public static final String MESSAGE_DUPLICATE_DEADLINE = "This deadline already exists in PlaNus.";

    private final Deadline toAdd;

    /**
     * Creates an DeadlineCommand to add the specified {@code Deadline}
     */
    public DeadlineCommand(Deadline deadline) {
        requireNonNull(deadline);
        toAdd = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_DEADLINE);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineCommand // instanceof handles nulls
                && toAdd.equals(((DeadlineCommand) other).toAdd));
    }
}
