package seedu.address.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_STATUS;

import seedu.address.logic.commands.appointment.EditAppointmentCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentId;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentCommand
     * and returns an EditAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_APPT_STATUS);

        AppointmentId appointmentId;
        try {
            appointmentId = ParserUtil.parseAppointmentId(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentCommand.ChangedAppointmentDescriptor changedAppointmentDescriptor =
                new EditAppointmentCommand.ChangedAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_APPT_STATUS).isPresent()) {
            changedAppointmentDescriptor.setStatus(ParserUtil.parseAppointmentStatus(argMultimap
                    .getValue(PREFIX_APPT_STATUS).get().toUpperCase()));
        }

        if (!changedAppointmentDescriptor.isStatusChanged()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_STATUS_MISSING);
        }

        return new EditAppointmentCommand(appointmentId, changedAppointmentDescriptor);
    }
}
