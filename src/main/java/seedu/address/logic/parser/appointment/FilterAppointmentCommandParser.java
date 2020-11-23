package seedu.address.logic.parser.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MULTIPLE_INPUT_FOR_SAME_PREFIX;
import static seedu.address.logic.commands.appointment.FilterAppointmentCommand.MESSAGE_NOT_STATED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_OF_APPT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HAIRDRESSER_ID;

import java.util.Optional;

import seedu.address.logic.commands.appointment.FilterAppointmentCommand;
import seedu.address.logic.commands.appointment.FilterAppointmentCommand.FilterAppointmentDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDate;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.person.client.ClientId;
import seedu.address.model.person.hairdresser.HairdresserId;

/**
 * Parses input arguments and creates a new FilterAppointmentCommand object
 */
public class FilterAppointmentCommandParser implements Parser<FilterAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterAppointmentCommand
     * and returns an FilterAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterAppointmentCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLIENT_ID, PREFIX_HAIRDRESSER_ID,
                        PREFIX_DATE_OF_APPT, PREFIX_APPT_STATUS);

        FilterAppointmentDescriptor descriptors =
                new FilterAppointmentDescriptor();

        if (argMultimap.getNum(PREFIX_CLIENT_ID) > 1) {
            throw new ParseException(String.format(MULTIPLE_INPUT_FOR_SAME_PREFIX));
        }

        if (argMultimap.getNum(PREFIX_HAIRDRESSER_ID) > 1) {
            throw new ParseException(String.format(MULTIPLE_INPUT_FOR_SAME_PREFIX));
        }

        if (argMultimap.getNum(PREFIX_DATE_OF_APPT) > 1) {
            throw new ParseException(String.format(MULTIPLE_INPUT_FOR_SAME_PREFIX));
        }

        if (argMultimap.getNum(PREFIX_APPT_STATUS) > 1) {
            throw new ParseException(String.format(MULTIPLE_INPUT_FOR_SAME_PREFIX));
        }

        if (argMultimap.getValue(PREFIX_CLIENT_ID).isPresent()) {
            ClientId pid = ParserUtil
                    .parseClientId(argMultimap.getValue(PREFIX_CLIENT_ID).get());
            descriptors.setClientId(Optional.of(pid));
        }
        if (argMultimap.getValue(PREFIX_HAIRDRESSER_ID).isPresent()) {
            HairdresserId did = ParserUtil
                    .parseHairdresserId(argMultimap.getValue(PREFIX_HAIRDRESSER_ID).get());
            descriptors.setHairdresserId(Optional.of(did));
        }
        if (argMultimap.getValue(PREFIX_DATE_OF_APPT).isPresent()) {
            AppointmentDate date = ParserUtil
                    .parseAppointmentDate(argMultimap.getValue(PREFIX_DATE_OF_APPT).get());
            descriptors.setDate(Optional.of(date));
        }
        if (argMultimap.getValue(PREFIX_APPT_STATUS).isPresent()) {
            AppointmentStatus status = ParserUtil
                    .parseAppointmentStatus(argMultimap.getValue(PREFIX_APPT_STATUS).get());
            descriptors.setStatus(Optional.of(status));
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterAppointmentCommand.MESSAGE_USAGE));
        }

        if (descriptors.noFieldStated()) {
            throw new ParseException(String.format(MESSAGE_NOT_STATED,
                    FilterAppointmentCommand.MESSAGE_USAGE));
        }

        return new FilterAppointmentCommand(descriptors);
    }
}
