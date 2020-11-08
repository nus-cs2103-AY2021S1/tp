package seedu.address.commons.util;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalQuery;

//@@author w-yeehong
/**
 * Helper functions for handling date and date-time objects.
 */
public class DateTimeUtil {

    // Date formats
    /** e.g. 20200920 */
    public static final DateTimeFormatter DATE_FORMAT_YEAR_MONTH_DAY =
            DateTimeFormatter.ofPattern("yyyyMMdd");

    /** e.g. 20 Sep 2020 */
    public static final DateTimeFormatter DATE_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED =
            DateTimeFormatter.ofPattern("d MMM yyyy");

    /** e.g. 20/9/2020 */
    public static final DateTimeFormatter DATE_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED =
            DateTimeFormatter.ofPattern("d/M/yyyy");


    // Date-time formats
    /** e.g. 20200920 or 20200920 2359 */
    public static final DateTimeFormatter DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME =
            createFormatterWithOptionalTime("yyyyMMdd", " HHmm");

    /** e.g. 20 Sep 2020 (time defaults to 0000) or 20 Sep 2020 2359 */
    public static final DateTimeFormatter DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME =
            createFormatterWithOptionalTime("d MMM yyyy", " HHmm");

    /** e.g. 20/9/2020 (time defaults to 0000) or 20/09/2020 2359 */
    public static final DateTimeFormatter DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME =
            createFormatterWithOptionalTime("d/M/yyyy", " HHmm");


    /**
     * Parses the text into a date-time object (e.g. {@code LocalDate} or {@code LocalDateTime})
     * using all given {@code DateTimeFormatter} and returns the first successful result.
     *
     * The text is parsed using each formatter in turn. No output is generated if a formatter fails to
     * parse the text. However, a {@code DateTimeParseException} is thrown if all formatters fail.
     *
     * The {@code TemporalQuery<T>} is typically a method reference to a
     * {@code from(TemporalAccessor)} method.
     * For example:
     * <pre>
     *     LocalDate date = DateTimeUtil.parseFirstMatching(text, LocalDate::from, formatter1, formatter2);
     * </pre>
     *
     * For more details, refer to
     * https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#
     * parse-java.lang.CharSequence-java.time.temporal.TemporalQuery-
     *
     * @param text The text to parse, cannot be null.
     * @param query A way to retrieve information from a temporal-based object.
     * @param formatters Formatters to parse the text with, cannot be null.
     * @param <T> A date-time class, usually a {@code TemporalAccessor}.
     * @return The date-time object parsed from text.
     * @throws DateTimeParseException if text cannot be parsed with any of the formatters or no formatters provided.
     */
    public static <T> T parseFirstMatching(
            CharSequence text, TemporalQuery<T> query, DateTimeFormatter... formatters) {
        requireAllNonNull(text, query, formatters);

        for (DateTimeFormatter formatter : formatters) {
            try {
                return formatter.parse(text, query);
            } catch (DateTimeParseException e) {
                // Current formatter is invalid.
                // Proceed to test next formatter.
            }
        }

        String exceptionMessage = String.format("Unable to parse %s", text);
        int errorIndex = 0; // index in parsed text that was invalid, set to 0 to indicate entirety of text

        throw new DateTimeParseException(exceptionMessage, text, errorIndex);
    }

    /**
     * Creates a {@code DateTimeFormatter} with default values of time.
     * If a time conforms to the time format, the {@code DateTimeFormatter} sets the parsed date-time
     * to that value. Otherwise, the hour and minute of the parsed date-time are set to 0.
     *
     * @param dateFormat The pattern for the date portion of a string parsed by  a{@code DateTimeFormatter}.
     * @param timeFormat The pattern for the time portion of a string parsed by a {@code DateTimeFormatter}.
     * @return A {@code DateTimeFormatter} that defaults the hour and minute to 0 if time does not conform to format.
     */
    private static DateTimeFormatter createFormatterWithOptionalTime(String dateFormat, String timeFormat) {
        return new DateTimeFormatterBuilder()
                .appendPattern(dateFormat)
                .optionalStart()
                .appendPattern(timeFormat)
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();
    }
}
