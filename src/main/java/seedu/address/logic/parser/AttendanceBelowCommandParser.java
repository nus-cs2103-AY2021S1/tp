package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.AttendanceBelowCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.AttendanceBelowSpecifiedScorePredicate;

/**
 * Parses input arguments and creates a new AttendanceBelowCommand object.
 */
public class AttendanceBelowCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AttendanceBelowCommand
     * and returns a AttendanceBelowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendanceBelowCommand parse(String args) throws ParseException {
        final int upperBound;

        try {
            upperBound = ParserUtil.parseUpperBound(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendanceBelowCommand.MESSAGE_USAGE), pe);
        }

        return new AttendanceBelowCommand(new AttendanceBelowSpecifiedScorePredicate(upperBound), upperBound);
    }
}
