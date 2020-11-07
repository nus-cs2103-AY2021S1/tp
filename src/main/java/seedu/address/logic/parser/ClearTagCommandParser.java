//@@author jerrylchong
package seedu.address.logic.parser;

import seedu.address.logic.commands.ClearTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

public class ClearTagCommandParser implements Parser<ClearTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ClearTagCommand
     * and returns a ClearTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearTagCommand parse(String args) throws ParseException {
        Name targetName = ParserUtil.parseName(args);
        return new ClearTagCommand(targetName);
    }
}
