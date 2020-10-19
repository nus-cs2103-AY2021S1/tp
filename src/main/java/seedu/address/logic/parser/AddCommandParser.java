package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INSUFFICENT_ARGUMENTS;

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
        String[] argList = trimArgs.split(" ");
        if (argList.length == 0 || argList.length > 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
        if (argList.length == 1 && argList[0].equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    String.format(MESSAGE_INSUFFICENT_ARGUMENTS, AddCommand.COMMAND_WORD, 1)));
        }
        Index index = ParserUtil.parseIndex(argList[0], "Menu Index");

        if (argList.length == 1) {
            return new AddCommand(index);
        }

        int quantity = ParserUtil.parseQuantity(argList[1]);
        return new AddCommand(index, quantity);

    }
}


