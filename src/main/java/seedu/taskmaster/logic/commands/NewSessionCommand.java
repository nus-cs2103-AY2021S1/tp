package seedu.taskmaster.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_SESSION_DATE_TIME;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_SESSION_NAME;

import seedu.taskmaster.logic.commands.exceptions.CommandException;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.session.Session;

/**
 * Adds a session to the session list.
 */
public class NewSessionCommand extends Command {

    public static final String COMMAND_WORD = "session";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a session to the session list. "
            + "Parameters: "
            + PREFIX_SESSION_NAME + "SESSION_NAME "
            + PREFIX_SESSION_DATE_TIME + "SESSION_DATE_TIME...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSION_NAME + "CS2103 Tutorial 1 "
            + PREFIX_SESSION_DATE_TIME + "23 Oct 2020 0900H";

    public static final String MESSAGE_SUCCESS = "New session added: %1$s";
    public static final String MESSAGE_DUPLICATE_SESSION = "This session already exists in the session list";

    private final Session toAdd;

    /**
     * Creates a NewSessionCommand to add the specified {@code Session}
     */
    public NewSessionCommand(Session session) {
        requireNonNull(session);
        toAdd = session;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSession(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION);
        }

        model.addSession(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((NewSessionCommand) other).toAdd));
    }
}
