package seedu.address.logic.parser.exercise;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.exercise.ExerciseFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.ExerciseNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ExerciseFindCommand object
 */
public class ExerciseFindCommandParser implements Parser<ExerciseFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExerciseFindCommand
     * and returns a ExerciseFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExerciseFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExerciseFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new ExerciseFindCommand(new ExerciseNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
