package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLACKLIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPLICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPERIENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL_LINK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.Person;
import seedu.address.model.information.predicate.PersonAddressContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonApplicationContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonBlacklistContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonEmailContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonExperienceContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonNameContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonPhoneContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonSalaryContainsKeywordsPredicate;
import seedu.address.model.information.predicate.PersonTagsContainKeywordsPredicate;
import seedu.address.model.information.predicate.PersonUrlLinkContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPersonCommand object
 */
public class FindPersonCommandParser implements Parser<FindPersonCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindPersonCommand
     * and returns a FindPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindPersonCommand parse(String args) throws ParseException {

        List<Predicate<Person>> predicates = new ArrayList<>();

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_EXPERIENCE, PREFIX_DATE_OF_APPLICATION, PREFIX_SALARY, PREFIX_BLACKLIST,
                        PREFIX_URL_LINK, PREFIX_TAG);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            String name = argMultimap.getValue(PREFIX_NAME).orElse("");
            checkInput(name);
            List<String> words = splitInput(name);
            predicates.add(new PersonNameContainsKeywordsPredicate(words));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
            String phone = argMultimap.getValue(PREFIX_PHONE).orElse("");
            checkInput(phone);
            List<String> words = splitInput(phone);
            predicates.add(new PersonPhoneContainsKeywordsPredicate(words));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            String email = argMultimap.getValue(PREFIX_EMAIL).orElse("");
            checkInput(email);
            List<String> words = splitInput(email);
            predicates.add(new PersonEmailContainsKeywordsPredicate(words));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_ADDRESS)) {
            String address = argMultimap.getValue(PREFIX_ADDRESS).orElse("");
            checkInput(address);
            List<String> words = splitInput(address);
            predicates.add(new PersonAddressContainsKeywordsPredicate(words));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_EXPERIENCE)) {
            String experience = argMultimap.getValue(PREFIX_EXPERIENCE).orElse("");
            checkInput(experience);
            List<String> words = splitInput(experience);
            predicates.add(new PersonExperienceContainsKeywordsPredicate(words));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_DATE_OF_APPLICATION)) {
            String dateOfApplication = argMultimap.getValue(PREFIX_DATE_OF_APPLICATION).orElse("");
            checkInput(dateOfApplication);
            List<String> words = splitInput(dateOfApplication);
            predicates.add(new PersonApplicationContainsKeywordsPredicate(words));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_SALARY)) {
            String salary = argMultimap.getValue(PREFIX_SALARY).orElse("");
            checkInput(salary);
            List<String> words = splitInput(salary);
            predicates.add(new PersonSalaryContainsKeywordsPredicate(words));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_BLACKLIST)) {
            String blacklist = argMultimap.getValue(PREFIX_BLACKLIST).orElse("");
            checkInput(blacklist);
            List<String> words = splitInput(blacklist);
            predicates.add(new PersonBlacklistContainsKeywordsPredicate(words));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_URL_LINK)) {
            String url = argMultimap.getValue(PREFIX_URL_LINK).orElse("");
            checkInput(url);
            List<String> words = splitInput(url);
            predicates.add(new PersonUrlLinkContainsKeywordsPredicate(words));
        }
        if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            String tag = argMultimap.getValue(PREFIX_TAG).orElse("");
            checkInput(tag);
            List<String> words = splitInput(tag);
            predicates.add(new PersonTagsContainKeywordsPredicate(words));
        }

        return new FindPersonCommand(predicates);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Splits a String into words.
     * @param userInput User specified keyword for a field.
     * @return a List containing the words.
     */
    public List<String> splitInput(String userInput) {
        List<String> keywords = new ArrayList<>();
        String[] words = userInput.split("\\s+");
        Collections.addAll(keywords, words);
        return keywords;
    }

    /**
     * Checks if userInput is empty.
     * @param userInput User specified keyword for a field.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public void checkInput(String userInput) throws ParseException {
        if (userInput.length() == 0) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPersonCommand.MESSAGE_USAGE));
        }
    }

}
