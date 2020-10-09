package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingName;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class DeleteMeetingCommand extends Command {

    public static final String COMMAND_WORD = "meeting delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the meeting identified by the name used in the displayed meeting list.\n"
            + "Parameters: MEETINGNAME (must be a valid meeting name)\n"
            + "Example: " + COMMAND_WORD + " CS2103 Project Meeting";

    public static final String MESSAGE_DELETE_MEETING_SUCCESS = "Deleted Meeting: %1$s";

    private final MeetingName targetMeetingName;

    public DeleteMeetingCommand(MeetingName targetMeetingName) {
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
        Meeting meetingToDelete = filteredMeetingList.get(0);
        model.deleteMeeting(meetingToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteMeetingCommand // instanceof handles nulls
                && targetMeetingName.equals(((DeleteMeetingCommand) other).targetMeetingName)); // state check
    }
}
