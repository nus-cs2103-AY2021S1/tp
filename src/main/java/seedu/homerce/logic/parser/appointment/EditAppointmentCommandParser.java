package seedu.homerce.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_SERVICE_CODE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_TIME_OF_DAY;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.appointment.EditAppointmentCommand;
import seedu.homerce.logic.parser.ArgumentMultimap;
import seedu.homerce.logic.parser.ArgumentTokenizer;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of
     * the EditAppointmentCommand and returns an EditAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_TIME_OF_DAY,
            PREFIX_PHONE, PREFIX_SERVICE_SERVICE_CODE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentCommand.EditAppointmentDescriptor editAppointmentDescriptor =
            new EditAppointmentCommand.EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editAppointmentDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME_OF_DAY).isPresent()) {
            editAppointmentDescriptor.setTimeOfDay(ParserUtil.parseTime(
                argMultimap.getValue(PREFIX_TIME_OF_DAY).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editAppointmentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_SERVICE_SERVICE_CODE).isPresent()) {
            editAppointmentDescriptor.setServiceCode(ParserUtil.parseServiceCode(
                argMultimap.getValue(PREFIX_SERVICE_SERVICE_CODE).get()));
        }
        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }
}
