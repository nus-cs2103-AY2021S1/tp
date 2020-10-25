package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;

public class ViewMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meeting view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the meeting identified by the name used in the displayed meeting list.\n"
            + "Parameters: MEETINGNAME (must be a valid meeting name)\n"
            + "Example: " + COMMAND_WORD + " CS2103 Project Meeting";

    public static final String MESSAGE_VIEW_MEETING_SUCCESS = "Viewing selected meeting: %1$s";

    private final MeetingName targetMeetingName;

    public ViewMeetingCommand(MeetingName targetMeetingName) {
        this.targetMeetingName = targetMeetingName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        boolean isValidMeeting = model.hasMeetingName(targetMeetingName);

        if (!isValidMeeting) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEETING_DISPLAYED);
        }

        List<Meeting> filteredMeetingList = model.getFilteredMeetingList().stream()
                .filter(meeting -> meeting.isSameMeetingName(targetMeetingName)).collect(Collectors.toList());
        Meeting meetingToView = filteredMeetingList.get(0);
        model.setSelectedMeeting(meetingToView);
        return new CommandResult(String.format(MESSAGE_VIEW_MEETING_SUCCESS, meetingToView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewMeetingCommand // instanceof handles nulls
                && targetMeetingName.equals(((ViewMeetingCommand) other).targetMeetingName)); // state check
    }
}
