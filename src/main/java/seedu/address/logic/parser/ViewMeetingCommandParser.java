package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ViewMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.MeetingName;

public class ViewMeetingCommandParser implements Parser<ViewMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteMeetingCommand
     * and returns a DeleteMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewMeetingCommand parse(String args) throws ParseException {
        try {
            MeetingName targetMeetingName = ParserUtil.parseMeetingName(args);
            return new ViewMeetingCommand(targetMeetingName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewMeetingCommand.MESSAGE_USAGE), pe);
        }
    }
}
