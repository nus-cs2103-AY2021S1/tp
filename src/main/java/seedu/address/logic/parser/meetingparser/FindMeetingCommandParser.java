package seedu.address.logic.parser.meetingparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;

import java.util.Arrays;

import seedu.address.logic.commands.meetingcommands.FindMeetingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.id.IdParserUtil;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.BidderIdContainsKeywordsPredicate;
import seedu.address.model.meeting.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.TimeContainsKeywordsPredicate;
import seedu.address.model.meeting.Venue;
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
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_TYPE, PREFIX_MEETING_BIDDER_ID,
                        PREFIX_MEETING_PROPERTY_ID, PREFIX_MEETING_VENUE, PREFIX_MEETING_TIME);

        try {
            if (argMultimap.getValue(PREFIX_MEETING_VENUE).isPresent()) {
                Venue venue = ParserUtil.parseMeetingVenue(argMultimap.getValue(PREFIX_MEETING_VENUE).get());
                return new FindMeetingCommand(
                        new VenueContainsKeywordsPredicate(Arrays.asList(venue.venue.split("\\s+"))),
                        null, null, null);
            } else if (argMultimap.getValue(PREFIX_MEETING_TIME).isPresent()) {
                Time time = ParserUtil.parseMeetingTime(argMultimap.getValue(PREFIX_MEETING_TIME).get());
                return new FindMeetingCommand(null,
                        new TimeContainsKeywordsPredicate(Arrays.asList(time.time.split("\\s+"))),
                        null, null);
            } else if (argMultimap.getValue(PREFIX_MEETING_BIDDER_ID).isPresent()) {
                BidderId bidderId =
                        IdParserUtil.parseBidderId(argMultimap.getValue(PREFIX_MEETING_BIDDER_ID).get());
                return new FindMeetingCommand(null, null,
                        new BidderIdContainsKeywordsPredicate(Arrays.asList(bidderId.toString()
                                .split("\\s+"))),
                        null);
            } else if (argMultimap.getValue(PREFIX_MEETING_PROPERTY_ID).isPresent()) {
                PropertyId propertyId =
                        IdParserUtil.parsePropertyId(argMultimap.getValue(PREFIX_MEETING_PROPERTY_ID).get());
                return new FindMeetingCommand(null, null, null,
                        new PropertyIdContainsKeywordsPredicate(Arrays.asList(propertyId.toString()
                                .split("\\s+"))));
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMeetingCommand.MESSAGE_USAGE));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindMeetingCommand.MESSAGE_USAGE), pe);
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindMeetingCommand.MESSAGE_USAGE), e);
        }
    }

}
