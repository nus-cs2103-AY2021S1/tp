package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimapUtil.isOnlyOneGivenPrefixPresent;
import static seedu.address.logic.parser.ArgumentMultimapUtil.isOnlyOnePrefixPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_FILTER_BY_NAME;

import java.util.function.Predicate;

import seedu.address.logic.commands.project.MeetingFilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.Meeting;

/**
 * Parses input {@code String} and creates a MeetingFilterCommand object.
 */
public class MeetingFilterCommandParser implements Parser<MeetingFilterCommand> {

    /**
     * Parses the given input {@code String}.
     * @param args  the user input
     * @return      the filter command whose predicate corresponds to the user input
     * @throws ParseException   if the user input does not follow the format
     */
    @Override
    public MeetingFilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_MEETING_FILTER_BY_NAME);

        if (!isOnlyOnePrefixPresent(argMultimap, PREFIX_MEETING_FILTER_BY_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MeetingFilterCommand.MESSAGE_USAGE));
        }

        Predicate<Meeting> predicate = meeting -> true;

        if (argMultimap.getValue(PREFIX_MEETING_FILTER_BY_NAME).isPresent()) {
            predicate = meeting -> meeting.getMeetingName()
                .contains(argMultimap.getValue(PREFIX_MEETING_FILTER_BY_NAME).get());
        }

        return new MeetingFilterCommand(predicate);
    }
}
