package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ItemParserUtil.REGEX_ENTRIES;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FindByTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.TagMatchesKeywordsPredicate;

public class FindByTagCommandParser implements Parser<FindByTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindByTagCommand
     * and returns a FindByTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FindByTagCommand parse(String userInput) throws ParseException {
        String trimmedArgs = userInput.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindByTagCommand.MESSAGE_USAGE));
        }

        List<String> tagKeywords = Arrays.stream(trimmedArgs.split(REGEX_ENTRIES))
                .map(String::strip)
                .collect(Collectors.toList());
        return new FindByTagCommand(new TagMatchesKeywordsPredicate(tagKeywords));
    }
}
