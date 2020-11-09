package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindProblemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.problem.DescriptionContainsKeywordsPredicate;




public class FindProblemCommandParser implements Parser<FindProblemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindProblemCommand
     * and returns a FindProblemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindProblemCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindProblemCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindProblemCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
