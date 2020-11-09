package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.UNKNOWN_CLEAR_COMMAND;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ", 2);
        try {
            if (splitArgs[0].trim().equals("contact")) {
                return new ClearCommand();
            } else {
                throw new ParseException(UNKNOWN_CLEAR_COMMAND);
            }
        } catch (ParseException | ArrayIndexOutOfBoundsException pe) {
            throw new ParseException(UNKNOWN_CLEAR_COMMAND);
        }
    }
}
