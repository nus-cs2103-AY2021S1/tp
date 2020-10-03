package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exceptions.InvalidScopeException;

/**
 * Leaves the project view and go back to the main catalogue view.
 */
public class LeaveCommand extends Command {

    public static final String COMMAND_WORD = "leave";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Leaves the project detail view and go back to the main catalogue view.\n";

    public static final String MESSAGE_LEAVE_SUCCESS = "Returned to the main catalogue page";

    public LeaveCommand() {

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        try {
            model.quit();
        } catch (InvalidScopeException e) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_SCOPE_COMMAND,
                    e.getExpected(), e.getActual()));
        }
        return new CommandResult(MESSAGE_LEAVE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof LeaveCommand; // instanceof handles nulls
    }
}
