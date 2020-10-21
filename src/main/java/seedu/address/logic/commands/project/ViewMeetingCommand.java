package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.project.Project;

/**
 * Requests to view the details of an existing meeting in the project.
 */
public class ViewMeetingCommand extends Command {

    public static final String COMMAND_WORD = "viewmeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the details of the meeting identified "
            + "by the index number used in the displayed meeting list. ";

    public static final String MESSAGE_VIEW_MEETING_SUCCESS = "Started MEETING: %1$s";

    private final Index index;

    /**
     * @param index of the task in the filtered meeting list to edit
     *
     */
    public ViewMeetingCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        List<Meeting> lastShownList = (List<Meeting>) project.getMeetings();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meeting = lastShownList.get(index.getZeroBased());
        model.enterMeeting(meeting);

        return new CommandResult(String.format(MESSAGE_VIEW_MEETING_SUCCESS, meeting));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewMeetingCommand)) {
            return false;
        }

        // state check
        ViewMeetingCommand e = (ViewMeetingCommand) other;
        return index.equals(e.index);
    }
}
