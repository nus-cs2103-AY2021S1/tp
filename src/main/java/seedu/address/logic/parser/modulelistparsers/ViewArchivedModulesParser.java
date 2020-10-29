package seedu.address.logic.parser.modulelistparsers;

import seedu.address.logic.commands.modulelistcommands.ViewArchivedModulesCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewArchivedModulesParser implements Parser<ViewArchivedModulesCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewArchivedModulesCommand parse(String args) throws ParseException {
        return new ViewArchivedModulesCommand();
    }
}
