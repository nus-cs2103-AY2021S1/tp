package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeUtilTest {

    @Test
    public void parseFirstMatching_nullText_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DateTimeUtil.parseFirstMatching(
                null, LocalDate::from, DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    @Test
    public void parseFirstMatching_nullQuery_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DateTimeUtil.parseFirstMatching(
                "20201230", null, DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    @Test
    public void parseFirstMatching_nullFormatter_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> DateTimeUtil.parseFirstMatching(
                "20201230", LocalDate::from, (DateTimeFormatter) null));
    }

    @Test
    public void parseFirstMatching_noFormattersGiven_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "20201230", LocalDate::from));
    }

    @Test
    public void parseFirstMatching_singleDateFormatterInvalidText_throwsDateTimeParseException() {
        // EP: invalid text for DATE_FORMAT_YEAR_MONTH_DAY
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30122020", LocalDate::from, DateTimeUtil.DATE_FORMAT_YEAR_MONTH_DAY));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30 Dec 2020", LocalDate::from, DateTimeUtil.DATE_FORMAT_YEAR_MONTH_DAY));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30/12/2020", LocalDate::from, DateTimeUtil.DATE_FORMAT_YEAR_MONTH_DAY));

        // EP: invalid text for DATE_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "20201230", LocalDate::from, DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "Dec 30 2020", LocalDate::from, DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30/12/2020", LocalDate::from, DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED));

        // EP: invalid text for DATE_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "20201230", LocalDate::from, DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30 Dec 2020", LocalDate::from, DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "2020/12/30", LocalDate::from, DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED));
    }

    @Test
    public void parseFirstMatching_singleDateTimeFormatterInvalidText_throwsDateTimeParseException() {
        // EP: invalid text for DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30122020 2359", LocalDateTime::from, DateTimeUtil.DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30 Dec 2020 2359", LocalDateTime::from, DateTimeUtil.DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30/12/2020 2359", LocalDateTime::from, DateTimeUtil.DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME));

        // EP: invalid text for DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "20201230 2359", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "Dec 30 2020 2359", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30/12/2020 2359", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME));

        // EP: invalid text for DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "20201230 2359", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30 Dec 2020 2359", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "2020/12/30", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME));
    }

    @Test
    public void parseFirstMatching_manyDateFormattersInvalidText_throwsDateTimeParseException() {
        DateTimeFormatter[] formatters = {
            DateTimeUtil.DATE_FORMAT_YEAR_MONTH_DAY,
            DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED,
            DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED
        };

        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30122020", LocalDate::from, formatters));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "aaa", LocalDate::from, formatters));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "", LocalDate::from, formatters));
    }

    @Test
    public void parseFirstMatching_manyDateTimeFormattersInvalidText_throwsDateTimeParseException() {
        DateTimeFormatter[] formatters = {
            DateTimeUtil.DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME,
            DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME,
            DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME
        };

        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "30122020 2359", LocalDateTime::from, formatters));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "aaa", LocalDateTime::from, formatters));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseFirstMatching(
                "", LocalDateTime::from, formatters));
    }

    @Test
    public void parseFirstMatching_singleDateFormatterValidText_correctObject() {
        int year = 2020;
        int month = 12;
        int day = 30;
        LocalDate date = LocalDate.of(year, month, day);

        assertEquals(date, DateTimeUtil.parseFirstMatching(
                "20201230", LocalDate::from, DateTimeUtil.DATE_FORMAT_YEAR_MONTH_DAY));
        assertEquals(date, DateTimeUtil.parseFirstMatching(
                "30 Dec 2020", LocalDate::from, DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED));
        assertEquals(date, DateTimeUtil.parseFirstMatching(
                "30/12/2020", LocalDate::from, DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED));
    }

    @Test
    public void parseFirstMatching_singleDateTimeFormatterValidText_correctObject() {
        int year = 2020;
        int month = 12;
        int day = 30;
        int hour = 23;
        int minute = 59;
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);

        /*
         * EP: Valid text (custom time)
         */
        assertEquals(dateTime, DateTimeUtil.parseFirstMatching(
                "20201230 2359", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME));
        assertEquals(dateTime, DateTimeUtil.parseFirstMatching(
                "30 Dec 2020 2359", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME));
        assertEquals(dateTime, DateTimeUtil.parseFirstMatching(
                "30/12/2020 2359", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME));

        int defaultHour = 0;
        int defaultMinute = 0;
        LocalDateTime dateWithDefaultTime = LocalDateTime.of(year, month, day, defaultHour, defaultMinute);

        /*
         * EP: Valid text (default time)
         */
        assertEquals(dateWithDefaultTime, DateTimeUtil.parseFirstMatching(
                "20201230", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME));
        assertEquals(dateWithDefaultTime, DateTimeUtil.parseFirstMatching(
                "30 Dec 2020", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME));
        assertEquals(dateWithDefaultTime, DateTimeUtil.parseFirstMatching(
                "30/12/2020", LocalDateTime::from,
                DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME));
    }

    @Test
    public void parseFirstMatching_manyDateFormatterValidText_correctObject() {
        DateTimeFormatter[] formatters = {
            DateTimeUtil.DATE_FORMAT_YEAR_MONTH_DAY,
            DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED,
            DateTimeUtil.DATE_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED
        };

        int year = 2020;
        int month = 12;
        int day = 30;
        LocalDate date = LocalDate.of(year, month, day);

        assertEquals(date, DateTimeUtil.parseFirstMatching(
                "20201230", LocalDate::from, formatters));
        assertEquals(date, DateTimeUtil.parseFirstMatching(
                "30 Dec 2020", LocalDate::from, formatters));
        assertEquals(date, DateTimeUtil.parseFirstMatching(
                "30/12/2020", LocalDate::from, formatters));
    }

    @Test
    public void parseFirstMatching_manyDateTimeFormatterValidText_correctObject() {
        DateTimeFormatter[] formatters = {
            DateTimeUtil.DATETIME_FORMAT_YEAR_MONTH_DAY_OPTIONAL_TIME,
            DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_LONG_SPACE_DELIMITED_OPTIONAL_TIME,
            DateTimeUtil.DATETIME_FORMAT_DAY_MONTH_YEAR_SLASH_DELIMITED_OPTIONAL_TIME
        };

        int year = 2020;
        int month = 12;
        int day = 30;
        int hour = 23;
        int minute = 59;
        LocalDateTime dateWithCustomTime = LocalDateTime.of(year, month, day, hour, minute);

        /*
         * EP: Valid text (custom time)
         */
        assertEquals(dateWithCustomTime, DateTimeUtil.parseFirstMatching(
                "20201230 2359", LocalDateTime::from, formatters));
        assertEquals(dateWithCustomTime, DateTimeUtil.parseFirstMatching(
                "30 Dec 2020 2359", LocalDateTime::from, formatters));
        assertEquals(dateWithCustomTime, DateTimeUtil.parseFirstMatching(
                "30/12/2020 2359", LocalDateTime::from, formatters));

        int defaultHour = 0;
        int defaultMinute = 0;
        LocalDateTime dateWithDefaultTime = LocalDateTime.of(year, month, day, defaultHour, defaultMinute);

        /*
         * EP: Valid text (default time)
         */
        assertEquals(dateWithDefaultTime, DateTimeUtil.parseFirstMatching(
                "20201230", LocalDateTime::from, formatters));
        assertEquals(dateWithDefaultTime, DateTimeUtil.parseFirstMatching(
                "30 Dec 2020", LocalDateTime::from, formatters));
        assertEquals(dateWithDefaultTime, DateTimeUtil.parseFirstMatching(
                "30/12/2020", LocalDateTime::from, formatters));
    }
}
