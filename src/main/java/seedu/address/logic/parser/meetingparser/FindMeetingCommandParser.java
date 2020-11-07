package seedu.address.logic.parser.meetingparser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;

import seedu.address.logic.commands.meetingcommands.FindMeetingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.meeting.BidderIdContainsKeywordsPredicate;
import seedu.address.model.meeting.DateContainsKeywordsPredicate;
import seedu.address.model.meeting.EndTimeContainsKeywordsPredicate;
import seedu.address.model.meeting.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.meeting.StartTimeContainsKeywordsPredicate;
import seedu.address.model.meeting.VenueContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindMeetingCommand object
 */
public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMeetingCommand
     * and returns a FindMeetingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_MEETING_BIDDER_ID,
                        PREFIX_MEETING_PROPERTY_ID,
                        PREFIX_MEETING_VENUE,
                        PREFIX_MEETING_DATE,
                        PREFIX_MEETING_STARTTIME,
                        PREFIX_MEETING_ENDTIME);

        FindMeetingCommand.FindMeetingDescriptor findMeetingDescriptor = new FindMeetingCommand.FindMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_MEETING_PROPERTY_ID).isPresent()) {
            findMeetingDescriptor.setPropertyIdContainsKeywordsPredicate(new PropertyIdContainsKeywordsPredicate(
                    MeetingParserUtil
                            .parseKeywords(argMultimap.getValue(PREFIX_MEETING_PROPERTY_ID).get())));
        }
        if (argMultimap.getValue(PREFIX_MEETING_BIDDER_ID).isPresent()) {
            findMeetingDescriptor.setBidderIdContainsKeywordsPredicate(
                    new BidderIdContainsKeywordsPredicate(
                            MeetingParserUtil.parseKeywords(argMultimap.getValue(PREFIX_MEETING_BIDDER_ID).get())
                    )
            );
        }
        if (argMultimap.getValue(PREFIX_MEETING_VENUE).isPresent()) {
            findMeetingDescriptor.setVenueContainsKeywordsPredicate(
                    new VenueContainsKeywordsPredicate(
                            MeetingParserUtil.parseKeywords(argMultimap.getValue(PREFIX_MEETING_VENUE).get())));
        }
        if (argMultimap.getValue(PREFIX_MEETING_DATE).isPresent()) {
            findMeetingDescriptor.setDateContainsKeywordsPredicate(
                    new DateContainsKeywordsPredicate(
                            ParserUtil.parseDate(argMultimap.getValue(PREFIX_MEETING_DATE).get())));
        }
        if (argMultimap.getValue(PREFIX_MEETING_STARTTIME).isPresent()) {
            findMeetingDescriptor.setStartTimeContainsKeywordsPredicate(
                    new StartTimeContainsKeywordsPredicate(
                            ParserUtil.parseStartTime(argMultimap.getValue(PREFIX_MEETING_STARTTIME).get())));
        }
        if (argMultimap.getValue(PREFIX_MEETING_ENDTIME).isPresent()) {
            findMeetingDescriptor.setEndTimeContainsKeywordsPredicate(
                    new EndTimeContainsKeywordsPredicate(
                            ParserUtil.parseEndTime(argMultimap.getValue(PREFIX_MEETING_ENDTIME).get())));
        }

        if (!findMeetingDescriptor.isAnyFieldFound()) {
            throw new ParseException(FindMeetingCommand.MESSAGE_USAGE);
        }

        return new FindMeetingCommand(findMeetingDescriptor);
    }

}
