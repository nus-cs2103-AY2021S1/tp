package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import seedu.address.logic.commands.ArchiveBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ArchiveBookingCommandParser implements Parser<ArchiveBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ArchiveBookingCommand
     * and returns a ArchiveBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ArchiveBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOOKING_ID);
        if (argMultiMap.getValue(PREFIX_BOOKING_ID).isEmpty() || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ArchiveBookingCommand.MESSAGE_USAGE));
        }

        assert argMultiMap.getValue(PREFIX_BOOKING_ID).isPresent();
        int bookingId = ParserUtil.parseBookingId(argMultiMap.getValue(PREFIX_BOOKING_ID).get());

        return new ArchiveBookingCommand(bookingId);
    }
}
