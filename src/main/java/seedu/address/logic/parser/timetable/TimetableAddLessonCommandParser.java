package seedu.address.logic.parser.timetable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.HashSet;
import java.util.stream.Stream;

import seedu.address.logic.commands.timetable.TimetableAddLessonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.util.Name;

/**
 * Parses input arguments and creates a new TimetableAddLessonCommand object
 */
public class TimetableAddLessonCommandParser implements Parser<TimetableAddLessonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RoutineAddExerciseCommand
     * and returns an RoutineAddExerciseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TimetableAddLessonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LESSON, PREFIX_DAY, PREFIX_TIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_LESSON, PREFIX_DAY, PREFIX_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TimetableAddLessonCommand.MESSAGE_USAGE));
        } else if (argMultimap.getAllValues(PREFIX_LESSON).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableAddLessonCommand.MESSAGE_USAGE)
            );
        } else if (argMultimap.getAllValues(PREFIX_DAY).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableAddLessonCommand.MESSAGE_USAGE)
            );
        } else if (argMultimap.getAllValues(PREFIX_TIME).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableAddLessonCommand.MESSAGE_USAGE)
            );
        }

        Name lessonName = ParserUtil.parseName(argMultimap.getValue(PREFIX_LESSON).get());

        Lesson lesson = new Lesson(lessonName, new HashSet<>());
        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_TIME).get());

        return new TimetableAddLessonCommand(lesson, day, duration);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
