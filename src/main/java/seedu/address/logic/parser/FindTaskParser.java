package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.NameContainsKeywordPredicate;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new FindTaskCommand object
 */
public class FindTaskParser implements Parser<FindTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTaskCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindTaskCommand(new NameContainsKeywordPredicate(Arrays.asList(nameKeywords)));
    }
}
