package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.medibook.logic.commands.FindCommand;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.patient.FieldContainsKeywordsPredicate;
import seedu.medibook.model.patient.Patient;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    // array of prefixes currently supported by the find command.
    public static final Prefix[] SUPPORTED_SEARCH_PREFIXES =
            new Prefix[]{
                PREFIX_NAME, PREFIX_IC, PREFIX_DATE, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_HEIGHT,
                PREFIX_WEIGHT, PREFIX_ADDRESS, PREFIX_BLOOD_TYPE};

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, SUPPORTED_SEARCH_PREFIXES);

        boolean isArgumentInvalid = anyPrefixEmpty(argMultimap)
                || noPrefixesSpecified(argMultimap)
                || !argMultimap.getPreamble().isEmpty();
        if (isArgumentInvalid) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Predicate<Patient>> predicates =
                Arrays.stream(SUPPORTED_SEARCH_PREFIXES)
                .filter(prefix -> prefixNotEmpty(argMultimap, prefix))
                .map(prefix -> mapToPredicate(argMultimap, prefix))
                .collect(Collectors.toList());

        return new FindCommand(predicates);
    }

    /**
     * Returns true if the prefixes by the user are all unsupported.
     */
    private boolean noPrefixesSpecified(ArgumentMultimap argumentMultimap) {
        return Stream.of(SUPPORTED_SEARCH_PREFIXES).allMatch(prefix -> argumentMultimap.getValue(prefix).isEmpty());
    }

    /**
     * Returns true if any of the prefixes that were specified by the user contains an empty {@code String}
     * in the given * {@code ArgumentMultimap}.
     */
    private boolean anyPrefixEmpty(ArgumentMultimap argumentMultimap) {
        return Stream.of(SUPPORTED_SEARCH_PREFIXES).anyMatch(prefix -> {
            Optional<String> keyword = argumentMultimap.getValue(prefix);

            boolean isPrefixSpecified = keyword.isPresent();
            if (!isPrefixSpecified) {
                return false;
            }

            return keyword.get().isEmpty();
        });
    }

    private boolean prefixNotEmpty(ArgumentMultimap argMultimap, Prefix prefix) {
        String keyword = argMultimap.getValue(prefix).orElse("");
        return !keyword.isEmpty();
    }

    private FieldContainsKeywordsPredicate mapToPredicate(ArgumentMultimap argMultimap, Prefix prefix) {
        List<String> keywords = Arrays.asList(argMultimap.getValue(prefix).get().split("\\s+"));
        return new FieldContainsKeywordsPredicate(keywords, prefix);
    }

}
