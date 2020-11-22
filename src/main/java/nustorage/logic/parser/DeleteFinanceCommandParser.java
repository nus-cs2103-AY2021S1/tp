package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import nustorage.commons.core.index.Index;
import nustorage.logic.commands.DeleteFinanceCommand;
import nustorage.logic.parser.exceptions.ParseException;

public class DeleteFinanceCommandParser implements Parser<DeleteFinanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteFinanceCommand
     * and returns a DeleteFinanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteFinanceCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteFinanceCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteFinanceCommand.MESSAGE_USAGE), pe);
        }
    }
}
