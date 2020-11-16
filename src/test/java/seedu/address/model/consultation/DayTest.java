package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class DayTest {

    private static final String VALID_DATE_STRING = "13/01/2020";

    @Test
    public void fromDateString() {
        // null attendance
        assertThrows(NullPointerException.class, () -> Day.fromDateString(null));

        // invalid attendances
        assertThrows(IllegalArgumentException.class, () -> Day.fromDateString(""));
        assertThrows(IllegalArgumentException.class, () -> Day.fromDateString("hello"));

        // valid attendances
        assertEquals(Day.fromDateString(VALID_DATE_STRING).toString(), VALID_DATE_STRING);
    }

    @Test
    public void equals() {
        // Equality to null
        assertNotEquals(Day.fromDateString(VALID_DATE_STRING), null);

        // Equality to objects of other classes / parent classes
        assertNotEquals(Day.fromDateString(VALID_DATE_STRING), new Object());
    }

    @Test
    public void testEquals_isReflexive() {
        Day x = Day.fromDateString(VALID_DATE_STRING);
        assertEquals(x, x);
        assertEquals(x.hashCode(), x.hashCode());
    }

    @Test
    public void testEquals_isSymmetric() {
        Day x = Day.fromDateString(VALID_DATE_STRING);
        Day y = Day.fromDateString(VALID_DATE_STRING);
        assertEquals(x, y);
        assertEquals(y, x);
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    public void testEquals_isTransitive() {
        Day x = Day.fromDateString(VALID_DATE_STRING);
        Day y = Day.fromDateString(VALID_DATE_STRING);
        Day z = Day.fromDateString(VALID_DATE_STRING);
        assertEquals(x, y);
        assertEquals(y, z);
        assertEquals(x, z);
        assertEquals(x.hashCode(), z.hashCode());
    }

    @Test
    public void testCompareTo() {
        // earlier date
        assertEquals(-1,
            Day.fromDateString("27/03/1998").compareTo(
                Day.fromDateString("28/03/1998")
            ));

        // later date
        assertEquals(1,
            Day.fromDateString("29/03/1998").compareTo(
                Day.fromDateString("28/03/1998")
            ));

        // same date
        assertEquals(0,
            Day.fromDateString("29/03/1998").compareTo(
                Day.fromDateString("29/03/1998")
            ));
    }
}
