package seedu.address.logic.parser;

import seedu.address.logic.commands.ClearLabelCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ClearLabelCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ClearLabelCommand
     * and returns a ClearLabelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearLabelCommand parse(String args) throws ParseException {
        try {
            Name targetName = ParserUtil.parseName(args);
            return new ClearLabelCommand(targetName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearLabelCommand.MESSAGE_USAGE), pe);
        }
    }
}
