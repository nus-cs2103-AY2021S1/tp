package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.person.CliSyntax.PREFIX_ROLE;

import java.util.stream.Stream;

import seedu.address.logic.commands.person.AddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ParserCommon;
import seedu.address.model.person.person.Email;
import seedu.address.model.person.person.Name;
import seedu.address.model.person.person.Person;
import seedu.address.model.person.person.Phone;
import seedu.address.model.person.person.Role;
import seedu.address.model.util.Description;

/**
 * Parses input arguments and creates a new AddCommand object for person
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ROLE, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_ROLE, PREFIX_NAME, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Role role = ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Description description = ParserCommon.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION));

        Person person = new Person(name, phone, email, role, description);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
