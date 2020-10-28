package seedu.address.logic.parser.schedulerparsers;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.DeleteModuleCommand;
import seedu.address.logic.commands.schedulercommands.AddEventCommand;
import seedu.address.logic.commands.schedulercommands.DeleteEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteEventParser implements Parser<DeleteEventCommand> {
    @Override
    public DeleteEventCommand parse(String userInput) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(userInput);
            return new DeleteEventCommand(index);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE), e);
        }

    }
}
