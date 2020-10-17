package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        if (!areAnyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DEPARTMENT, PREFIX_OFFICE,
                PREFIX_REMARK, PREFIX_TAG)) {

            String trimmedArgs = args.trim();
            if (trimmedArgs.isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            String[] nameKeywords = trimmedArgs.split("\\s+");

            return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        }

        String[] keywords;
        List<Predicate<Person>> predicates = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            predicates.add(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (argMultimap.getValue(PREFIX_DEPARTMENT).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_DEPARTMENT).get().split("\\s+");
            predicates.add(new DepartmentContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (argMultimap.getValue(PREFIX_OFFICE).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_OFFICE).get().split("\\s+");
            predicates.add(new OfficeContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_REMARK).get().split("\\s+");
            predicates.add(new RemarkContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            keywords = argMultimap.getValue(PREFIX_TAG).get().split("\\s+");
            predicates.add(new TagContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (predicates.size() == 1) {
            return new FindCommand(predicates.get(0));
        }

        Predicate<Person> composedPredicate = person -> true;

        for (Predicate<Person> p: predicates) {
            composedPredicate = composedPredicate.and(p);
        }

        return new FindCommand(composedPredicate);
    }

    /**
     * Returns true if one of the prefixes has an {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAnyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
