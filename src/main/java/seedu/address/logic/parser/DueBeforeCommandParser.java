package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.due.DueBeforeCommand;
import seedu.address.logic.commands.due.DueByCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.DueBeforePredicate;

/**
 * Parses input arguments and creates a new DueBeforeCommand object
 */
public class DueBeforeCommandParser implements Parser<DueBeforeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DueBeforeCommand
     * and returns a DueBeforeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DueBeforeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] splitArgs = args.trim().split(" ", 1);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + splitArgs[0], PREFIX_DATE,
                PREFIX_TIME);

        String date = argMultimap.getValue(PREFIX_DATE).orElseThrow(() -> new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueByCommand.MESSAGE_USAGE)));
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate checkDate = LocalDate.parse(date, dateFormat);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }

        String time = argMultimap.getValue(PREFIX_TIME).orElseThrow(() -> new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueByCommand.MESSAGE_USAGE)));
        try {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            LocalTime checkTime = LocalTime.parse(time, timeFormat);
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.MESSAGE_INVALID_TIME_FORMAT);
        }

        String deadline = date + " " + time;
        return new DueBeforeCommand(new DueBeforePredicate(deadline));
    }

}
