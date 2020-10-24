package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TimeTest {

    @Test
    void testToString() {
        Time time = new Time("12 Oct 2012");
        String timeTest = "12 Oct 2012";
        assertTrue(timeTest.equals(time.toString()));

        Time timeError = new Time("14 Oct 2012");
        String timeDiff = "12 Oct 2012";
        assertFalse(timeDiff.equals(timeError.toString()));
    }

    @Test
    void testEquals() {
        Time time = new Time("12 Oct 2012");
        Time timeSecond = new Time("12 Oct 2012");
        assertTrue(time.equals(timeSecond));

        Time timeDiff = new Time("12 Oct 2012");
        Time timeDiffSecond = new Time("14 Oct 2012");
        assertFalse(timeDiff.equals(timeDiffSecond));
    }

    @Test
    void testHashCode() {
        Venue venue = new Venue("bedok");
        Venue venueSecond = new Venue("bedok");
        assertTrue(venue.hashCode() == venueSecond.hashCode());

        Venue venueDiff = new Venue("bedok");
        Venue venueDiffSecond = new Venue("tampines");
        assertFalse(venueDiff.hashCode() == venueDiffSecond.hashCode());
    }
}
