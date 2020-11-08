package seedu.address.logic.parser.lessons;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.lessons.LessonFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.LessonNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new LessonFindCommand object
 */
public class LessonFindCommandParser implements Parser<LessonFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LessonFindCommand
     * and returns a LessonFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public LessonFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new LessonFindCommand(new LessonNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
