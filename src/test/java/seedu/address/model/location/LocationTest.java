package seedu.address.model.location;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalLocations.DENVER;
import static seedu.address.testutil.TypicalLocations.NEW_YORK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LocationBuilder;

public class LocationTest {
    /**
     * Tests for location equality, defined as two locations having the same name.
     */
    @Test
    public void isSameLocation() {
        // same object -> returns true
        assertTrue(NEW_YORK.isSameLocation(NEW_YORK));

        // null -> returns false
        assertFalse(NEW_YORK.isSameLocation(null));

        // different name -> returns false
        Location editedNewYork = new LocationBuilder(NEW_YORK).build();
        assertFalse(DENVER.isSameLocation(editedNewYork));
    }

    /**
     * Test for strict location equality, defined as two location having the exact
     * same fields.
     */
    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(NEW_YORK.isSameLocation(NEW_YORK));

        // null -> returns false
        assertFalse(NEW_YORK.isSameLocation(null));

        // null -> returns false
        assertFalse(NEW_YORK.equals(null));

        // different type -> returns false
        assertFalse(NEW_YORK.equals(5));

        // different item -> returns false
        assertFalse(NEW_YORK.equals(DENVER));

        // different name -> returns false
        Location editedNewYork = new LocationBuilder(NEW_YORK).build();
        assertFalse(DENVER.isSameLocation(editedNewYork));
    }
}
