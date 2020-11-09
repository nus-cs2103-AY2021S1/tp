package seedu.address.logic.parser.attraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.attraction.FindAttractionCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attraction.AttractionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindAttractionCommand object
 */
public class FindAttractionCommandParser implements Parser<FindAttractionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAttractionCommand
     * and returns a FindAttractionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAttractionCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAttractionCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindAttractionCommand(new AttractionContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
