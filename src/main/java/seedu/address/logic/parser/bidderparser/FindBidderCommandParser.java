package seedu.address.logic.parser.bidderparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.biddercommands.FindBidderCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindBidderCommand object
 */
public class FindBidderCommandParser implements Parser<FindBidderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindBidderCommand
     * and returns a FindBidderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindBidderCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBidderCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindBidderCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
