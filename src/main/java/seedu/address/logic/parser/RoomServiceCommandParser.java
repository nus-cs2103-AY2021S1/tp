package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_SERVICE_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.RoomServiceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;

public class RoomServiceCommandParser implements Parser<RoomServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RoomServiceCommand}
     * and returns a {@code RoomServiceCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public RoomServiceCommand parse(String args) throws ParseException {
        assert args != null;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BOOKING_ID, PREFIX_ROOM_SERVICE_TYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_BOOKING_ID, PREFIX_ROOM_SERVICE_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoomServiceCommand.MESSAGE_USAGE));
        }

        assert argMultimap.getValue(PREFIX_BOOKING_ID).isPresent();
        assert argMultimap.getValue(PREFIX_ROOM_SERVICE_TYPE).isPresent();

        Integer bookingId = ParserUtil.parseBookingId(argMultimap.getValue(PREFIX_BOOKING_ID).get());
        String type = argMultimap.getValue(PREFIX_ROOM_SERVICE_TYPE).get();

        // make user input case insensitive
        String processedType = type.toLowerCase();

        // matching case-insensitive room services
        String dining = RoomServiceType.DINING.getName().toLowerCase();
        String wifi = RoomServiceType.WIFI.getName().toLowerCase();
        String massage = RoomServiceType.MASSAGE.getName().toLowerCase();

        if (processedType.equals(dining)) {
            return new RoomServiceCommand(new RoomService(bookingId, RoomServiceType.DINING));
        } else if (processedType.equals(wifi)) {
            return new RoomServiceCommand(new RoomService(bookingId, RoomServiceType.WIFI));
        } else if (processedType.equals(massage)) {
            return new RoomServiceCommand(new RoomService(bookingId, RoomServiceType.MASSAGE));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoomServiceCommand.MESSAGE_USAGE));
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
