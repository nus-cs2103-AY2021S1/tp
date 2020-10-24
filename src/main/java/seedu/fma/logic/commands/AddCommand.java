package seedu.fma.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_C;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_E;
import static seedu.fma.logic.parser.CliSyntax.PREFIX_R;

import seedu.fma.logic.commands.exceptions.CommandException;
import seedu.fma.model.Model;
import seedu.fma.model.log.Log;

/**
 * Adds a log to the log book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String AC_SUGGESTION = COMMAND_WORD + " "
            + PREFIX_E + "Sit ups "
            + PREFIX_R + "30 "
            + PREFIX_C + "my abs hurt:(";

    public static final String AC_REGEX = COMMAND_WORD + "*" + PREFIX_E + "*" + PREFIX_R + "*" + PREFIX_C + "*";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a log to the log book. "
            + "Parameters: "
            + PREFIX_E + "EXERCISE "
            + PREFIX_R + "REPS "
            + PREFIX_C + "COMMENT \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_E + "Sit ups "
            + PREFIX_R + "30 "
            + PREFIX_C + "my abs hurt:( \n";

    public static final String MESSAGE_SUCCESS = "New log added: %1$s";
    public static final String MESSAGE_DUPLICATE_LOG = "This log already exists in the log book";

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
