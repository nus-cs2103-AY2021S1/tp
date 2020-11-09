package seedu.address.logic.parser.meetingparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.meetingcommands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Contains utility methods used for parsing strings in the various *MeetingCommandParser classes.
 */
public class MeetingParserUtil {
    /**
     * Parses the String arguments into a List of String.
     *
     * @param args The arguments.
     * @return The List of String separated by words.
     * @throws ParseException If arguments is empty.
     */
    public static List<String> parseKeywords(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingCommand.MESSAGE_USAGE));
        }
        String[] keywords = trimmedArgs.split("\\s+");
        return Arrays.asList(keywords);
    }
}
