package seedu.homerce.logic.parser.appointment;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.appointment.DoneAppointmentCommand;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAppointmentCommand object
 */
public class DoneAppointmentCommandParser implements Parser<DoneAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DoneAppointmentCommand
     * and returns a DoneAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DoneAppointmentCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DoneAppointmentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DoneAppointmentCommand.MESSAGE_USAGE), pe);
        }
    }
}
