package seedu.address.logic.parser;

import seedu.address.logic.commands.ExitCommand;

/**
 * Parses input arguments and creates a new ExitCommand object
 */
public class ExitCommandParser implements Parser<ExitCommand> {

    /**
     * @return a List of Command Instruction for users.
     * */
    @Override
    public ExitCommand parse(String userInput) {
        return new ExitCommand();
    }

}
