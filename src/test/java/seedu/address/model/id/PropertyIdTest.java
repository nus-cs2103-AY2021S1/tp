package seedu.address.model.id;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class PropertyIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PropertyId(null));
    }

    @Test
    public void constructor_invalidPropertyId_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PropertyId(""));
    }

    @Test
    void increment() {
        PropertyId propertyId = new PropertyId(1);
        List<Integer> expected = Arrays.asList(2, 3, 4, 5, 6);
        for (int value : expected) {
            propertyId = propertyId.increment();
            assertEquals(new PropertyId(value), propertyId);
        }
    }

    @Test
    public void isValidId() {
        // null property id
        assertThrows(NullPointerException.class, () -> PropertyId.isValidId(null));

        // invalid property ids
        assertFalse(PropertyId.isValidId("B1"));
        assertFalse(PropertyId.isValidId("p1"));
        assertFalse(PropertyId.isValidId("random string"));
        assertFalse(PropertyId.isValidId("1"));
        assertFalse(PropertyId.isValidId("P-1"));
        assertFalse(PropertyId.isValidId("P0.1"));

        // valid property ids
        assertTrue(PropertyId.isValidId("P1"));
        assertTrue(PropertyId.isValidId("P0"));
        assertTrue(PropertyId.isValidId("P" + Integer.MAX_VALUE));
    }

    @Test
    public void equals() {

        // same object
        PropertyId propertyId = new PropertyId(1);
        assertTrue(propertyId.equals(propertyId));

        // same prefix and id
        PropertyId other = new PropertyId(1);
        assertTrue(propertyId.equals(other));

        // different id (not possible to have different prefix)
        other = new PropertyId(2);
        assertFalse(propertyId.equals(other));

        // different type
        assertFalse(propertyId.equals(new BidderId(1)));

    }
}