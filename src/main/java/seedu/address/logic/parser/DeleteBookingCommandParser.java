package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;

import seedu.address.logic.commands.DeleteBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteBookingCommand object
 */
public class DeleteBookingCommandParser implements Parser<DeleteBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBookingCommand
     * and returns a DeleteBookingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);


        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BOOKING_ID);

        if (argMultimap.getValue(PREFIX_BOOKING_ID).isPresent()) {
            Integer bookingId = ParserUtil.parseBookingId(argMultimap.getValue(PREFIX_BOOKING_ID).get());
            return new DeleteBookingCommand(bookingId);
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE));
        }
    }

}
