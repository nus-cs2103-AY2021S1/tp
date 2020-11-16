package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    private static final String VALID_TIME_STRING = "12:00";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Time.fromTimeString(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> Time.fromTimeString(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null name
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("4 pm")); // incorrect format
        assertFalse(Time.isValidTime("1200")); // missing colon characters
        assertFalse(Time.isValidTime("1270")); // incorrect input

        // valid time
        assertTrue(Time.isValidTime(VALID_TIME_STRING));
        assertTrue(Time.isValidTime("23:59"));
        assertTrue(Time.isValidTime("00:00"));
    }


    @Test
    public void equals() {
        // Equality to null
        assertNotEquals(Time.fromTimeString(VALID_TIME_STRING), null);

        // Equality to objects of other classes / parent classes
        assertNotEquals(Time.fromTimeString(VALID_TIME_STRING), new Object());
    }

    @Test
    public void testEquals_isReflexive() {
        Time x = Time.fromTimeString(VALID_TIME_STRING);
        assertEquals(x, x);
        assertEquals(x.hashCode(), x.hashCode());
    }

    @Test
    public void testEquals_isSymmetric() {
        Time x = Time.fromTimeString(VALID_TIME_STRING);
        Time y = Time.fromTimeString(VALID_TIME_STRING);
        assertEquals(x, y);
        assertEquals(y, x);
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    public void testEquals_isTransitive() {
        Time x = Time.fromTimeString(VALID_TIME_STRING);
        Time y = Time.fromTimeString(VALID_TIME_STRING);
        Time z = Time.fromTimeString(VALID_TIME_STRING);
        assertEquals(x, y);
        assertEquals(y, z);
        assertEquals(x, z);
        assertEquals(x.hashCode(), z.hashCode());
    }

    @Test
    public void testCompareTo() {
        // earlier time
        assertEquals(-1,
            Time.fromTimeString("11:59").compareTo(
                Time.fromTimeString("12:00")
            ));

        // later time
        assertEquals(1,
            Time.fromTimeString("12:01").compareTo(
                Time.fromTimeString("12:00")
            ));

        // same time
        assertEquals(0,
            Time.fromTimeString("12:00").compareTo(
                Time.fromTimeString("12:00")
            ));
    }
}
