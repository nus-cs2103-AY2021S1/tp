package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.task.Recurrence.DAY;
import static seedu.address.model.task.Recurrence.MONTH;
import static seedu.address.model.task.Recurrence.WEEK;
import static seedu.address.model.task.Recurrence.YEAR;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.add.AddContactCommand;
import seedu.address.logic.commands.add.AddEventCommand;
import seedu.address.logic.commands.add.AddTodoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Event;
import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Todo;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        String[] splitArgs = args.trim().split(" ", 2);
        if (splitArgs[0].trim().split(" ")[0].trim().equals("contact")) {
            if (splitArgs.length != 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddContactCommand.MESSAGE_USAGE));
            }
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_NAME,
                    PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

            if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddContactCommand.MESSAGE_USAGE));
            }

            Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
            Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
            Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            Person person = new Person(name, phone, email, address, tagList);

            return new AddContactCommand(person);
        } else if (splitArgs[0].trim().equals("todo")) {
            if (splitArgs.length != 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddTodoCommand.MESSAGE_USAGE));
            }
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_DESCRIPTION,
                            PREFIX_DATE, PREFIX_TIME, PREFIX_RECURRING, PREFIX_TAG);
            if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_TIME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddTodoCommand.MESSAGE_USAGE));
            }
            String description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            String date = argMultimap.getValue(PREFIX_DATE).get().trim();
            String time = argMultimap.getValue(PREFIX_TIME).get().trim();
            ParserUtil.checkDateValidity(date);
            ParserUtil.checkTimeValidity(time);
            ParserUtil.validateDescription(description);
            String deadline = date + " " + time;

            Todo todo;
            if (arePrefixesPresent(argMultimap, PREFIX_RECURRING)) {
                String recurrenceInput = argMultimap.getValue(PREFIX_RECURRING).get();
                try {
                    String[] recurrenceSplit = recurrenceInput.split(" ");
                    Integer recurrenceValue = Integer.parseInt(recurrenceSplit[0]);
                    String recurrenceTimePeriod = recurrenceSplit[1];
                    if (checkChronoUnitValidity(recurrenceTimePeriod)
                            && checkRecurrenceValueValidity(recurrenceValue)) {
                        Recurrence recurrence = new Recurrence(recurrenceValue, recurrenceTimePeriod);
                        todo = new Todo(description, deadline, recurrence, tagList);
                    } else {
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                AddTodoCommand.MESSAGE_USAGE));
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            AddTodoCommand.MESSAGE_USAGE));
                }
            } else {
                todo = new Todo(description, deadline, tagList);
            }
            return new AddTodoCommand(todo);
        } else if (splitArgs[0].trim().equals("event")) {
            if (splitArgs.length != 2) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddEventCommand.MESSAGE_USAGE));
            }
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" " + splitArgs[1], PREFIX_DESCRIPTION,
                    PREFIX_STARTDATE, PREFIX_STARTTIME, PREFIX_ENDDATE, PREFIX_ENDTIME, PREFIX_RECURRING, PREFIX_TAG);

            if (!arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_STARTDATE,
                    PREFIX_STARTTIME, PREFIX_ENDDATE, PREFIX_ENDTIME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddEventCommand.MESSAGE_USAGE));
            }
            String description = argMultimap.getValue(PREFIX_DESCRIPTION).get().trim();
            Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            String stDate = argMultimap.getValue(PREFIX_STARTDATE).get().trim();
            String stTime = argMultimap.getValue(PREFIX_STARTTIME).get().trim();
            String endDate = argMultimap.getValue(PREFIX_ENDDATE).get().trim();
            String endTime = argMultimap.getValue(PREFIX_ENDTIME).get().trim();
            ParserUtil.checkDateValidity(stDate);
            ParserUtil.checkTimeValidity(stTime);
            ParserUtil.checkDateValidity(endDate);
            ParserUtil.checkTimeValidity(endTime);
            ParserUtil.validateDescription(description);
            String stDateTime = stDate + " " + stTime;
            String endDateTime = endDate + " " + endTime;

            Event event;
            if (arePrefixesPresent(argMultimap, PREFIX_RECURRING)) {
                String recurrenceInput = argMultimap.getValue(PREFIX_RECURRING).get();
                try {
                    String[] recurrenceSplit = recurrenceInput.split(" ");
                    Integer recurrenceValue = Integer.parseInt(recurrenceSplit[0]);
                    String recurrenceTimePeriod = recurrenceSplit[1];
                    if (checkChronoUnitValidity(recurrenceTimePeriod)
                            && checkRecurrenceValueValidity(recurrenceValue)) {
                        Recurrence recurrence = new Recurrence(recurrenceValue, recurrenceTimePeriod);
                        event = new Event(description, stDateTime, endDateTime, recurrence, tagList);
                    } else {
                        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                AddEventCommand.MESSAGE_USAGE));
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            AddEventCommand.MESSAGE_USAGE));
                }
            } else {
                event = new Event(description, stDateTime, endDateTime, tagList);
            }
            return new AddEventCommand(event);
        } else {
            throw new ParseException(AddCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if the recurrence unit is day/week/month/year.
     * @param recurrenceTimePeriod input by user
     * @return boolean
     */
    private static boolean checkChronoUnitValidity(String recurrenceTimePeriod) {
        return recurrenceTimePeriod.equals(DAY) || recurrenceTimePeriod.equals(WEEK)
                || recurrenceTimePeriod.equals(MONTH) || recurrenceTimePeriod.equals(YEAR);
    }

    /**
     * Returns true if the recurrence value is > 0.
     * @param value input by user
     * @return boolean
     */
    private static boolean checkRecurrenceValueValidity(Integer value) {
        return value > 0;
    }
}
