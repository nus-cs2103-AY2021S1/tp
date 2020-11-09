package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDate;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.FilterRoomCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code FilterRoomCommand} object
 */
public class FilterRoomCommandParser implements Parser<FilterRoomCommand> {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    /**
     * Parses the given {@code String} of arguments in the context of the {@code FilterRoomCommand}
     * and returns a {@code FilterRoomCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterRoomCommand parse(String args) throws ParseException {
        logger.info("=============================[ Parsing filterRoom ]===========================");
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE,
                PREFIX_ROOM_TYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_START_DATE, PREFIX_END_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterRoomCommand.MESSAGE_USAGE));
        }

        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_START_DATE).get());
        LocalDate endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_END_DATE).get());
        int roomType = 0;

        if (!startDate.isBefore(endDate)) {
            throw new ParseException(Messages.MESSAGE_INVALID_START_END_DATE);
        }

        if (argMultimap.getValue(PREFIX_ROOM_TYPE).isPresent()) {
            roomType = ParserUtil.parseRoomType(argMultimap.getValue(PREFIX_ROOM_TYPE).get());
        }

        return new FilterRoomCommand(startDate, endDate, roomType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
