package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_BIDDER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_PROPERTY_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALENDAR_VENUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
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
                ArgumentTokenizer.tokenize(args, PREFIX_CALENDAR_TYPE, PREFIX_CALENDAR_BIDDER_ID,
                        PREFIX_CALENDAR_PROPERTY_ID, PREFIX_CALENDAR_VENUE, PREFIX_CALENDAR_TIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMeetingCommand.MESSAGE_USAGE), pe);
        }

        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        if (argMultimap.getValue(PREFIX_CALENDAR_BIDDER_ID).isPresent()) {
            editMeetingDescriptor.setCalendarBidderId(IdParserUtil.parseBidderId(
                    argMultimap.getValue(PREFIX_CALENDAR_BIDDER_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_CALENDAR_PROPERTY_ID).isPresent()) {
            editMeetingDescriptor.setCalendarPropertyId(IdParserUtil.parsePropertyId(
                    argMultimap.getValue(PREFIX_CALENDAR_PROPERTY_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_CALENDAR_VENUE).isPresent()) {
            editMeetingDescriptor.setCalendarVenue(ParserUtil.parseCalendarVenue(
                    argMultimap.getValue(PREFIX_CALENDAR_VENUE).get()));
        }
        if (argMultimap.getValue(PREFIX_CALENDAR_TIME).isPresent()) {
            editMeetingDescriptor.setCalendarTime(ParserUtil.parseCalendarTime(
                    argMultimap.getValue(PREFIX_CALENDAR_TIME).get()));
        }

        if (!editMeetingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditMeetingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMeetingCommand(index, editMeetingDescriptor);
    }

}
