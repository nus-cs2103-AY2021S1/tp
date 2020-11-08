package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListAttemptsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListAttemptsCommandParser implements Parser<ListAttemptsCommand> {
    @Override
    public ListAttemptsCommand parse(String userInput) throws ParseException {
        if (userInput.trim().equals("")) {
            return new ListAttemptsCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListAttemptsCommand.MESSAGE_USAGE));
        }
    }
}
