package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_KEYWORD;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.DepartmentContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.OfficeContainsKeywordsPredicate;
import seedu.address.model.person.predicates.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEPARTMENT, PREFIX_OFFICE,
                PREFIX_REMARK, PREFIX_TAG);

        List<Predicate<Person>> predicates = new ArrayList<>();

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DEPARTMENT, PREFIX_OFFICE,
            PREFIX_REMARK, PREFIX_TAG)) {

            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedArgs.split("\\s+");
            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        checkDuplicatePrefix(argMultimap, PREFIX_NAME, PREFIX_DEPARTMENT, PREFIX_OFFICE,
            PREFIX_REMARK, PREFIX_TAG);

        getKeywords(argMultimap, PREFIX_NAME)
            .ifPresent(k -> predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_DEPARTMENT)
            .ifPresent(k -> predicates.add(new DepartmentContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_OFFICE)
            .ifPresent(k -> predicates.add(new OfficeContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_REMARK)
            .ifPresent(k -> predicates.add(new RemarkContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_TAG)
            .ifPresent(k -> predicates.add(new TagContainsKeywordsPredicate(Arrays.asList(k))));

        return new FindCommand(predicates);
    }

    /**
     * Returns true if one of the prefixes has an {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Throws a {@code ParseException} if there is a blank keyword.
     */
    private void checkKeywords(String[] keywords) throws ParseException {
        if (Stream.of(keywords).anyMatch(String::isBlank)) {
            throw new ParseException(MESSAGE_EMPTY_KEYWORD);
        }
    }

    /**
     * Throws a {@code ParseException} if there is a duplicate prefix.
     */
    private void checkDuplicatePrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes)
        throws ParseException {
        for (Prefix p : prefixes) {
            if (argumentMultimap.getAllValues(p).size() > 1) {
                throw new ParseException(String.format(MESSAGE_DUPLICATE_PREFIX, p));
            }
        }
    }

    /**
     * Returns keywords stored as the value of {@code prefix}.
     */
    private Optional<String[]> getKeywords(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        if (argMultimap.getValue(prefix).isEmpty()) {
            return Optional.empty();
        }

        String[] keywords;
        keywords = argMultimap.getValue(prefix).get().split("\\s+");
        checkKeywords(keywords);
        return Optional.of(keywords);
    }
}
