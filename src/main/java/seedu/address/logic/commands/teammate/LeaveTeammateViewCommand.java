package seedu.address.logic.commands.teammate;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.Status;

/**
 * Leaves the task view and go back to the project view.
 */
public class LeaveTeammateViewCommand extends Command {

    public static final String COMMAND_WORD = "leaveTeammateView";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Leaves the teammate detail view and go back to the project detail view.\n";

    public static final String MESSAGE_LEAVE_SUCCESS = "Returned to the project page";
    public static final String MESSAGE_LEAVE_FAIL = "This leaveTeammateView is only to exit the teammate view page./n"
            + "Please use 'leaveProjectView' to exit the project view page"
            + " or 'leaveTaskView' to exit the task view page.";

    public LeaveTeammateViewCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getStatus() == Status.PERSON) {
            model.quit();
            return new CommandResult(MESSAGE_LEAVE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_LEAVE_FAIL);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof LeaveTeammateViewCommand; // instanceof handles nulls
    }
}
