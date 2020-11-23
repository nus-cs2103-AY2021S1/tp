package seedu.tr4cker.model.util;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.temporal.TemporalAdjusters.next;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import seedu.tr4cker.logic.parser.exceptions.ParseException;

public class NaturalDateUtil {

    public static final String MESSAGE_DAY = "Please enter a valid day of the week in full, e.g. Saturday";
    public static final String MESSAGE_TIME = "Please enter a valid time in 24Hr format";
    public static final String VALIDATION_REGEX = "[a-zA-Z]{5,}";
    public static final String VALIDATION_REGEX_TIME = "[a-zA-Z]{5,} \\d{4}";

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private static final Map<String, DayOfWeek> DAYS_OF_WEEK;

    static {
        DAYS_OF_WEEK = new HashMap<>();
        DAYS_OF_WEEK.put("MONDAY", MONDAY);
        DAYS_OF_WEEK.put("TUESDAY", TUESDAY);
        DAYS_OF_WEEK.put("WEDNESDAY", WEDNESDAY);
        DAYS_OF_WEEK.put("THURSDAY", THURSDAY);
        DAYS_OF_WEEK.put("FRIDAY", FRIDAY);
        DAYS_OF_WEEK.put("SATURDAY", SATURDAY);
        DAYS_OF_WEEK.put("SUNDAY", SUNDAY);
    }

    /**
     * Returns true if a given string is a Natural Deadline.
     */
    public static boolean isNaturalDeadline(String test) {
        return test.matches(VALIDATION_REGEX) || test.matches(VALIDATION_REGEX_TIME);
    }

    /**
     * Converts a natural date-time input into a formatted DateTime String.
     *
     * @param naturalDateTime natural date-time input
     * @return formatted DateTime String
     */
    public static String convertToDateTime(String naturalDateTime) throws ParseException {
        assert isNaturalDeadline(naturalDateTime);
        String [] deadlineTokens = naturalDateTime.split(" ");
        int unitsOfDeadline = deadlineTokens.length;
        assert unitsOfDeadline == 1 || unitsOfDeadline == 2;

        return parse(deadlineTokens, unitsOfDeadline == 2);
    }

    private static String parse(String[] deadlineTokens, boolean hasTime) throws ParseException {
        String dateTime;
        String dayOfDeadline = deadlineTokens[0].toUpperCase();

        if (dayOfDeadline.equals("TODAY")) {
            dateTime = LocalDate.now().format(DATE_TIME_FORMATTER);
        } else {
            if (!DAYS_OF_WEEK.containsKey(dayOfDeadline)) {
                throw new ParseException(MESSAGE_DAY);
            }
            dateTime = LocalDate.now().with(next(DAYS_OF_WEEK.get(dayOfDeadline)))
                    .format(DATE_TIME_FORMATTER);
        }

        if (hasTime) {
            try {
                LocalTime.parse(deadlineTokens[1], DateTimeFormatter.ofPattern("HHmm"));
                dateTime += " " + deadlineTokens[1];
            } catch (DateTimeParseException e) {
                throw new ParseException(MESSAGE_TIME);
            }
        }
        return dateTime;
    }
}
