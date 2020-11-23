package seedu.tr4cker.model.countdown;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventName(invalidName));
    }

    @Test
    public void testIsValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> EventName.isValidName(null));

        // invalid name
        assertFalse(EventName.isValidName("")); // empty string
        assertFalse(EventName.isValidName(" ")); // spaces only
        assertFalse(EventName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(EventName.isValidName("birbd4e p^rt^y!")); // contains non-alphanumeric characters

        // valid name
        assertTrue(EventName.isValidName("graded assessment")); // alphabets only
        assertTrue(EventName.isValidName("12345")); // numbers only
        assertTrue(EventName.isValidName("midterm 1")); // alphanumeric characters
        assertTrue(EventName.isValidName("Practical Assignment")); // with capital letters
        assertTrue(EventName.isValidName("A Very Long name for my Final Exam")); // long names
    }

    @Test
    public void testEquals() {
        EventName eventName1 = new EventName("HWSGKK");
        EventName eventName2 = new EventName("HWSGKK");
        EventName eventName3 = new EventName("EthanSGKK");
        assertEquals(eventName1, eventName2);
        assertEquals(eventName1, eventName1);
        assertNotEquals(eventName1, eventName3);
    }

    @Test
    public void testHashCode() {
        EventName eventName1 = new EventName("HWSGKK");
        EventName eventName2 = new EventName("HWSGKK");
        assertEquals(eventName1.hashCode(), eventName2.hashCode());
        assertEquals(eventName1.hashCode(), eventName2.toString().hashCode());
    }
}
