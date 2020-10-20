package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

class OptionTest {

    @Test
    public void equals() {
        Option option = new Option("1");
        Option optionCopy = new Option("1");
        Option optionDifferent = new Option("2");
        assertTrue(option.equals(optionCopy));

        // same object -> returns true
        assertTrue(option.equals(option));

        // null -> returns false
        assertFalse(option.equals(null));

        // different type -> returns false
        assertFalse(option.equals(5));

        // different option -> returns false
        assertFalse(option.equals(optionDifferent));
    }

    @Test
    public void testHashCode() {
        String value = "1";
        Option option = new Option(value);
        assertEquals(option.hashCode(), Objects.hash(value));
    }

    @Test
    public void testToString() {
        String value = "1";
        Option option = new Option(value);
        assertEquals(option.toString(), "Option: " + value);
    }

}
