package seedu.address.logic.commands.project;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.project.Project;

/**
 * Shows all the meetings in the current project.
 */
public class AllMeetingsCommand extends Command {

    public static final String COMMAND_WORD = "allm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": clear meeting filter and view all the meetings\n";

    public static final String MESSAGE_ALL_MEETINGS_SUCCESS = "These are all the meetings in this project";

    public AllMeetingsCommand() {

    }

    @Override
    public CommandResult execute(Model model) {
        assert(model != null);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        project.showAllMeetings();
        return new CommandResult(MESSAGE_ALL_MEETINGS_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || other instanceof AllMeetingsCommand; // instanceof handles nulls
    }
}
