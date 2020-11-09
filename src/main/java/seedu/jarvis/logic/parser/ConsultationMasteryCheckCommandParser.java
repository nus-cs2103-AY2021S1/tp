package seedu.jarvis.logic.parser;

import static seedu.jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.jarvis.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.jarvis.logic.commands.add.AddCommand;
import seedu.jarvis.logic.parser.exceptions.ParseException;
import seedu.jarvis.model.consultation.Consultation;
import seedu.jarvis.model.masterycheck.MasteryCheck;

/**
 * Parses input add consultation or mastery check arguments and creates a new specified consultation or mc object
 */
public class ConsultationMasteryCheckCommandParser {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    /**
     * Takes in user input parameters and creates an Consultation.
     * Returns an consultation object.
     */
    public static Consultation parseConsultation(String[] nameKeywords, int length) throws ParseException {
        LocalDateTime formattedEventDateTime;
        String studentName;

        try {
            studentName = parseStudentName(nameKeywords, length);
            formattedEventDateTime = parseTimedTaskTime(nameKeywords, length);
        } catch (ParseException pe) {
            throw pe;
        }

        return new Consultation(studentName, formattedEventDateTime);
    }

    /**
     * Takes in user input parameters and creates a Mastery Check.
     * Returns a mastery check object.
     */
    public static MasteryCheck parseMasteryCheck(String[] nameKeywords, int length) throws ParseException {
        LocalDateTime formattedDeadlineDateTime;
        String studentName;
        try {
            studentName = parseStudentName(nameKeywords, length);
            formattedDeadlineDateTime = parseTimedTaskTime(nameKeywords, length);
        } catch (ParseException pe) {
            throw pe;
        }
        return new MasteryCheck(studentName, formattedDeadlineDateTime);
    }

    /**
     * Parses timed event description
     * @param nameKeywords
     * @param length
     * @return String of description
     */
    public static String parseStudentName(String[] nameKeywords, int length) throws ParseException {

        if (nameKeywords[1].equals(PREFIX_DATE) || nameKeywords[1].equals(PREFIX_TIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
        }

        int datePrefixLocation = -1;
        for (int i = 1; i < length; i++) {
            if (nameKeywords[i].length() > 1 && nameKeywords[i].substring(0, 2).equals(PREFIX_DATE)) {
                datePrefixLocation = i;
            }
        }

        if (datePrefixLocation == -1) { // if still not found
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
        }

        String studentName = nameKeywords[1];
        for (int i = 2; i < datePrefixLocation; i++) {
            studentName = studentName + " " + nameKeywords[i];
        }

        return studentName;
    }

    /**
     * Parses the date time String to LocalDateTime object with checks
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

        for (int i = 1; i < length; i++) {
            if (nameKeywords[i].length() > 1 && nameKeywords[i].substring(0, 2).equals(PREFIX_DATE)) {
                hasDatePrefix = true;
                datePrefixLocation = i;
            }

            if (nameKeywords[i].length() > 1 && nameKeywords[i].substring(0, 2).equals(PREFIX_TIME)) {
                hasTimePrefix = true;
                timePrefixLocation = i;
            }
        }

        if (datePrefixLocation > timePrefixLocation || !hasDatePrefix || !hasTimePrefix) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_MISSING_INFO_CONSULTATION));
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
        LocalDateTime formattedConsultationDateTime;

        try {
            formattedConsultationDateTime = LocalDateTime.parse(taskDateTime, dateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_INVALID_DATETIME));
        }

        if (isDateTimeChanged(taskDateTime, formattedConsultationDateTime)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_INVALID_DATETIME));
        }

        return formattedConsultationDateTime;
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
