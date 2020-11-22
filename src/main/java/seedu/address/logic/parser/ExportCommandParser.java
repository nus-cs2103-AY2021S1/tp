package seedu.address.logic.parser;

import seedu.address.logic.commands.ExportCommand;


public class ExportCommandParser implements Parser<ExportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExportCommand
     * and returns an ExportCommand object for execution.
     */
    public ExportCommand parse(String args) {
        String type = args.strip().toLowerCase();

        return new ExportCommand(type);
    }
}
