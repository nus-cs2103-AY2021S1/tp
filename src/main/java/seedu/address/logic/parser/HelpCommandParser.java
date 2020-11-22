package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * @return a List of Command Instruction for users.
     * */
    @Override
    public HelpCommand parse(String userInput) {
        return new HelpCommand();
    }

}
