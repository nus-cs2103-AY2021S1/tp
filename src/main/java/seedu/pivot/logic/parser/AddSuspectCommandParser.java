package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.suspectcommands.AddSuspectCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.caseperson.Address;
import seedu.pivot.model.investigationcase.caseperson.Email;
import seedu.pivot.model.investigationcase.caseperson.Gender;
import seedu.pivot.model.investigationcase.caseperson.Name;
import seedu.pivot.model.investigationcase.caseperson.Phone;
import seedu.pivot.model.investigationcase.caseperson.Suspect;

public class AddSuspectCommandParser implements Parser<AddSuspectCommand> {
    @Override
    public AddSuspectCommand parse(String args) throws ParseException {
        assert(StateManager.atCasePage()) : "Program should be at case page";

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GENDER,
                        PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_GENDER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSuspectCommand.MESSAGE_USAGE));
        }

        Index index = StateManager.getState();
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElse(""));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).orElse(""));
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).orElse(""));

        Suspect suspect = new Suspect(name, gender, phone, email, address);
        return new AddSuspectCommand(index, suspect);
    }
}
