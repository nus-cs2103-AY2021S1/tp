package seedu.address.logic.parser;

import seedu.address.logic.commands.RatesCommand;

/**
 * Parses input arguments and creates a new RatesCommand object
 */
public class RatesCommandParser implements Parser<RatesCommand> {

    /**
     * @return a List of Command Instruction for users.
     * */
    @Override
    public RatesCommand parse(String userInput) {
        return new RatesCommand();
    }

}
