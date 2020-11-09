package seedu.address.logic.parser.meetingparser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEETING_VENUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.meetingcommands.EditMeetingCommand;
import seedu.address.logic.commands.meetingcommands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.id.IdParserUtil;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditMeetingCommandParser implements Parser<EditMeetingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditMeetingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MEETING_TYPE, PREFIX_MEETING_BIDDER_ID,
                        PREFIX_MEETING_PROPERTY_ID, PREFIX_MEETING_VENUE, PREFIX_MEETING_DATE,
                        PREFIX_MEETING_STARTTIME, PREFIX_MEETING_ENDTIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_MEETING_PROPERTY_ID).isPresent()) {
            editMeetingDescriptor.setMeetingPropertyId(IdParserUtil.parsePropertyId(
                    argMultimap.getValue(PREFIX_MEETING_PROPERTY_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_MEETING_BIDDER_ID).isPresent()) {
            editMeetingDescriptor.setMeetingBidderId(IdParserUtil.parseBidderId(
                    argMultimap.getValue(PREFIX_MEETING_BIDDER_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_MEETING_VENUE).isPresent()) {
            editMeetingDescriptor.setMeetingVenue(ParserUtil.parseMeetingVenue(
                    argMultimap.getValue(PREFIX_MEETING_VENUE).get()));
        }
        if (argMultimap.getValue(PREFIX_MEETING_DATE).isPresent()) {
            editMeetingDescriptor.setMeetingDate(ParserUtil.parseDate(
                    argMultimap.getValue(PREFIX_MEETING_DATE).get()));
        }

        if (argMultimap.getValue(PREFIX_MEETING_STARTTIME).isPresent()) {
            editMeetingDescriptor.setMeetingStartTime(ParserUtil.parseStartTime(
                    argMultimap.getValue(PREFIX_MEETING_STARTTIME).get()));
        }

        if (argMultimap.getValue(PREFIX_MEETING_ENDTIME).isPresent()) {
            editMeetingDescriptor.setMeetingEndTime(ParserUtil.parseEndTime(
                    argMultimap.getValue(PREFIX_MEETING_ENDTIME).get()));
        }


        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
    }

}
