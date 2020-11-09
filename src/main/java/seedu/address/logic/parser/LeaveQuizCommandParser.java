package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.LeaveQuizCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class LeaveQuizCommandParser implements Parser<LeaveQuizCommand> {

    @Override
    public LeaveQuizCommand parse(String userInput) throws ParseException {
        if (userInput.trim().equals("quiz")) {
            return new LeaveQuizCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, LeaveQuizCommand.MESSAGE_USAGE));
        }
    }
}
