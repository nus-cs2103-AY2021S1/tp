package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.basicEqualsTests;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;

class LastModifiedInstantTest {

    private static final String VALID_INSTANT_1 = "2020-10-10T15:18:35.617617Z";
    private static final String VALID_INSTANT_2 = "2020-10-11T15:18:35.617617Z";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastModifiedInstant(null));
    }

    @Test
    public void constructor_invalidInstant_returnsCurrentInstant() {
        LocalDate currentDate = LocalDate.now();
        LastModifiedInstant lastModifiedInstant = new LastModifiedInstant("invalid instant");

        LocalDate instantDate = Instant.parse(lastModifiedInstant.toString())
                .atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(currentDate.getYear(), instantDate.getYear());
        assertEquals(currentDate.getMonth(), instantDate.getMonth());
        assertEquals(currentDate.getDayOfMonth(), instantDate.getDayOfMonth());
    }

    @Test
    public void constructor_noArguments_returnsCurrentInstant() {
        LastModifiedInstant lastModifiedInstant = new LastModifiedInstant();
        // Test to make sure the generated instant is within 1 second of now.
        Instant instantNowMinusOneSecond = Instant.now().minusSeconds(1);
        assertEquals(instantNowMinusOneSecond.compareTo(lastModifiedInstant.value), -1);

        LocalDate currentDate = LocalDate.now();
        LocalDate instantDate = Instant.parse(lastModifiedInstant.toString())
                .atZone(ZoneId.systemDefault()).toLocalDate();
        assertEquals(currentDate.getYear(), instantDate.getYear());
        assertEquals(currentDate.getMonth(), instantDate.getMonth());
        assertEquals(currentDate.getDayOfMonth(), instantDate.getDayOfMonth());
    }

    @Test
    public void isValidInstant() {
        // null instant
        assertThrows(NullPointerException.class, () -> LastModifiedInstant.isValidInstant(null));

        // blank instant
        assertFalse(LastModifiedInstant.isValidInstant("")); // empty string
        assertFalse(LastModifiedInstant.isValidInstant(" ")); // spaces only

        // missing parts
        assertFalse(LastModifiedInstant.isValidInstant("-10-10T15:18:35.617617Z")); // missing year
        assertFalse(LastModifiedInstant.isValidInstant("2020-10T15:18:35.617617Z")); // missing month
        assertFalse(LastModifiedInstant.isValidInstant("2020-10T15:18:35.617617Z")); // missing day
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-1015:18:35.617617Z")); // missing T
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10T18:35.617617Z")); // missing hour
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10T15:35.617617Z")); // missing minutes
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10T15:18.617617Z")); // missing seconds
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10T15:18:35.617617")); // missing Z

        // invalid parts
        assertFalse(LastModifiedInstant.isValidInstant("YYYY-10-10T15:18:35.617617Z")); // invalid year
        assertFalse(LastModifiedInstant.isValidInstant("2020-MM-10T15:18:35.617617Z")); // invalid month
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-DDT15:18:35.617617Z")); // invalid day
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10THH:18:35.617617Z")); // invalid hour
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10T15:MM:35.617617Z")); // invalid minute
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10T15:18:SS.617617Z")); // invalid second
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10T15:18:35.nanosecondZ")); // invalid nanoseconds
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10T15:18:35.617617Y")); // invalid zone
        assertFalse(LastModifiedInstant.isValidInstant("2020-10-10Z15:18:35.617617Z")); // invalid separator
        assertFalse(LastModifiedInstant.isValidInstant("2020/10/10T15:18:35.617617Z")); // "/" instead of "-"

        // valid email
        assertTrue(LastModifiedInstant.isValidInstant("2020-10-10T15:18:35.617617Z")); // valid instant
        assertTrue(LastModifiedInstant.isValidInstant("9999-12-31T23:59:59.999999Z")); // largest instant
        assertTrue(LastModifiedInstant.isValidInstant("0000-01-01T00:00:00.000000Z")); // smallest instant
        assertTrue(LastModifiedInstant.isValidInstant("2020-10-10T15:18:35.617Z")); // 3 digits for nano
        assertTrue(LastModifiedInstant.isValidInstant("2020-10-10T15:18:35Z")); // no nano
    }

    @Test
    public void equals() {
        LastModifiedInstant lastModifiedInstant1 = new LastModifiedInstant(VALID_INSTANT_1);
        LastModifiedInstant lastModifiedInstant2 = new LastModifiedInstant(VALID_INSTANT_2);

        // basic equals tests
        basicEqualsTests(lastModifiedInstant1);

        assertTrue(lastModifiedInstant1.equals(new LastModifiedInstant(VALID_INSTANT_1))); // same instant
        assertFalse(lastModifiedInstant1.equals(lastModifiedInstant2)); // different instant
    }

    @Test
    public void hashCode_test() {
        LastModifiedInstant lastModifiedInstant1 = new LastModifiedInstant(VALID_INSTANT_1);
        LastModifiedInstant lastModifiedInstant2 = new LastModifiedInstant(VALID_INSTANT_2);
        assertEquals(lastModifiedInstant1.hashCode(), lastModifiedInstant1.hashCode()); // same object
        assertEquals(lastModifiedInstant1.hashCode(),
                new LastModifiedInstant(VALID_INSTANT_1).hashCode()); // same instant
        assertNotEquals(lastModifiedInstant1.hashCode(), lastModifiedInstant2.hashCode()); // different instant
    }

    @Test
    public void compareTo() {
        LastModifiedInstant lastModifiedInstant1 = new LastModifiedInstant(VALID_INSTANT_1);
        LastModifiedInstant lastModifiedInstant2 = new LastModifiedInstant(VALID_INSTANT_2);

        assertEquals(lastModifiedInstant1.compareTo(lastModifiedInstant2), 1);
        assertEquals(lastModifiedInstant2.compareTo(lastModifiedInstant1), -1);
        assertEquals(lastModifiedInstant1.compareTo(lastModifiedInstant1), 0);
        assertEquals(lastModifiedInstant1.compareTo(new LastModifiedInstant(VALID_INSTANT_1)), 0);
    }

    @Test
    public void toString_test() {
        LastModifiedInstant lastModifiedInstant1 = new LastModifiedInstant(VALID_INSTANT_1);
        assertEquals(VALID_INSTANT_1, lastModifiedInstant1.toString());
    }
}
