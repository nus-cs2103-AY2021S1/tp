package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.casecommands.AddDescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.state.StateManager;
import seedu.address.model.investigationcase.Description;

public class AddDescriptionCommandParser implements Parser<AddDescriptionCommand> {
    @Override
    public AddDescriptionCommand parse(String args) throws ParseException {
        assert(StateManager.atCasePage()) : "Program should be at case page";

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESC);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDescriptionCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap
                        .getValue(PREFIX_DESC).orElse(""));
        Index index = StateManager.getState();
        return new AddDescriptionCommand(index, description);
    }
}
