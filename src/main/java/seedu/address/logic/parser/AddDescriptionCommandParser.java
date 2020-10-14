package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.casecommands.AddDescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.state.StateManager;

public class AddDescriptionCommandParser implements Parser<AddDescriptionCommand> {
    @Override
    public AddDescriptionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddDescriptionCommand.MESSAGE_USAGE));
        }

        // Assume that State is already validated from the main AddCommandParser
        Index index = StateManager.getState();
        EditCommand.EditCaseDescriptor editCaseDescriptor = new EditCommand.EditCaseDescriptor();
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editCaseDescriptor.setDescription(ParserUtil.parseDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }

        if (!editCaseDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AddDescriptionCommand.MESSAGE_DESCRIPTION_NOT_EDITED);
        }

        return new AddDescriptionCommand(index, editCaseDescriptor);
    }
}
