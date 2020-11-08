package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.EndAttemptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EndAttemptCommandParser implements Parser<EndAttemptCommand> {
    @Override
    public EndAttemptCommand parse(String userInput) throws ParseException {

        if (userInput.trim().equals("attempt")) {
            return new EndAttemptCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EndAttemptCommand.MESSAGE_USAGE));
        }

    }
}
