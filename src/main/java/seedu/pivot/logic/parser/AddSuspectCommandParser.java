package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.suspectcommands.AddSuspectCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Name;
import seedu.pivot.model.investigationcase.Suspect;

public class AddSuspectCommandParser implements Parser<AddSuspectCommand> {
    @Override
    public AddSuspectCommand parse(String args) throws ParseException {
        assert(StateManager.atCasePage()) : "Program should be at case page";

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSuspectCommand.MESSAGE_USAGE));
        }

        Index index = StateManager.getState();
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Suspect suspect = new Suspect(name);
        return new AddSuspectCommand(index, suspect);
    }
}
