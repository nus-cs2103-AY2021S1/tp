package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimapUtil.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_PHONE;

import seedu.address.logic.commands.global.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUserName;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonName;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class AddPersonCommandParser implements Parser<AddPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonCommand
     * and returns a AddPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_PERSON_NAME, PREFIX_PERSON_GIT_USERNAME,
                PREFIX_PERSON_PHONE, PREFIX_PERSON_EMAIL, PREFIX_PERSON_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_PERSON_NAME, PREFIX_PERSON_GIT_USERNAME,
            PREFIX_PERSON_PHONE, PREFIX_PERSON_EMAIL, PREFIX_PERSON_ADDRESS)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand
                .MESSAGE_USAGE));
        }

        PersonName name = ParsePersonUtil.parsePersonName(argMultimap
            .getValue(PREFIX_PERSON_NAME).get());

        GitUserName gitUserName =
            ParsePersonUtil.parseGitUserName(argMultimap
                .getValue(PREFIX_PERSON_GIT_USERNAME).get());

        Phone phone = ParsePersonUtil.parsePhone(argMultimap
            .getValue(PREFIX_PERSON_PHONE).get());

        Email email = ParsePersonUtil.parseEmail(argMultimap
            .getValue(PREFIX_PERSON_EMAIL).get());

        Address address = ParsePersonUtil.parseAddress(argMultimap
            .getValue(PREFIX_PERSON_ADDRESS).get());

        Person teammate = new Person(name, gitUserName, phone, email, address);
        return new AddPersonCommand(teammate);
    }

}
