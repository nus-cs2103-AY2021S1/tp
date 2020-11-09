package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.logic.commands.EditBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditBookingCommandParser implements Parser<EditBookingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOOKING_ID, PREFIX_ROOM_ID, PREFIX_START_DATE, PREFIX_END_DATE);

        if (argMultimap.getValue(PREFIX_BOOKING_ID).isEmpty() || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBookingCommand.MESSAGE_USAGE));
        }

        Integer bookingId = ParserUtil.parseBookingId(argMultimap.getValue(PREFIX_BOOKING_ID).get());

        EditBookingCommand.EditBookingDescriptor editBookingDescriptor = new EditBookingCommand.EditBookingDescriptor();
        if (argMultimap.getValue(PREFIX_ROOM_ID).isPresent()) {
            editBookingDescriptor.setRoomId(ParserUtil.parseRoomId(argMultimap.getValue(PREFIX_ROOM_ID).get()));
        }

        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            editBookingDescriptor.setStartDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get()));
        }

        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            editBookingDescriptor.setEndDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get()));
        }

        if (!editBookingDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditBookingCommand.MESSAGE_NOT_EDITED);
        }

        return new EditBookingCommand(bookingId, editBookingDescriptor);
    }
}
