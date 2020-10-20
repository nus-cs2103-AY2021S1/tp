package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Leaves the task view and go back to the project view.
 */
public class LeaveMeetingViewCommand extends Command {

    public static final String COMMAND_WORD = "leaveMeetingView";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Leaves the meeting detail view and go back to the project detail view.\n";

    public static final String MESSAGE_LEAVE_SUCCESS = "Returned to the project page";

    public LeaveMeetingViewCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.quit();
        return new CommandResult(MESSAGE_LEAVE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof LeaveMeetingViewCommand; // instanceof handles nulls
    }
}
