package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddBookingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code AddBookingCommand} object
 */
public class AddBookingCommandParser implements Parser<AddBookingCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddBookingCommand}
     * and returns a {@code AddBookingCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBookingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PERSON_ID, PREFIX_ROOM_ID,
                PREFIX_START_DATE, PREFIX_END_DATE);


        if (!arePrefixesPresent(argMultimap, PREFIX_PERSON_ID, PREFIX_ROOM_ID, PREFIX_START_DATE, PREFIX_END_DATE)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE));
        }

        assert argMultimap.getValue(PREFIX_PERSON_ID).isPresent();
        assert argMultimap.getValue(PREFIX_ROOM_ID).isPresent();
        assert argMultimap.getValue(PREFIX_START_DATE).isPresent();
        assert argMultimap.getValue(PREFIX_END_DATE).isPresent();

        int personId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_PERSON_ID).get());
        int roomId = ParserUtil.parseRoomId(argMultimap.getValue(PREFIX_ROOM_ID).get());
        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());

        if (!startDate.isBefore(endDate)) {
            throw new ParseException(MESSAGE_INVALID_START_END_DATE);
        }

        assert startDate.isBefore(endDate);

        return new AddBookingCommand(personId, roomId, startDate, endDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
