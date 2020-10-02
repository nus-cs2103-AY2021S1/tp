package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.stock.logic.commands.FindCommand;
import seedu.stock.logic.commands.HelpCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    /**
     * Used for initial separation of prefix and keywords to find.
     */
    private static final Pattern BASIC_FIND_FORMAT = Pattern
            .compile("(?<commandPrefix>.*/)(?<keyWordsToFind>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        final Matcher findMatcher = BASIC_FIND_FORMAT.matcher(trimmedArgs);

        boolean isEmptyArgument = trimmedArgs.isEmpty();
        boolean isMatch = findMatcher.matches();
        // check if argument matches find format
        if (!isMatch || isEmptyArgument) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // keywords to find in field
        final String keyWordsToFind = findMatcher.group("keyWordsToFind");
        String trimmedKeyWordsToFind = keyWordsToFind.trim();
        String[] keyWords = trimmedKeyWordsToFind.split("\\s+");

        // prefix for field to find
        final String commandPrefix = findMatcher.group("commandPrefix");

        return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(keyWords)));
    }

}
