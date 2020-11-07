package seedu.address.logic.parser.timetable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.timetable.TimetableDeleteSlotCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.timetable.Slot;

/**
 * Parses input arguments and creates a new TimetableDeleteSlotCommand object
 */
public class TimetableDeleteSlotCommandParser implements Parser<TimetableDeleteSlotCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineAddExerciseCommand
     * and returns an RoutineAddExerciseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TimetableDeleteSlotCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DAY, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DAY, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TimetableDeleteSlotCommand.MESSAGE_USAGE));
        } else if (argMultimap.getAllValues(PREFIX_DAY).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableDeleteSlotCommand.MESSAGE_USAGE)
            );
        } else if (argMultimap.getAllValues(PREFIX_TIME).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableDeleteSlotCommand.MESSAGE_USAGE)
            );
        }

        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_TIME).get());

        Slot slotToFind = new Slot(day, duration);
        return new TimetableDeleteSlotCommand(slotToFind);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
