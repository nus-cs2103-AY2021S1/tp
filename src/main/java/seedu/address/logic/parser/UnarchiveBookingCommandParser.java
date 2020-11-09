package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import seedu.address.logic.commands.UnarchiveBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnarchiveBookingCommandParser implements Parser<UnarchiveBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UnarchiveBookingCommand
     * and returns an UnarchiveBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnarchiveBookingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOOKING_ID);
        if (argMultiMap.getValue(PREFIX_BOOKING_ID).isEmpty() || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnarchiveBookingCommand.MESSAGE_USAGE));
        }

        assert argMultiMap.getValue(PREFIX_BOOKING_ID).isPresent();
        int bookingId = ParserUtil.parseBookingId(argMultiMap.getValue(PREFIX_BOOKING_ID).get());

        return new UnarchiveBookingCommand(bookingId);
    }
}
