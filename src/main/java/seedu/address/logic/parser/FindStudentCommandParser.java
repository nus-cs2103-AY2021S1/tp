package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindStudentCommand object.
 */
public class FindStudentCommandParser implements Parser<FindStudentCommand> {

    /**
     * Parses
     */
    public FindStudentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStudentCommand.MESSAGE_USAGE)
            );
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindStudentCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
