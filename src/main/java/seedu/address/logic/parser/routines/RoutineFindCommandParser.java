package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.routines.RoutineFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.routine.RoutineNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new RoutineFindCommand object
 */
public class RoutineFindCommandParser implements Parser<RoutineFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RoutineFindCommand
     * and returns a RoutineFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RoutineFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RoutineFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new RoutineFindCommand(new RoutineNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
