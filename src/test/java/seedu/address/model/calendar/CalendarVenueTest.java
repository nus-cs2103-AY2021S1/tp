package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CalendarVenueTest {

    @Test
    void testToString() {
        CalendarVenue venue = new CalendarVenue("Bedok");
        String venueTest = "Bedok";
        assertTrue(venueTest.equals(venue.toString()));

        CalendarVenue venueError = new CalendarVenue("Bedok");
        String venueDiff = "Tampines";
        assertFalse(venueDiff.equals(venueError.toString()));
    }

    @Test
    void testEquals() {
        CalendarVenue venue = new CalendarVenue("bedok");
        CalendarVenue venueSecond = new CalendarVenue("bedok");
        assertTrue(venue.equals(venueSecond));

        CalendarVenue venueDiff = new CalendarVenue("bedok");
        CalendarVenue venueDiffSecond = new CalendarVenue("tampines");
        assertFalse(venueDiff.equals(venueDiffSecond));
    }

    @Test
    void testHashCode() {
    }
}
