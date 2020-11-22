package seedu.address.logic.parser;

import seedu.address.logic.commands.ClearCommand;

/**
 * Parses input arguments and creates a new ClearCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * @return a List of Command Instruction for users.
     * */
    @Override
    public ClearCommand parse(String userInput) {
        return new ClearCommand();
    }

}
