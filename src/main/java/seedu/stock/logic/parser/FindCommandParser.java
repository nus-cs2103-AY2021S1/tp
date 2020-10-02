package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.*;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.stock.logic.commands.AddCommand;
import seedu.stock.logic.commands.FindCommand;
import seedu.stock.logic.commands.HelpCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.exceptions.StockNotFoundException;
import seedu.stock.model.stock.predicates.LocationContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.NameContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.SerialNumberContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.SourceContainsKeywordsPredicate;

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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SOURCE, PREFIX_SERIALNUMBER, PREFIX_LOCATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LOCATION, PREFIX_SOURCE, PREFIX_SERIALNUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> prefixesPresentAndKeywords =
                parsePrefixAndKeywords(argMultimap, PREFIX_NAME, PREFIX_LOCATION, PREFIX_SOURCE, PREFIX_SERIALNUMBER);

        List<Predicate<Stock>> predicates = getPredicates(prefixesPresentAndKeywords);

        return new FindCommand(predicates);
    }

    /**
     * Returns true if any one of the prefixes does not contain an empty {@code Optional} value
     * in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static List<String> parsePrefixAndKeywords(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .map(prefix -> prefix.getPrefix() + argumentMultimap.getValue(prefix))
                .collect(Collectors.toList());
    }

    private static List<Predicate<Stock>> getPredicates(List<String> prefixesPresentAndKeywords) {
        return prefixesPresentAndKeywords
                .stream().map(FindCommandParser::parsePredicate).collect(Collectors.toList());
    }

    private static Predicate<Stock> parsePredicate(String prefixAndKeywords) {
        String trimmedArgs = prefixAndKeywords.trim();
        final Matcher findMatcher = BASIC_FIND_FORMAT.matcher(trimmedArgs);

        // keywords to find in field
        final String keyWordsToFind = findMatcher.group("keyWordsToFind");
        String trimmedKeyWordsToFind = keyWordsToFind.trim();
        String[] keyWords = trimmedKeyWordsToFind.split("\\s+");

        // prefix for field to find
        final String prefix = findMatcher.group("commandPrefix");

        return getPredicate(prefix, keyWords);

    }

    private static Predicate<Stock> getPredicate(String prefix, String[] keyWords) {
        final Predicate<Stock> fieldContainsKeywordsPredicate;
        switch(prefix) {
        case "n/":
            fieldContainsKeywordsPredicate =
                    new NameContainsKeywordsPredicate(Arrays.asList(keyWords));
            break;

        case "sn/":
            fieldContainsKeywordsPredicate =
                    new SerialNumberContainsKeywordsPredicate(Arrays.asList(keyWords));
            break;

        case "s/":
            fieldContainsKeywordsPredicate =
                    new SourceContainsKeywordsPredicate(Arrays.asList(keyWords));
            break;

        case "l/":
            fieldContainsKeywordsPredicate =
                    new LocationContainsKeywordsPredicate(Arrays.asList(keyWords));
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + prefix);
        }

        return fieldContainsKeywordsPredicate;
    }


}
