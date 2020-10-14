package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddSuspectCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.investigationcase.Name;
import seedu.address.model.investigationcase.Suspect;

public class AddSuspectCommandParser implements Parser<AddSuspectCommand> {
    @Override
    public AddSuspectCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSuspectCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Suspect suspect = new Suspect(name);
        return new AddSuspectCommand(suspect);
    }
}
