package seedu.address.logic.parser;

import seedu.address.logic.commands.ExitCommand;

public class ExitCommandParser {
    /**
     * Returns a ExitCommand object for execution.
     */
    public ExitCommand parse(String args) {
        return new ExitCommand();
    }
}
