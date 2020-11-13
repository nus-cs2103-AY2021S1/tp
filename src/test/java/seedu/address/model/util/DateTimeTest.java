package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DateTimeTest {

    public static final String VALID_SAMPLE_A = "31-12-9999 23:59";
    public static final String VALID_SAMPLE_B = "01-01-2019 00:00";
    public static final String VALID_SAMPLE_C = "28-02-2019 10:00";
    public static final String VALID_SAMPLE_D = "29-02-2020 10:01";
    public static final String INVALID_SAMPLE_A = "Next monday";
    public static final String INVALID_SAMPLE_B = "01-1-2020 23:59";
    public static final String INVALID_SAMPLE_C = "23:59";
    public static final String INVALID_SAMPLE_D = "01-01-2020";
    public static final String INVALID_SAMPLE_E = "28-02-2019 23:00:59";
    public static final String INVALID_SAMPLE_F = "31-11-2020 23:00";
    public static final String INVALID_SAMPLE_G = "29-02-2019 23:00";
    public static final String INVALID_SAMPLE_H = "31-12-2018 23:00";


    @Test
    void isValidDateTimeSuccess() {
        // Date and Time

        // Latest possible valid date
        assertTrue(DateTime.isValidDateTimePattern(VALID_SAMPLE_A));
        // Earliest possible valid date
        assertTrue(DateTime.isValidDateTimePattern(VALID_SAMPLE_B));
        // Non-leap year
        assertTrue(DateTime.isValidDateTimePattern(VALID_SAMPLE_C));
        // Leap year
        assertTrue(DateTime.isValidDateTimePattern(VALID_SAMPLE_D));
    }


    @Test
    void parseSuccess() {
        DateTime dtA = new DateTime(VALID_SAMPLE_A);
        DateTime dtB = new DateTime(VALID_SAMPLE_B);
        DateTime dtC = new DateTime(VALID_SAMPLE_C);
        DateTime dtD = new DateTime(VALID_SAMPLE_D);
        assertEquals(VALID_SAMPLE_A, dtA.toString());
        assertEquals(VALID_SAMPLE_B, dtB.toString());
        assertEquals(VALID_SAMPLE_C, dtC.toString());
        assertEquals(VALID_SAMPLE_D, dtD.toString());
    }

    @Test
    void parseFail() {
        // Fail pattern
        // Incorrect date representation
        assertExceptionMessage(INVALID_SAMPLE_A, DateTime.MESSAGE_CONSTRAINTS);
        // Missing digit
        assertExceptionMessage(INVALID_SAMPLE_B, DateTime.MESSAGE_CONSTRAINTS);
        // Missing date
        assertExceptionMessage(INVALID_SAMPLE_C, DateTime.MESSAGE_CONSTRAINTS);
        // Missing time
        assertExceptionMessage(INVALID_SAMPLE_D, DateTime.MESSAGE_CONSTRAINTS);
        // Seconds included in time
        assertExceptionMessage(INVALID_SAMPLE_E, DateTime.MESSAGE_CONSTRAINTS);

        // Fail Range
        // Too many days in a month
        assertExceptionMessage(INVALID_SAMPLE_F, DateTime.MESSAGE_CONSTRAINTS);
        // Too many days in a month (non-Leap year)
        assertExceptionMessage(INVALID_SAMPLE_G, DateTime.MESSAGE_CONSTRAINTS);
        // Date is before the earliest possible valid date
        assertExceptionMessage(INVALID_SAMPLE_H, DateTime.MESSAGE_CONSTRAINTS);

    }

    @Test
    void compareSuccess() {
        DateTime a = new DateTime(VALID_SAMPLE_A);
        DateTime b = new DateTime(VALID_SAMPLE_B);
        DateTime c = new DateTime(VALID_SAMPLE_A);
        assertTrue(a.compareTo(b) > 0);
        assertTrue(b.compareTo(a) < 0);
        assertTrue(a.compareTo(a) == 0);
        assertTrue(a.compareTo(c) == 0);

    }

    public void assertExceptionMessage(String test, String messageConstraint) {
        try {
            new DateTime(test);
        } catch (IllegalArgumentException pe) {
            assertEquals(messageConstraint,
                    pe.getMessage());
        }
    }
}
