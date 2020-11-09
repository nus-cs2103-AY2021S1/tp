package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class VenueTest {

    @Test
    void testToString() {
        Venue venue = new Venue("Bedok");
        String venueTest = "Bedok";
        assertTrue(venueTest.equals(venue.toString()));

        Venue venueError = new Venue("Bedok");
        String venueDiff = "Tampines";
        assertFalse(venueDiff.equals(venueError.toString()));
    }

    @Test
    void testEquals() {
        Venue venue = new Venue("bedok");
        Venue venueSecond = new Venue("bedok");
        assertTrue(venue.equals(venueSecond));

        Venue venueDiff = new Venue("bedok");
        Venue venueDiffSecond = new Venue("tampines");
        assertFalse(venueDiff.equals(venueDiffSecond));
    }

    @Test
    void testHashCode() {
    }
}
