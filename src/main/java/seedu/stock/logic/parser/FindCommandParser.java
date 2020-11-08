package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.FindCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.predicates.FieldContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.LocationContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.NameContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.SerialNumberContainsKeywordsPredicate;
import seedu.stock.model.stock.predicates.SourceContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private static final Prefix[] allPossiblePrefixes = CliSyntax.getAllPossiblePrefixesAsArray();
    private static final Prefix[]
            validPrefixesForFind = { PREFIX_NAME, PREFIX_LOCATION, PREFIX_SOURCE, PREFIX_SERIAL_NUMBER };
    private static final Logger logger = LogsCenter.getLogger(FindCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Starting to parse find command");
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, allPossiblePrefixes);

        // Check if command format is correct
        if (!ParserUtil.isAnyPrefixPresent(argMultimap, validPrefixesForFind)
                || ParserUtil.isInvalidPrefixPresent(argMultimap, validPrefixesForFind)
                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        if (ParserUtil.isDuplicatePrefixPresent(argMultimap, validPrefixesForFind)) {
            throw new ParseException(String.format(MESSAGE_DUPLICATE_HEADER_FIELD,
                    FindCommand.MESSAGE_USAGE));
        }

        // Get the predicates to test to find stocks wanted
        List<FieldContainsKeywordsPredicate> predicatesToTest =
                parsePrefixAndKeywords(argMultimap, validPrefixesForFind);

        logger.log(Level.INFO, "Finished parsing find command successfully");
        return new FindCommand(predicatesToTest);
    }

    /**
     * Returns a list of predicates to filter stocks based on user's search fields and terms.
     * @param argumentMultimap map of prefix to keywords entered by user
     * @param prefixes prefixes to parse
     * @return list of predicates to filter stocks
     */
    private static List<FieldContainsKeywordsPredicate> parsePrefixAndKeywords(
            ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .map(prefix -> generatePredicate(prefix, argumentMultimap.getValue(prefix).get()))
                .collect(Collectors.toList());
    }

    /**
     * Returns a field predicate to test whether a {@code Stock}'s {@code field} matches or contains
     * any of the keywords given.
     * @param prefix prefix for field
     * @param keywordsToFind keywords to match with the stock's field
     * @return predicate filter stocks based on field
     */
    private static FieldContainsKeywordsPredicate generatePredicate(Prefix prefix, String keywordsToFind) {
        final FieldContainsKeywordsPredicate fieldContainsKeywordsPredicate;
        String trimmedKeywordsToFind = keywordsToFind.trim();
        String[] keywords = trimmedKeywordsToFind.split("\\s+");

        switch(prefix.getPrefix()) {
        case "n/":
            // predicate to test name field of stock
            fieldContainsKeywordsPredicate =
                    new NameContainsKeywordsPredicate(Arrays.asList(keywords));
            break;

        case "sn/":
            // predicate to test serial number field of stock
            fieldContainsKeywordsPredicate =
                    new SerialNumberContainsKeywordsPredicate(Arrays.asList(keywords));
            break;

        case "s/":
            // predicate to test source field of stock
            fieldContainsKeywordsPredicate =
                    new SourceContainsKeywordsPredicate(Arrays.asList(keywords));
            break;

        case "l/":
            // predicate to test location stored field of stock
            fieldContainsKeywordsPredicate =
                    new LocationContainsKeywordsPredicate(Arrays.asList(keywords));
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + prefix);
        }

        return fieldContainsKeywordsPredicate;
    }

}
