package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.StartAttemptCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class StartAttemptCommandParser implements Parser<StartAttemptCommand> {

    @Override
    public StartAttemptCommand parse(String userInput) throws ParseException {

        if (userInput.trim().equals("attempt")) {
            return new StartAttemptCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StartAttemptCommand.MESSAGE_USAGE));
        }

    }

}
