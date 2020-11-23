package seedu.address.logic.parser.hairdresser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALISATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;

import seedu.address.logic.commands.hairdresser.AddHairdresserCommand;
import seedu.address.logic.parser.AddCommandParser;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.hairdresser.Hairdresser;
import seedu.address.model.person.hairdresser.Title;
import seedu.address.model.specialisation.Specialisation;

public class AddHairdresserCommandParser extends AddCommandParser<AddHairdresserCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddHairdresserCommand
     * and returns an AddHairdresserCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddHairdresserCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_GENDER, PREFIX_TITLE, PREFIX_SPECIALISATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_GENDER, PREFIX_TITLE) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddHairdresserCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Set<Specialisation> specList = ParserUtil.parseSpecialisations(argMultimap.getAllValues(PREFIX_SPECIALISATION));

        Hairdresser hairdresser = new Hairdresser(name, phone, email, gender, title, specList);

        return new AddHairdresserCommand(hairdresser);
    }

}
