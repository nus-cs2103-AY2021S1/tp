package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EnterQuizCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class EnterQuizCommandParser implements Parser<EnterQuizCommand> {

    @Override
    public EnterQuizCommand parse(String userInput) throws ParseException {
        if (userInput.trim().equals("quiz")) {
            return new EnterQuizCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnterQuizCommand.MESSAGE_USAGE));
        }
    }
}
