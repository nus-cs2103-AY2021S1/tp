package seedu.jarvis.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jarvis.model.masterycheck.MasteryCheck;

public class TypicalMasteryChecks {
    public static final String TEST_MASTERY_CHECK_NAME_FIRST = "Alice";
    public static final String TEST_MASTERY_CHECK_NAME_SECOND = "Bob";
    public static final String TEST_MASTERY_CHECK_NAME_THIRD = "Carl";
    public static final String TEST_MASTERY_CHECK_NAME_FOURTH = "David";
    public static final String TEST_MASTERY_CHECK_NAME_FIFTH = "Eugene";

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final LocalDateTime TEST_MASTERY_CHECK_DATETIME_FIRST =
            LocalDateTime.parse("2018-10-20 10:00", dateTimeFormat);
    public static final LocalDateTime TEST_MASTERY_CHECK_DATETIME_SECOND =
            LocalDateTime.parse("2019-08-31 23:00", dateTimeFormat);
    public static final LocalDateTime TEST_MASTERY_CHECK_DATETIME_THIRD =
            LocalDateTime.parse("2020-12-13 23:59", dateTimeFormat);
    public static final LocalDateTime TEST_MASTERY_CHECK_DATETIME_FOURTH =
            LocalDateTime.parse("2021-01-20 16:42", dateTimeFormat);
    public static final LocalDateTime TEST_MASTERY_CHECK_DATETIME_FIFTH =
            LocalDateTime.parse("2024-11-30 02:51", dateTimeFormat);

    public static final String FORMATTED_DATETIME_FIRST =
            TEST_MASTERY_CHECK_DATETIME_FIRST.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_SECOND =
            TEST_MASTERY_CHECK_DATETIME_SECOND.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_THIRD =
            TEST_MASTERY_CHECK_DATETIME_THIRD.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_FOURTH =
            TEST_MASTERY_CHECK_DATETIME_FOURTH.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_FIFTH =
            TEST_MASTERY_CHECK_DATETIME_FIFTH.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));

    public static final MasteryCheck TEST_MASTERY_CHECK_ONE = new MasteryCheck(TEST_MASTERY_CHECK_NAME_FIRST,
            TEST_MASTERY_CHECK_DATETIME_FIRST);
    public static final MasteryCheck TEST_MASTERY_CHECK_TWO = new MasteryCheck(TEST_MASTERY_CHECK_NAME_SECOND,
            TEST_MASTERY_CHECK_DATETIME_SECOND);
    public static final MasteryCheck TEST_MASTERY_CHECK_THREE = new MasteryCheck(TEST_MASTERY_CHECK_NAME_THIRD,
            TEST_MASTERY_CHECK_DATETIME_THIRD);
    public static final MasteryCheck TEST_MASTERY_CHECK_FOUR = new MasteryCheck(TEST_MASTERY_CHECK_NAME_FOURTH,
            TEST_MASTERY_CHECK_DATETIME_FOURTH);
    public static final MasteryCheck TEST_MASTERY_CHECK_FIVE = new MasteryCheck(TEST_MASTERY_CHECK_NAME_FIFTH,
            TEST_MASTERY_CHECK_DATETIME_FIFTH);

    public static List<MasteryCheck> getTypicalMasteryChecks() {
        return new ArrayList<>(Arrays.asList(TEST_MASTERY_CHECK_ONE, TEST_MASTERY_CHECK_TWO, TEST_MASTERY_CHECK_THREE,
                TEST_MASTERY_CHECK_FOUR, TEST_MASTERY_CHECK_FIVE));
    }
}
