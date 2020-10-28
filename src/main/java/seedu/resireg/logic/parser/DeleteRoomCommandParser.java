package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.commands.DeleteRoomCommand;
import seedu.resireg.logic.parser.exceptions.ParseException;

public class DeleteRoomCommandParser implements Parser<DeleteRoomCommand> {
    @Override
    public DeleteRoomCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteRoomCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteRoomCommand.HELP.getFullMessage()), pe);
        }
    }
}
