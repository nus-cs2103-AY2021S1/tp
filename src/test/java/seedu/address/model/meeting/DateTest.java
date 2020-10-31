package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    void testToString() {
        Date date = new Date("12 Oct 2012");
        String timeTest = "12 Oct 2012";
        assertTrue(timeTest.equals(date.toString()));

        Date dateError = new Date("14 Oct 2012");
        String timeDiff = "12 Oct 2012";
        assertFalse(timeDiff.equals(dateError.toString()));
    }

    @Test
    void testEquals() {
        Date date = new Date("12 Oct 2012");
        Date dateSecond = new Date("12 Oct 2012");
        assertTrue(date.equals(dateSecond));

        Date dateDiff = new Date("12 Oct 2012");
        Date dateDiffSecond = new Date("14 Oct 2012");
        assertFalse(dateDiff.equals(dateDiffSecond));
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
