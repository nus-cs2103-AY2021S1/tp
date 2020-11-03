package seedu.address.model.event;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.EventUtil.VALID_NAME;

public class EventNameTest {

    @Test
    public void constructor() {
        EventName eventName = new EventName("Test");
        assertEquals(eventName, VALID_NAME);
    }

    @Test
    public void constructorNull_value_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new EventName(null));
    }

    @Test
    public void constructorInvalid_name() {
        assertThrows(IllegalArgumentException.class, () -> new EventName("@Test"));
    }

    @Test
    public void getName() {
        assertTrue(VALID_NAME.getName().equals("Test"));
    }

    @Test
    public void validName_success() {
        String name = "CS2100 Assignment";
        assertTrue(EventName.isValidName(name));
    }

    @Test
    public void invalidName_failure() {
        String name = "CS2100 Assignment!";
        assertFalse(EventName.isValidName(name));
    }

    @Test
    public void toStringTest() {
        assertTrue(VALID_NAME.getName().toString().equals("Test"));
    }

    @Test
    public void isEquals() {
        EventName event = new EventName("Test");
        assertEquals(event, VALID_NAME);
    }

    @Test
    public void isNotEquals() {
        EventName event = new EventName("test");
        assertNotEquals(event, VALID_NAME);
    }
}
