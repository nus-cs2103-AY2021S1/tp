package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.GetBillCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code GetBillCommand} object
 */
public class GetBillCommandParser implements Parser<GetBillCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code GetBillCommand}
     * and returns a {@code GetBillCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public GetBillCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOOKING_ID);

        if (!arePrefixesPresent(argMultiMap, PREFIX_BOOKING_ID) || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetBillCommand.MESSAGE_USAGE));
        }

        int bookingId = ParserUtil.parseBookingId(argMultiMap.getValue(PREFIX_BOOKING_ID).get());

        return new GetBillCommand(bookingId);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
