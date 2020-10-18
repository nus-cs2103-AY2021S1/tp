package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class RemoveCommandParser implements Parser<RemoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns a RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
        try {
            String trimArgs = args.trim();
            String[] argList = trimArgs.split(" ");
            if (argList.length == 0 || argList.length > 2) {
                throw new ParseException("");
            }
            Index index = ParserUtil.parseIndex(argList[0]);
            if (argList.length == 1) {
                return new RemoveCommand(index);
            } else {
                int quantity = Integer.valueOf(argList[1]);
                return new RemoveCommand(index, quantity);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), pe);
        }
    }

}
