package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROUTINE;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.routines.RoutineAddExerciseCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.routine.Routine;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;

/**
 * Parses input arguments and creates a new RoutineAddExerciseCommand object
 */
public class RoutineAddExerciseCommandParser implements Parser<RoutineAddExerciseCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineAddExerciseCommand
     * and returns a RoutineAddExerciseCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RoutineAddExerciseCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROUTINE, PREFIX_EXERCISE);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROUTINE, PREFIX_EXERCISE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RoutineAddExerciseCommand.MESSAGE_USAGE));
        } else if (argMultimap.getAllValues(PREFIX_ROUTINE).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineAddExerciseCommand.MESSAGE_USAGE)
            );
        } else if (argMultimap.getAllValues(PREFIX_EXERCISE).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineAddExerciseCommand.MESSAGE_USAGE)
            );
        }

        Name routineName = ParserUtil.parseName(argMultimap.getValue(PREFIX_ROUTINE).get());

        Name exerciseName = ParserUtil.parseName(argMultimap.getValue(PREFIX_EXERCISE).get());
        Routine routine = new Routine(routineName);
        Set<Tag> tagList = new HashSet<>();
        Exercise exercise = new Exercise(exerciseName, tagList);

        return new RoutineAddExerciseCommand(routine, exercise);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
