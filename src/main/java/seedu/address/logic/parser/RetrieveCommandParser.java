package seedu.address.logic.parser;

import seedu.address.logic.commands.RetrieveCommand;

public class RetrieveCommandParser implements Parser<RetrieveCommand> {
    /**
     * Returns a RetrieveCommand object for execution.
     */
    public RetrieveCommand parse(String args) {
        return new RetrieveCommand();
    }
}
