package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.Status;

/**
 * Leaves the project view and go back to the main catalogue view.
 */
public class LeaveProjectViewCommand extends Command {

    public static final String COMMAND_WORD = "leaveProjectView";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Leaves the project detail view and go back to the main catalogue view.\n";

    public static final String MESSAGE_LEAVE_SUCCESS = "Returned to the main catalogue page";
    public static final String MESSAGE_LEAVE_FAIL = "This leaveProjectView is only to exit the project view page./n"
            + "Please use 'leaveTeammateView' to exit the teammate view page"
            + " or 'leaveTaskView' to exit the task view page.";

    public LeaveProjectViewCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getStatus() == Status.PROJECT) {
            model.quit();
            return new CommandResult(MESSAGE_LEAVE_SUCCESS);
        } else {
            return new CommandResult(MESSAGE_LEAVE_FAIL);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof LeaveProjectViewCommand; // instanceof handles nulls
    }
}
