package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearEventAttendeesCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ClearEventAttendees object
 */

public class ClearEventAttendeesCommandParser implements Parser<ClearEventAttendeesCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearEventAttendeesCommand
     * and returns a ClearEventAttendeesCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearEventAttendeesCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ClearEventAttendeesCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearEventAttendeesCommand.MESSAGE_USAGE), pe);
        }
    }
}
