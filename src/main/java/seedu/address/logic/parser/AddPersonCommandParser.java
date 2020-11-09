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

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.Address;
import seedu.address.model.information.BlacklistStatus;
import seedu.address.model.information.Date;
import seedu.address.model.information.Email;
import seedu.address.model.information.Experience;
import seedu.address.model.information.Name;
import seedu.address.model.information.Person;
import seedu.address.model.information.Phone;
import seedu.address.model.information.Salary;
import seedu.address.model.information.UrlLink;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddPersonCommand object
 */
public class AddPersonCommandParser implements Parser<AddPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonCommand
     * and returns an AddPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_DATE_OF_APPLICATION, PREFIX_BLACKLIST, PREFIX_ADDRESS,
                        PREFIX_EXPERIENCE, PREFIX_URL_LINK, PREFIX_SALARY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_EXPERIENCE, PREFIX_DATE_OF_APPLICATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Experience experience = ParserUtil.parseExperience(argMultimap.getValue(PREFIX_EXPERIENCE).get());
        Date dateOfApplication = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_OF_APPLICATION).get());
        BlacklistStatus blacklistStatus = arePrefixesPresent(argMultimap, PREFIX_BLACKLIST)
                ? ParserUtil.parseBlacklistStatus(argMultimap.getValue(PREFIX_BLACKLIST).get())
                : new BlacklistStatus("false");
        Optional<Address> addressOptional = arePrefixesPresent(argMultimap, PREFIX_ADDRESS)
                ? Optional.of(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()))
                : Optional.empty();
        Optional<UrlLink> urlLinkOptional = arePrefixesPresent(argMultimap, PREFIX_URL_LINK)
                ? Optional.of(ParserUtil.parseUrlLink(argMultimap.getValue(PREFIX_URL_LINK).get()))
                : Optional.empty();
        Optional<Salary> salaryOptional = arePrefixesPresent(argMultimap, PREFIX_SALARY)
                ? Optional.of(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()))
                : Optional.empty();
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, experience, dateOfApplication,
                blacklistStatus, addressOptional, urlLinkOptional, salaryOptional, tagList);

        return new AddPersonCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
