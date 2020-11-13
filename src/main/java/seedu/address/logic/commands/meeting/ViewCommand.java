package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.meeting.ModelMeeting;
import seedu.address.model.meeting.meeting.Meeting;

/**
 * View a meeting by displaying its details in the side panel
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_VIEW_MEETING_SUCCESS = "Displayed meeting: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Displays the details of the meeting identified by the index number used in the displayed meeting list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    private final Index targetIndex;

    /**
     * Construct command given index of meeting to view.
     * @param targetIndex specified index of meeting to view.
     */
    public ViewCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }


    @Override
    public CommandResult execute(ModelMeeting modelMeeting) throws CommandException {
        requireNonNull(modelMeeting);
        List<Meeting> lastShownList = modelMeeting.getFilteredMeetingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meetingToView = lastShownList.get(targetIndex.getZeroBased());
        modelMeeting.setMeetingInView(meetingToView);
        return new CommandResult(String.format(MESSAGE_VIEW_MEETING_SUCCESS, meetingToView));
    }
}
