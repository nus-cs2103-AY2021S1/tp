package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ItemParserUtil.REGEX_ENTRIES;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.NameMatchesKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindItemCommand object
 */
public class FindItemCommandParser implements Parser<FindItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindItemCommand
     * and returns a FindItemCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindItemCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindItemCommand.MESSAGE_USAGE));
        }

        List<String> nameKeywords = Arrays.stream(trimmedArgs.split(REGEX_ENTRIES))
                .map(String::strip)
                .collect(Collectors.toList());

        return new FindItemCommand(new NameMatchesKeywordsPredicate(nameKeywords));
    }

}
