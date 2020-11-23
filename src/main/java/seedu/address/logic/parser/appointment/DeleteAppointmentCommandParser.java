package seedu.address.logic.parser.appointment;

import seedu.address.logic.commands.appointment.DeleteAppointmentCommand;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentId;

/**
 * Parses input arguments and creates a new DeleteAppointmentCommand object
 */
public class DeleteAppointmentCommandParser extends DeleteCommandParser<DeleteAppointmentCommand> {


    @Override
    public DeleteAppointmentCommand getCommand(String args) throws ParseException {
        AppointmentId id = ParserUtil.parseAppointmentId(args);
        return new DeleteAppointmentCommand(id);
    }

    @Override
    public String getMessageUsage() {
        return DeleteAppointmentCommand.MESSAGE_USAGE;
    }
}
