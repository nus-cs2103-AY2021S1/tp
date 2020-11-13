package seedu.jarvis.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jarvis.model.consultation.Consultation;

public class TypicalConsultations {
    public static final String TEST_CONSULTATION_NAME_FIRST = "Alice";
    public static final String TEST_CONSULTATION_NAME_SECOND = "Bob";
    public static final String TEST_CONSULTATION_NAME_THIRD = "Carl";
    public static final String TEST_CONSULTATION_NAME_FOURTH = "David";
    public static final String TEST_CONSULTATION_NAME_FIFTH = "Eugene";

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final LocalDateTime TEST_CONSULTATION_DATETIME_FIRST =
            LocalDateTime.parse("2018-10-20 10:00", dateTimeFormat);
    public static final LocalDateTime TEST_CONSULTATION_DATETIME_SECOND =
            LocalDateTime.parse("2019-08-31 23:00", dateTimeFormat);
    public static final LocalDateTime TEST_CONSULTATION_DATETIME_THIRD =
            LocalDateTime.parse("2020-12-13 23:59", dateTimeFormat);
    public static final LocalDateTime TEST_CONSULTATION_DATETIME_FOURTH =
            LocalDateTime.parse("2021-01-20 16:42", dateTimeFormat);
    public static final LocalDateTime TEST_CONSULTATION_DATETIME_FIFTH =
            LocalDateTime.parse("2024-11-30 02:51", dateTimeFormat);

    public static final String FORMATTED_DATETIME_FIRST =
            TEST_CONSULTATION_DATETIME_FIRST.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_SECOND =
            TEST_CONSULTATION_DATETIME_SECOND.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_THIRD =
            TEST_CONSULTATION_DATETIME_THIRD.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_FOURTH =
            TEST_CONSULTATION_DATETIME_FOURTH.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_FIFTH =
            TEST_CONSULTATION_DATETIME_FIFTH.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));

    public static final Consultation TEST_CONSULTATION_ONE = new Consultation(TEST_CONSULTATION_NAME_FIRST,
            TEST_CONSULTATION_DATETIME_FIRST);
    public static final Consultation TEST_CONSULTATION_TWO = new Consultation(TEST_CONSULTATION_NAME_SECOND,
            TEST_CONSULTATION_DATETIME_SECOND);
    public static final Consultation TEST_CONSULTATION_THREE = new Consultation(TEST_CONSULTATION_NAME_THIRD,
            TEST_CONSULTATION_DATETIME_THIRD);
    public static final Consultation TEST_CONSULTATION_FOUR = new Consultation(TEST_CONSULTATION_NAME_FOURTH,
            TEST_CONSULTATION_DATETIME_FOURTH);
    public static final Consultation TEST_CONSULTATION_FIVE = new Consultation(TEST_CONSULTATION_NAME_FIFTH,
            TEST_CONSULTATION_DATETIME_FIFTH);

    public static List<Consultation> getTypicalConsultations() {
        return new ArrayList<>(Arrays.asList(TEST_CONSULTATION_ONE, TEST_CONSULTATION_TWO, TEST_CONSULTATION_THREE,
                TEST_CONSULTATION_FOUR, TEST_CONSULTATION_FIVE));
    }
}
