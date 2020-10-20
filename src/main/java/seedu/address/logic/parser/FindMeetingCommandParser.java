package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_VENUE;

import java.util.Arrays;

import seedu.address.logic.commands.FindMeetingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.calendar.BidderIdContainsKeywordsPredicate;
import seedu.address.model.calendar.CalendarBidderId;
import seedu.address.model.calendar.CalendarPropertyId;
import seedu.address.model.calendar.CalendarTime;
import seedu.address.model.calendar.CalendarVenue;
import seedu.address.model.calendar.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.calendar.TimeContainsKeywordsPredicate;
import seedu.address.model.calendar.VenueContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindMeetingCommandParser implements Parser<FindMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CALENDAR_TYPE, PREFIX_CALENDAR_BIDDER_ID,
                        PREFIX_CALENDAR_PROPERTY_ID, PREFIX_CALENDAR_VENUE, PREFIX_CALENDAR_TIME);

        try {
            if (argMultimap.getValue(PREFIX_CALENDAR_VENUE).isPresent()) {
                CalendarVenue venue = ParserUtil.parseCalendarVenue(argMultimap.getValue(PREFIX_CALENDAR_VENUE).get());
                return new FindMeetingCommand(
                        new VenueContainsKeywordsPredicate(Arrays.asList(venue.venue.split("\\s+"))),
                        null, null, null);
            } else if (argMultimap.getValue(PREFIX_CALENDAR_TIME).isPresent()) {
                CalendarTime time = ParserUtil.parseCalendarTime(argMultimap.getValue(PREFIX_CALENDAR_TIME).get());
                return new FindMeetingCommand(null,
                        new TimeContainsKeywordsPredicate(Arrays.asList(time.time.split("\\s+"))),
                        null, null);
            } else if (argMultimap.getValue(PREFIX_CALENDAR_BIDDER_ID).isPresent()) {
                CalendarBidderId bidderId =
                        ParserUtil.parseCalendarBidderId(argMultimap.getValue(PREFIX_CALENDAR_BIDDER_ID).get());
                return new FindMeetingCommand(null, null,
                        new BidderIdContainsKeywordsPredicate(Arrays.asList(bidderId.bidderId.split("\\s+"))),
                        null);
            } else if (argMultimap.getValue(PREFIX_CALENDAR_PROPERTY_ID).isPresent()) {
                CalendarPropertyId propertyId =
                        ParserUtil.parseCalendarPropertyId(argMultimap.getValue(PREFIX_CALENDAR_PROPERTY_ID).get());
                return new FindMeetingCommand(null, null, null,
                        new PropertyIdContainsKeywordsPredicate(Arrays.asList(propertyId.propertyId.split("\\s+"))));
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
