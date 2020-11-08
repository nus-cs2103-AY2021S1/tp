package seedu.pivot.logic.parser;

import static seedu.pivot.commons.core.DeveloperMessages.ASSERT_CASE_PAGE;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.pivot.logic.parser.AddCommandParser.arePrefixesPresent;
import static seedu.pivot.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.casecommands.EditStatusCommand;
import seedu.pivot.logic.parser.exceptions.ParseException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Status;

public class EditStatusCommandParser implements Parser<EditStatusCommand> {
    @Override
    public EditStatusCommand parse(String args) throws ParseException {
        assert(StateManager.atCasePage()) : ASSERT_CASE_PAGE;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_STATUS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStatusCommand.MESSAGE_USAGE));
        }

        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).orElse(""));
        Index index = StateManager.getState();

        return new EditStatusCommand(index, status);
    }
}
