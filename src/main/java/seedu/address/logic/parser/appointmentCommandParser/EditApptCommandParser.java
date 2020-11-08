package seedu.address.logic.parser.appointmentCommandParser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_NEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_OLD;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.appointmentcommand.EditApptCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Appointment;
import seedu.address.model.patient.Nric;

/**
 * Parses input arguments and creates a new EditApptCommand object
 */
public class EditApptCommandParser implements Parser<EditApptCommand> {

    private final Logger logger = LogsCenter.getLogger(MainApp.class);

    /**
     * Parses the given {@code String} of arguments in the context of the EditApptCommand
     * and returns an EditApptCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditApptCommand parse(String args) throws ParseException {
        logger.info("----------------[STRING TO PARSE][" + args + "]");
        logger.log(Level.INFO, "Start parsing");
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APPOINTMENT_OLD, PREFIX_APPOINTMENT_NEW);

        Nric nric;

        try {
            nric = ParserUtil.parseNric(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditApptCommand.MESSAGE_USAGE), pe);
        }

        Appointment oldAppointment = new Appointment();

        if (argMultimap.getValue(PREFIX_APPOINTMENT_OLD).isPresent()) {
            oldAppointment = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_APPOINTMENT_OLD).get(), false);
        }
        assert !oldAppointment.equals(new Appointment()) : "Appointment should not be empty!";

        Appointment newAppointment = new Appointment();

        if (argMultimap.getValue(PREFIX_APPOINTMENT_NEW).isPresent()) {
            newAppointment = ParserUtil.parseAppointment(argMultimap.getValue(PREFIX_APPOINTMENT_NEW).get(), false);
        }

        return new EditApptCommand(nric, oldAppointment, newAppointment);
    }

}
