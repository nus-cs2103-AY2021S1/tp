package seedu.address.logic.parser;

import seedu.address.logic.commands.CurrenciesCommand;

/**
 * Parses input arguments and creates a new RatesCommand object
 */
public class CurrenciesCommandParser implements Parser<CurrenciesCommand> {

    /**
     * @return a List of Command Instruction for users.
     * */
    @Override
    public CurrenciesCommand parse(String userInput) {
        return new CurrenciesCommand();
    }

}
