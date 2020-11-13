package seedu.jarvis.logic.parser;

import static seedu.jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.jarvis.logic.commands.add.AddCommand;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Todo;

/**
 * Parses input add task arguments and creates a new Specified Task object
 */
public class TaskCommandParser {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * Takes in user input parameters and creates a Todo.
     * Returns a todo task.
     */
    public static Todo parseTodo(String[] nameKeywords, int length) {
        String description = nameKeywords[1];

        assert length >= 2 : "The task should already contain a description of >= 1 word";
        for (int i = 2; i < length; i++) {
            description = description + " " + nameKeywords[i];
        }

        return new Todo(description);
    }

    /**
     * Takes in user input parameters and creates an Event.
     * Returns an event task.
     */
    public static Event parseEvent(String[] nameKeywords, int length) throws ParseException {
        LocalDateTime formattedEventDateTime;

        try {
            String eventDescription = parseTimedTaskDescription(nameKeywords, length);
            formattedEventDateTime = parseTimedTaskTime(nameKeywords, length);

            return new Event(eventDescription, formattedEventDateTime);

        } catch (ParseException pe) {
            throw pe;
        }
    }

    /**
     * Takes in user input parameters and creates a Deadline.
     * Returns a deadline task.
     */
    public static Deadline parseDeadline(String[] nameKeywords, int length) throws ParseException {
        LocalDateTime formattedDeadlineDateTime;

        try {
            String deadlineDescription = parseTimedTaskDescription(nameKeywords, length);
            formattedDeadlineDateTime = parseTimedTaskTime(nameKeywords, length);

            return new Deadline(deadlineDescription, formattedDeadlineDateTime);

        } catch (ParseException pe) {
            throw pe;
        }
    }

    /**
     * Parses timed event description
     * @param nameKeywords
     * @param length
     * @return String of description
     * @throws ParseException
     */
    public static String parseTimedTaskDescription(String[] nameKeywords, int length) throws ParseException {
        int datePrefixLocation = -1;

        if (nameKeywords[1].substring(0, 2).equals(PREFIX_DATE)
            || nameKeywords[1].substring(0, 2).equals(PREFIX_TIME)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_MISSING_DESCRIPTION));
        }

        for (int i = 2; i < length; i++) {
            if (nameKeywords[i].length() <= 1) {
                //Ignores the string segment if the length is <= 1

            } else if (nameKeywords[i].substring(0, 2).equals(PREFIX_DATE)) {
                datePrefixLocation = i;
            }
        }

        String taskDescription = nameKeywords[1];
        for (int i = 2; i < datePrefixLocation; i++) {
            taskDescription = taskDescription + " " + nameKeywords[i];
        }

        return taskDescription;
    }

    /**
     * Parses the date time String to LocalDateTime object with checks for missing date, missing time, or incorrectly
     * rounding down of date while parsing using Java API.
     * @param nameKeywords
     * @param length
     * @return LocalDateTime
     * @throws ParseException
     */
    public static LocalDateTime parseTimedTaskTime(String[] nameKeywords, int length) throws ParseException {
        boolean hasDatePrefix = false;
        boolean hasTimePrefix = false;
        int datePrefixLocation = -1;
        int timePrefixLocation = -1;

        for (int i = 2; i < length; i++) {
            if (nameKeywords[i].length() <= 1) {
                //Ignores the string segment if the length is <= 1

            } else if (nameKeywords[i].substring(0, 2).equals(PREFIX_DATE)) {
                hasDatePrefix = true;
                datePrefixLocation = i;

            } else if (nameKeywords[i].substring(0, 2).equals(PREFIX_TIME)) {
                hasTimePrefix = true;
                timePrefixLocation = i;
            }
        }

        if (hasTimePrefix && datePrefixLocation > timePrefixLocation) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_INVALID_DATETIME));

        } else if (!hasDatePrefix || !hasTimePrefix) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_MISSING_DATE));
        }

        assert hasDatePrefix : "Date prefix d/ should already be handled properly";
        assert hasTimePrefix : "Time prefix t/ should already be handled properly";
        assert datePrefixLocation > 0 : "Date prefix location should already been found correctly";
        assert timePrefixLocation > 0 : "Time prefix location should already been found correctly";

        LocalDateTime formattedDateTime;
        try {
            formattedDateTime = parseDateTimeString(nameKeywords[datePrefixLocation], nameKeywords[timePrefixLocation]);

        } catch (ParseException pe) {
            throw pe;
        }

        return formattedDateTime;
    }

    /**
     * Formats the datetime String to LocalDateTime object
     * @param dateString
     * @param timeString
     * @return LocalDateTime object
     * @throws ParseException
     */
    public static LocalDateTime parseDateTimeString(String dateString, String timeString)
            throws ParseException {
        String taskDateTime = dateString.substring(2) + " " + timeString.substring(2);
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime formattedTaskDateTime;

        try {
            formattedTaskDateTime = LocalDateTime.parse(taskDateTime, dateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_INVALID_DATETIME));
        }

        if (isDateTimeChanged(taskDateTime, formattedTaskDateTime)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_INVALID_DATETIME));
        }

        return formattedTaskDateTime;
    }

    /**
     * Compares if the string forms of user input and parsed LocalDateTime have the same values.
     * @param userInput date time input by the user.
     * @param parsedDateTime date time parsed into Java LocalDateTime.
     * @return a boolean value check if both araguments' values are the same.
     */
    private static boolean isDateTimeChanged(String userInput, LocalDateTime parsedDateTime) {
        String stringifiedParsedDateTime = parsedDateTime.toString().replace('T', ' ');
        return !(userInput.equals(stringifiedParsedDateTime));
    }

}
