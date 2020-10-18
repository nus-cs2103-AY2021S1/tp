package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RemoveCommand object
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
                throw new ParseException(String.format(ParserUtil.MESSAGE_INVALID_COMMAND_FORMAT,
                        RemoveCommand.MESSAGE_USAGE));
            }
            Index index = ParserUtil.parseIndex(argList[0]);
            if (argList.length == 1) {
                return new RemoveCommand(index);
            } else {
                int quantity = ParserUtil.parseQuantity(argList[1]);
                return new RemoveCommand(index, quantity);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(ParserUtil.MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), pe);
        }
    }
}
