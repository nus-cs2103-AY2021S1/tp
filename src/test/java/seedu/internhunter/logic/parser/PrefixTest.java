package seedu.internhunter.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrefixTest {

    private Prefix prefix;
    private Prefix nullPrefix;

    @BeforeEach
    public void setUp() {
        prefix = new Prefix("s/");
        nullPrefix = new Prefix(null);
    }

    @Test
    public void getPrefix_equals() {
        assertEquals("s/", prefix.getPrefix());
    }

    @Test
    public void toString_equals() {
        assertEquals("s/", prefix.toString());
    }

    @Test
    public void hashCode_equals() {
        // same object -> same hash code
        assertEquals(prefix.hashCode(), prefix.hashCode());

        // same value -> same hash code
        assertEquals(new Prefix("s/").hashCode(), prefix.hashCode());

        // different value -> different hash code
        assertNotEquals(prefix.hashCode(), new Prefix("t/").hashCode());

        // null -> 0
        assertEquals(0, nullPrefix.hashCode());
    }

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(prefix.equals(prefix));

        // same value -> return true
        assertTrue(prefix.equals(new Prefix("s/")));

        // null -> return false
        assertFalse(prefix.equals(null));

        // different types -> return false
        assertFalse(prefix.equals(0.5f));

        // different value -> return false
        assertFalse(prefix.equals(new Prefix("t/")));
    }
}
