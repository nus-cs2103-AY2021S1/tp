package seedu.homerce.logic.parser.appointment;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_SERVICE_CODE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_TIME_OF_DAY;

import java.util.stream.Stream;

import seedu.homerce.logic.commands.appointment.AddAppointmentCommand;
import seedu.homerce.logic.parser.ArgumentMultimap;
import seedu.homerce.logic.parser.ArgumentTokenizer;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.Prefix;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.appointment.AppointmentTemp;
import seedu.homerce.model.appointment.TimeOfDay;
import seedu.homerce.model.client.Phone;
import seedu.homerce.model.service.ServiceCode;
import seedu.homerce.model.util.attributes.Date;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {
    @Override
    public AddAppointmentCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                    userInput, PREFIX_DATE, PREFIX_PHONE,
                    PREFIX_TIME_OF_DAY, PREFIX_SERVICE_SERVICE_CODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_PHONE, PREFIX_TIME_OF_DAY, PREFIX_SERVICE_SERVICE_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        TimeOfDay time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME_OF_DAY).get());

        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        ServiceCode serviceCode = ParserUtil.parseServiceCode(argMultimap.getValue(PREFIX_SERVICE_SERVICE_CODE).get());

        AppointmentTemp appointmentTemp = new AppointmentTemp(date, time, phone, serviceCode);

        return new AddAppointmentCommand(appointmentTemp);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
