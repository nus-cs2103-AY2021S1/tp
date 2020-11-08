package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Starts a quiz attempt.
 */
public class StartAttemptCommand extends Command {

    public static final String COMMAND_WORD = "start";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "start attempt: Starts a quiz attempt. \n"
        + "Example: " + COMMAND_WORD + " attempt" + " (case sensitive)";
    public static final String MESSAGE_ATTEMPT_ALREADY_ONGOING = "Attempt is already ongoing!";
    public static final String MESSAGE_ATTEMPT_ACKNOWLEDGEMENT = "Attempt started!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCurrentAttempt()) {
            throw new CommandException(MESSAGE_ATTEMPT_ALREADY_ONGOING);
        } else {
            model.startAttempt();
            model.setAllSelectedIndex(-2);
            return new CommandResult(MESSAGE_ATTEMPT_ACKNOWLEDGEMENT, false, false, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StartAttemptCommand)) {
            return false;
        }

        return true;
    }

}
