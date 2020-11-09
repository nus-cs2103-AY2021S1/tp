package seedu.zookeep.model.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.zookeep.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidId() {
        // null id number
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid id numbers
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only
        assertFalse(Id.isValidId("-123")); // negative numbers
        assertFalse(Id.isValidId("91")); // less than 3 numbers
        assertFalse(Id.isValidId("99")); // just below lower boundary
        assertFalse(Id.isValidId("1000000")); // just above upper boundary
        assertFalse(Id.isValidId("93121534")); // more than 6 numbers
        assertFalse(Id.isValidId("id")); // non-numeric
        assertFalse(Id.isValidId("9011p041")); // alphabets within digits
        assertFalse(Id.isValidId("9312 1534")); // spaces within digits

        // valid id numbers
        assertTrue(Id.isValidId("100")); // lower boundary exactly
        assertTrue(Id.isValidId("911")); // exactly 3 numbers
        assertTrue(Id.isValidId("654321")); // long id numbers (6 numbers)
        assertTrue(Id.isValidId("999999")); // upper boundary exactly
    }

    @Test
    public void hashcode() {
        Id testId = new Id("123");

        // same values -> returns same hashcode
        assertEquals(testId.hashCode(), new Id("123").hashCode());

        // different values -> returns different hashcode
        assertNotEquals(testId.hashCode(), new Id("456").hashCode());
    }
}
