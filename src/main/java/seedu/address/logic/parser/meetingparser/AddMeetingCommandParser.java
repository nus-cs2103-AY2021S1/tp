package seedu.address.logic.parser.meetingparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;

import java.util.stream.Stream;

import seedu.address.logic.commands.meetingcommands.AddMeetingCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.id.IdParserUtil;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.Admin;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Paperwork;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.Venue;
import seedu.address.model.meeting.Viewing;


/**
* Parses input arguments and creates a new AddCommand object
*/
public class AddMeetingCommandParser implements Parser<AddMeetingCommand> {

    /**
    * Parses the given {@code String} of arguments in the context of the AddMeetingCommand
    * and returns an AddCommand object for execution.
    * @throws ParseException if the user input does not conform the expected format
    */
    public AddMeetingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_TYPE, PREFIX_MEETING_BIDDER_ID,
                        PREFIX_MEETING_PROPERTY_ID, PREFIX_MEETING_VENUE, PREFIX_MEETING_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_MEETING_BIDDER_ID, PREFIX_MEETING_PROPERTY_ID,
                PREFIX_MEETING_PROPERTY_ID, PREFIX_MEETING_VENUE, PREFIX_MEETING_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        }

        try {
            if (argMultimap.getValue(PREFIX_MEETING_TYPE).isPresent()
                    & argMultimap.getValue(PREFIX_MEETING_BIDDER_ID).isPresent()
                    & argMultimap.getValue(PREFIX_MEETING_PROPERTY_ID).isPresent()
                    & argMultimap.getValue(PREFIX_MEETING_VENUE).isPresent()
                    & argMultimap.getValue(PREFIX_MEETING_TIME).isPresent()) {
                Venue venue = ParserUtil.parseMeetingVenue(argMultimap.getValue(PREFIX_MEETING_VENUE).get());
                Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_MEETING_TIME).get());
                PropertyId propertyId =
                        IdParserUtil.parsePropertyId(argMultimap.getValue(PREFIX_MEETING_PROPERTY_ID).get());
                BidderId bidderId =
                        IdParserUtil.parseBidderId(argMultimap.getValue(PREFIX_MEETING_BIDDER_ID).get());
                String type = ParserUtil.parseMeetingType(argMultimap.getValue(PREFIX_MEETING_TYPE).get());

                if (type.contains("p")) {
                    Meeting meeting = new Paperwork(bidderId, propertyId,
                            time, venue);
                    return new AddMeetingCommand(meeting);
                } else if (type.contains("a")) {
                    Meeting meeting = new Admin(bidderId, propertyId,
                            time, venue);
                    return new AddMeetingCommand(meeting);
                } else if (type.contains("v")) {
                    Meeting meeting = new Viewing(bidderId, propertyId,
                            time, venue);
                    return new AddMeetingCommand(meeting);
                }
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMeetingCommand.MESSAGE_USAGE));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMeetingCommand.MESSAGE_USAGE), pe);
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMeetingCommand.MESSAGE_USAGE), e);
        }

    }

    /**
    * Returns true if none of the prefixes contains empty {@code Optional} values in the given
    * {@code ArgumentMultimap}.
    */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
