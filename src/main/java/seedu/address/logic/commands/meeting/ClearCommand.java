package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ModelMeeting;

/**
 * Clears the meeting book.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Cleared all meetings!";

    @Override
    public CommandResult execute(ModelMeeting modelMeeting) throws CommandException {
        requireNonNull(modelMeeting);

        if (modelMeeting.getInternalMeetingList().size() == 0) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MEETING_LIST_EMPTY, COMMAND_WORD));
        }

        modelMeeting.setMeetingBook(new MeetingBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
