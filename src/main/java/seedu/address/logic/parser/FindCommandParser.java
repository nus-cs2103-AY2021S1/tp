package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NAME_KEYWORD;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PHONE_KEYWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
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
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.OfficeContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.model.person.predicates.RemarkContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Name keywords should be alphanumeric and the first character should not be a whitespace.
     */
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alnum}]+";

    /**
     * Phone keywords should be numeric and the first character should not be a whitespace.
     */
    public static final String PHONE_VALIDATION_REGEX = "\\p{Digit}+";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_DEPARTMENT, PREFIX_OFFICE, PREFIX_REMARK, PREFIX_TAG);

        List<Predicate<Person>> predicates = new ArrayList<>();

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
            PREFIX_DEPARTMENT, PREFIX_OFFICE, PREFIX_REMARK, PREFIX_TAG)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        checkDuplicatePrefix(argMultimap, PREFIX_NAME, PREFIX_DEPARTMENT, PREFIX_OFFICE,
            PREFIX_REMARK, PREFIX_TAG);

        getKeywords(argMultimap, PREFIX_NAME)
            .ifPresent(k -> predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_PHONE)
            .ifPresent(k -> predicates.add(new PhoneContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_EMAIL)
            .ifPresent(k -> predicates.add(new EmailContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_DEPARTMENT)
            .ifPresent(k -> predicates.add(new DepartmentContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_OFFICE)
            .ifPresent(k -> predicates.add(new OfficeContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_REMARK)
            .ifPresent(k -> predicates.add(new RemarkContainsKeywordsPredicate(Arrays.asList(k))));
        getKeywords(argMultimap, PREFIX_TAG)
            .ifPresent(k -> predicates.add(new TagContainsKeywordsPredicate(Arrays.asList(k))));

        assert (!predicates.isEmpty());

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
     * Throws a {@code ParseException} if keyword does not match valid attribute format.
     */
    private void checkKeywords(String[] keywords, Prefix prefix) throws ParseException {
        if (prefix.equals(PREFIX_PHONE)
            && Stream.of(keywords).anyMatch(k -> !k.matches(PHONE_VALIDATION_REGEX))) {
            throw new ParseException(MESSAGE_INVALID_PHONE_KEYWORD);
        }

        if (prefix.equals(PREFIX_NAME)
            && Stream.of(keywords).anyMatch(k -> !k.matches(NAME_VALIDATION_REGEX))) {
            throw new ParseException(MESSAGE_INVALID_NAME_KEYWORD);
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
        keywords = ParserUtil.parseString(argMultimap.getValue(prefix).get()).split("\\s+");
        checkKeywords(keywords, prefix);
        return Optional.of(keywords);
    }

}
