package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddCommand parse(String args) throws ParseException {
        String trimArgs = args.trim();
        String finalArgs = trimArgs.replaceAll("( )+", " ");
        String[] argsArr = finalArgs.split(" ");
        ParserUtil.checkArgsLength(argsArr, AddCommand.COMMAND_WORD, AddCommand.MESSAGE_USAGE, 1, 2);
        Index index = ParserUtil.parseIndex(argsArr[0], "Menu Index");

        if (argsArr.length == 1) {
            return new AddCommand(index);
        }

        int quantity = ParserUtil.parseQuantity(argsArr[1]);
        return new AddCommand(index, quantity);
    }
}


