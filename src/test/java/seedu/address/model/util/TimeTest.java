package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class TimeTest {
    public static final String VALID_SAMPLE_A = "10:00";
    public static final String VALID_SAMPLE_B = "23:59";
    public static final String VALID_SAMPLE_C = "10:01";
    public static final String INVALID_SAMPLE_A = "01-01-2020 23:59";
    public static final String INVALID_SAMPLE_B = "01-01-2020";
    public static final String INVALID_SAMPLE_C = "23:00:59";
    public static final String INVALID_SAMPLE_D = "1:00";
    public static final String INVALID_SAMPLE_E = "24:00";


    @Test
    void isValidDateTimeSuccess() {
        // Date and Time
        assertTrue(Time.isValidTimePattern(VALID_SAMPLE_A));
        assertTrue(Time.isValidTimePattern(VALID_SAMPLE_B));
        assertTrue(Time.isValidTimePattern(VALID_SAMPLE_C));
    }


    @Test
    void parseSuccess() {
        Time dtA = new Time(VALID_SAMPLE_A);
        Time dtB = new Time(VALID_SAMPLE_B);
        Time dtC = new Time(VALID_SAMPLE_C);
        assertEquals(VALID_SAMPLE_A, dtA.toString());
        assertEquals(VALID_SAMPLE_B, dtB.toString());
        assertEquals(VALID_SAMPLE_C, dtC.toString());

    }

    @Test
    void parseFail() {
        // Fail pattern
        // E.g Missing or additional digits, additional date
        assertExceptionMessage(INVALID_SAMPLE_A, Time.MESSAGE_CONSTRAINTS);
        assertExceptionMessage(INVALID_SAMPLE_B, Time.MESSAGE_CONSTRAINTS);
        assertExceptionMessage(INVALID_SAMPLE_C, Time.MESSAGE_CONSTRAINTS);
        assertExceptionMessage(INVALID_SAMPLE_D, Time.MESSAGE_CONSTRAINTS);

        // Fail Range
        // E.g 24:00
        assertExceptionMessage(INVALID_SAMPLE_E, Time.MESSAGE_CONSTRAINTS);

    }

    @Test
    void compareSuccess() {
        Time a = new Time(VALID_SAMPLE_A);
        Time b = new Time(VALID_SAMPLE_B);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertTrue(a.compareTo(a) == 0);

    }

    public void assertExceptionMessage(String test, String messageConstraint) {
        try {
            new Time(test);
        } catch (IllegalArgumentException pe) {
            assertEquals(messageConstraint,
                    pe.getMessage());
        }
    }
}
