package seedu.homerce.logic.parser.appointment;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.homerce.logic.parser.CliSyntax.PREFIX_SERVICE_SERVICE_CODE;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.homerce.logic.commands.appointment.FindAppointmentCommand;
import seedu.homerce.logic.parser.ArgumentMultimap;
import seedu.homerce.logic.parser.ArgumentTokenizer;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.Prefix;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.appointment.predicate.AppointmentDatePredicate;
import seedu.homerce.model.appointment.predicate.AppointmentNamePredicate;
import seedu.homerce.model.appointment.predicate.AppointmentPhonePredicate;
import seedu.homerce.model.appointment.predicate.AppointmentServiceCodePredicate;

public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {
    private static final String MULTIPLE_PARAMETERS = "Please only input one parameter.";
    private static final int NUM_ALLOWED_PARAMETERS = 1;

    @Override
    public FindAppointmentCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_DATE, PREFIX_PHONE, PREFIX_SERVICE_SERVICE_CODE, PREFIX_NAME);
        checkValidPrefixes(userInput);
        Predicate<Appointment> predicate = createPredicate(argMultimap);
        return new FindAppointmentCommand(predicate);
    }

    private void checkValidPrefixes(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
            args, PREFIX_DATE, PREFIX_PHONE, PREFIX_SERVICE_SERVICE_CODE, PREFIX_NAME
        );
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }
        if (areMultipleParametersPresent(argMultimap, PREFIX_DATE, PREFIX_PHONE, PREFIX_SERVICE_SERVICE_CODE,
            PREFIX_NAME)) {
            throw new ParseException(MULTIPLE_PARAMETERS);
        }
        if ((!isPrefixPresent(argMultimap, PREFIX_DATE)
            && !isPrefixPresent(argMultimap, PREFIX_PHONE)
            && !isPrefixPresent(argMultimap, PREFIX_NAME)
            && !isPrefixPresent(argMultimap, PREFIX_SERVICE_SERVICE_CODE))
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindAppointmentCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Returns true if there is more than one input parameter.
     */
    private boolean areMultipleParametersPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).count()
            > NUM_ALLOWED_PARAMETERS;
    }

    /**
     * Returns true if the prefix is present in the user's command.
     */
    private boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }

    private Predicate<Appointment> createPredicate(ArgumentMultimap argMultimap) throws ParseException {
        Predicate<Appointment> predicate = null;
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            predicate = new AppointmentDatePredicate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            predicate = new AppointmentPhonePredicate(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        } else if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            if (!argMultimap.getValue(PREFIX_NAME).get().trim().isEmpty()) { // Check for empty user input
                String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().trim().split("\\s+");
                predicate = new AppointmentNamePredicate(Arrays.asList(nameKeywords));
            } else {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindAppointmentCommand.MESSAGE_USAGE)
                );
            }
        } else if (argMultimap.getValue(PREFIX_SERVICE_SERVICE_CODE).isPresent()) {
            predicate = new AppointmentServiceCodePredicate(ParserUtil
                .parseServiceCode(argMultimap.getValue(PREFIX_SERVICE_SERVICE_CODE).get()));
        }
        return predicate;
    }
}
