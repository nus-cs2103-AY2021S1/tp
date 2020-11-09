package seedu.taskmaster.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_SESSION_NAME;

import seedu.taskmaster.logic.commands.exceptions.CommandException;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.session.SessionName;

/**
 * Changes the current session of the Taskmaster to another session in the session list.
 */
public class ChangeSessionCommand extends Command {

    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Changes the current session. "
            + "Parameters: "
            + PREFIX_SESSION_NAME + "SESSION_NAME...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSION_NAME + "Default session";

    public static final String MESSAGE_SUCCESS = "Current session: %1$s";
    public static final String MESSAGE_SESSION_NOT_FOUND = "This session does not exist!";

    private final SessionName sessionName;

    /**
     * Creates a ChangeSessionCommand to switch to the session with the specified {@code sessionName}
     */
    public ChangeSessionCommand(SessionName sessionName) {
        requireNonNull(sessionName);
        this.sessionName = sessionName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasSession(sessionName)) {
            throw new CommandException(MESSAGE_SESSION_NOT_FOUND);
        }

        model.changeSession(sessionName);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sessionName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ChangeSessionCommand // instanceof handles nulls
                && sessionName.equals(((ChangeSessionCommand) other).sessionName));
    }
}
