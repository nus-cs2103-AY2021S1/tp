package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import nustorage.commons.core.index.Index;
import nustorage.logic.commands.DeleteInventoryRecordCommand;
import nustorage.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteInventoryRecordCommand object
 */
public class DeleteInventoryRecordCommandParser implements Parser<DeleteInventoryRecordCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInventoryRecordCommand
     * and returns a DeleteInventoryRecordCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteInventoryRecordCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteInventoryRecordCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInventoryRecordCommand.MESSAGE_USAGE), pe);
        }
    }
}
