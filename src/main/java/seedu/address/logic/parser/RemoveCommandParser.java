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
        String cleanedArgs = args.replaceAll("( )+", " ");
        String trimArgs = cleanedArgs.trim();
        String[] argsArr = trimArgs.split(" ");
        ParserUtil.checkArgsLength(argsArr, RemoveCommand.COMMAND_WORD, RemoveCommand.MESSAGE_USAGE, 1, 2);
        Index index = ParserUtil.parseIndex(argsArr[0], "Menu Index");

        if (argsArr.length == 1) {
            return new RemoveCommand(index);
        }

        int quantity = ParserUtil.parseQuantity(argsArr[1]);
        return new RemoveCommand(index, quantity);
    }
}
