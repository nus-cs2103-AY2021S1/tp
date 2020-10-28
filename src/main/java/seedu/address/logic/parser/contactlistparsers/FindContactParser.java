package seedu.address.logic.parser.contactlistparsers;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.contactlistcommands.FindContactCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.ContactContainsTagsPredicate;
import seedu.address.model.contact.FindContactCriteria;
import seedu.address.model.contact.NameContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindContactCommand object
 */
public class FindContactParser implements Parser<FindContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a FindContactCommand object for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public FindContactCommand parse(String args) throws ParseException {
        ArgumentTokenizer tokenizer = new ArgumentTokenizer(args,
                PREFIX_NAME, PREFIX_TAG);
        ArgumentMultimap argMultiMap = tokenizer.tokenize();
        if (!isAtLeastOnePrefixPresent(argMultiMap, PREFIX_NAME, PREFIX_TAG)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }

        FindContactCriteria findContactCriteria = new FindContactCriteria();

        if (argMultiMap.getValue(PREFIX_NAME).isPresent()) {
            List<String> keywordsAsList = parseSearchKeywords(argMultiMap.getValue(PREFIX_NAME).get());
            NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(keywordsAsList);
            findContactCriteria.addPredicate(namePredicate);
        }

        if (argMultiMap.getValue(PREFIX_TAG).isPresent()) {
            Set<Tag> taskTags = ParserUtil.parseTags(argMultiMap.getAllValues(PREFIX_TAG));
            ContactContainsTagsPredicate tagsPredicate = new ContactContainsTagsPredicate(taskTags);
            findContactCriteria.addPredicate(tagsPredicate);
        }

        return new FindContactCommand(findContactCriteria);
    }

    /**
     * Parses the String containing the search keywords provided by the user into a list of keywords.
     *
     * @param keywords String containing search keywords provided by the user.
     * @return List of search keywords.
     * @throws ParseException If no valid keyword was provided by the user.
     */
    private List<String> parseSearchKeywords(String keywords) throws ParseException {
        requireNonNull(keywords);
        String trimmedKeywords = keywords.trim();
        if (trimmedKeywords.isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        } else {
            String[] keywordArray = keywords.split("\\s+");
            assert keywordArray.length > 0 : "There must be at least one search keyword provided";
            return Arrays.asList(keywordArray);
        }
    }

    /**
     * Determines if at least one of the prefixes does not contain an empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     *
     * @param argumentMultimap ArgumentMultimap object containing the mapping of Prefixes to their respective arguments.
     * @param prefixes Prefixes to check for in the ArgumentMultimap.
     * @return True if at least one prefix has an argument mapped to it, false otherwise.
     */
    private static boolean isAtLeastOnePrefixPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
