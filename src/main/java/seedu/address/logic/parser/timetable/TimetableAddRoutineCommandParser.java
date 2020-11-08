package seedu.address.logic.parser.timetable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.timetable.TimetableAddRoutineCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.routine.Routine;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.util.Name;

/**
 * Parses input arguments and creates a new TimetableAddRoutineCommand object
 */
public class TimetableAddRoutineCommandParser implements Parser<TimetableAddRoutineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineAddExerciseCommand
     * and returns an RoutineAddExerciseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TimetableAddRoutineCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROUTINE, PREFIX_DAY, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROUTINE, PREFIX_DAY, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TimetableAddRoutineCommand.MESSAGE_USAGE));
        } else if (argMultimap.getAllValues(PREFIX_ROUTINE).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableAddRoutineCommand.MESSAGE_USAGE)
            );
        } else if (argMultimap.getAllValues(PREFIX_DAY).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableAddRoutineCommand.MESSAGE_USAGE)
            );
        } else if (argMultimap.getAllValues(PREFIX_TIME).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableAddRoutineCommand.MESSAGE_USAGE)
            );
        }

        Name routineName = ParserUtil.parseName(argMultimap.getValue(PREFIX_ROUTINE).get());

        Routine routine = new Routine(routineName);
        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_TIME).get());

        return new TimetableAddRoutineCommand(routine, day, duration);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
