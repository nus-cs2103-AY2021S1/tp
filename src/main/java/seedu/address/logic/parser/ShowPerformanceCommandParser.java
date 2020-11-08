package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ShowPerformanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ShowPerformanceCommandParser implements Parser<ShowPerformanceCommand> {
    @Override
    public ShowPerformanceCommand parse(String userInput) throws ParseException {
        if (userInput.trim().equals("")) {
            return new ShowPerformanceCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ShowPerformanceCommand.MESSAGE_USAGE));
        }
    }
}
