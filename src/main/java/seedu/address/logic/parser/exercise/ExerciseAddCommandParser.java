package seedu.address.logic.parser.exercise;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.exercise.ExerciseAddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;

/**
 * Parses input arguments and creates a new ExerciseAddCommand object
 */
public class ExerciseAddCommandParser implements Parser<ExerciseAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExerciseAddCommand
     * and returns an ExerciseAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExerciseAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_EXERCISE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_EXERCISE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExerciseAddCommand.MESSAGE_USAGE));
        } else if (argMultimap.getAllValues(PREFIX_EXERCISE).size() != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExerciseAddCommand.MESSAGE_USAGE)
            );
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_EXERCISE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Exercise exercise = new Exercise(name, tagList);

        return new ExerciseAddCommand(exercise);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
