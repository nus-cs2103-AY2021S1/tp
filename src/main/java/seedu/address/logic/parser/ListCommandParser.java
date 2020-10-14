package seedu.address.logic.parser;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParser {
    /**
     * Returns a ListCommand object for execution.
     */
    public ListCommand parse(String args) {
        return new ListCommand();
    }
}
