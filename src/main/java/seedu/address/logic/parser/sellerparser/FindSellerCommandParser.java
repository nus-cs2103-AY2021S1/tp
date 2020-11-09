package seedu.address.logic.parser.sellerparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.sellercommands.FindSellerCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindSellerCommand object
 */
public class FindSellerCommandParser implements Parser<FindSellerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindSellerCommand
     * and returns a FindSellerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindSellerCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindSellerCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindSellerCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
