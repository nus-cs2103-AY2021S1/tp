package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class EndAttemptCommand extends Command {

    public static final String COMMAND_WORD = "end";
    public static final String MESSAGE_USAGE = COMMAND_WORD + "end attempt: Ends a quiz attempt. \n"
        + "Example: " + COMMAND_WORD + " attempt" + " (case sensitive)";
    public static final String MESSAGE_ATTEMPT_ACKNOWLEDGEMENT = "Attempt ended! \n"
                                                                + "You can view your results at Performance";
    public static final String MESSAGE_EMPTY_ATTEMPT = "Attempt will not be saved to Performance because it is empty.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasCurrentAttempt()) {
            throw new CommandException("There is no attempt in progress");
        } else {
            boolean isCurrentAttemptEmpty = model.endAttempt();
            if (isCurrentAttemptEmpty) {
                return new CommandResult(MESSAGE_EMPTY_ATTEMPT, false, false, false);
            }
            model.setAllSelectedIndex(-1);
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
        if (!(other instanceof EndAttemptCommand)) {
            return false;
        }

        return true;
    }
}
