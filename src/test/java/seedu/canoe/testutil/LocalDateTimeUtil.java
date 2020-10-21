package seedu.canoe.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * List of local date times for testing purposes.
 */
public class LocalDateTimeUtil {
    //Do not modify any of the local date times to prevent regressions with other tests
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final LocalDateTime VALID_LOCAL_DATE_TIME = LocalDateTime.parse("2020-11-20 1800", FORMATTER);
    public static final LocalDateTime VALID_LOCAL_DATE_TIME_1 = LocalDateTime.parse("2020-11-20 1900", FORMATTER);
    public static final LocalDateTime VALID_LOCAL_DATE_TIME_2 = LocalDateTime.parse("2020-09-20 1500", FORMATTER);
    public static final LocalDateTime VALID_LOCAL_DATE_TIME_3 = LocalDateTime.parse("2020-10-21 1000", FORMATTER);
    public static final LocalDateTime VALID_LOCAL_DATE_TIME_4 = LocalDateTime.parse("2020-10-20 1800", FORMATTER);
    public static final LocalDateTime VALID_LOCAL_DATE_TIME_5 = LocalDateTime.parse("2020-09-14 1500", FORMATTER);
    public static final LocalDateTime VALID_LOCAL_DATE_TIME_6 = LocalDateTime.parse("2020-09-15 1400", FORMATTER);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_ONE_DAY = LocalDateTime.now().plusDays(1);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_TWO_DAYS = LocalDateTime.now().plusDays(2);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_THREE_DAYS = LocalDateTime.now().plusDays(3);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_FOUR_DAYS = LocalDateTime.now().plusDays(4);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_FIVE_DAYS = LocalDateTime.now().plusDays(5);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_SIX_DAYS = LocalDateTime.now().plusDays(6);

}
