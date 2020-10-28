package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     */
    public HelpCommand parse(String args) {
        return new HelpCommand();
    }
}
