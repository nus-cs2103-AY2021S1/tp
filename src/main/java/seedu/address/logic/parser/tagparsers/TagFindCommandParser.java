package seedu.address.logic.parser.tagparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.tagcommands.TagFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.TagNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new TagFindCommand object
 */
public class TagFindCommandParser implements Parser<TagFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TagFindCommand
     * and returns a TagFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public TagFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new TagFindCommand(new TagNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
