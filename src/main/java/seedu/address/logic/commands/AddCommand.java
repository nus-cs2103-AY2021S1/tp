package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REPS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.log.Log;

/**
 * Adds a log to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a log to the address book. "
            + "Parameters: "
            + PREFIX_EXERCISE + "EXERCISE "
            + PREFIX_REPS + "REPS "
            + PREFIX_COMMENT + "COMMENT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EXERCISE + "situp "
            + PREFIX_REPS + "50 "
            + PREFIX_COMMENT + "my abs hurt:( \n";

    public static final String MESSAGE_SUCCESS = "New log added: %1$s";
    public static final String MESSAGE_DUPLICATE_LOG = "This log already exists in the address book";

    private final Log toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Log}
     */
    public AddCommand(Log log) {
        requireNonNull(log);
        toAdd = log;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasLog(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LOG);
        }

        model.addLog(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
