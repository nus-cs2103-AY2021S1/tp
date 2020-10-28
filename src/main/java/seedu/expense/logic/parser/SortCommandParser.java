package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.commands.SortCommand.REVERSE_KEYWORD;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_SORT;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.expense.logic.commands.SortCommand;
import seedu.expense.logic.parser.exceptions.ParseException;
import seedu.expense.model.expense.AmountComparator;
import seedu.expense.model.expense.DateComparator;
import seedu.expense.model.expense.DescriptionComparator;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final String VALIDATION_REGEX =
            "(?<keyword>" + DescriptionComparator.SORT_KEYWORD + "|" + AmountComparator.SORT_KEYWORD + "|"
                    + DateComparator.SORT_KEYWORD + ")";

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public SortCommand parse(String args) throws ParseException {
        // argMultimap stores mapping of prefixes to respective arguments
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SORT);
        List<String> sortKeys = argMultimap.getAllValues(PREFIX_SORT);
        for (String sortKey : sortKeys) {
            if (!sortKey.matches(VALIDATION_REGEX + "(" + REVERSE_KEYWORD + ")?")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }
        if (sortKeys.isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        // filter and clean up repeats. Only take the latest entry if conflicting entries are found
        Set<String> sortKeysUnique = getUniqueSortKeys(sortKeys);

        DescriptionComparator descriptionComparator = new DescriptionComparator(false, false, -1);
        AmountComparator amountComparator = new AmountComparator(false, false, -1);
        DateComparator dateComparator = new DateComparator(false, false, -1);

        int index = 0;
        for (String sortKey : sortKeysUnique) {
            if (sortKey.contains(DescriptionComparator.SORT_KEYWORD)) {
                descriptionComparator = new DescriptionComparator(true,
                        sortKey.matches(VALIDATION_REGEX + REVERSE_KEYWORD), index);
            } else if (sortKey.contains(AmountComparator.SORT_KEYWORD)) {
                amountComparator = new AmountComparator(true,
                        sortKey.matches(VALIDATION_REGEX + REVERSE_KEYWORD), index);
            } else if (sortKey.contains(DateComparator.SORT_KEYWORD)) {
                dateComparator = new DateComparator(true,
                        sortKey.matches(VALIDATION_REGEX + REVERSE_KEYWORD), index);
            }
            index++;
        }

        return new SortCommand(descriptionComparator, amountComparator, dateComparator);
    }

    private static Set<String> getUniqueSortKeys(Collection<String> sortKeys) {
        Set<String> sortKeysUnique = new HashSet<>();
        for (String sortKey : sortKeys) {
            Matcher m = Pattern.compile(VALIDATION_REGEX).matcher(sortKey);
            Matcher mReverse = Pattern.compile(VALIDATION_REGEX + REVERSE_KEYWORD).matcher(sortKey);
            if (m.matches()) {
                if (sortKeysUnique.contains(sortKey + REVERSE_KEYWORD)) {
                    sortKeysUnique.remove(sortKey + REVERSE_KEYWORD);
                }
                sortKeysUnique.add(sortKey);
            } else if (mReverse.matches()) {
                if (sortKeysUnique.contains(mReverse.group("keyword"))) {
                    sortKeysUnique.remove(mReverse.group("keyword"));
                }
                sortKeysUnique.add(sortKey);
            }
        }
        return sortKeysUnique;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
