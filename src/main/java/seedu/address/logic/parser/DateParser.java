package seedu.address.logic.parser;

import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse Date.
 */
public class DateParser {
    public static final String DATE_INVALID_FORMAT = "Date format should be either MM/DD/YYYY or MM/DD/YYYY HH:mm."
            + "Note: Single digit (except for year) can start with leading zero.";

    /**
     * Parses the given {@code String} to Date
     * @throws ParseException if the user input does not conform the expected date format
     */
    public static Date parseDate(String strDate) throws ParseException {

        String formatWithMin = "y-M-d HH:mm";
        String formatWithoutMin = "y-M-d";

        try {
            return new SimpleDateFormat(strDate.length() > 11 ? formatWithMin : formatWithoutMin).parse(strDate);
        } catch (java.text.ParseException e) {
            throw new ParseException(DATE_INVALID_FORMAT);
        }
    }
}
