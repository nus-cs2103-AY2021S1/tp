package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.stock.logic.commands.TabCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class TabCommandParser implements Parser<TabCommand> {

    @Override
    public TabCommand parse(String userInput) throws ParseException {
        if (userInput.trim().length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE));
        }
        return new TabCommand();
    }
}
