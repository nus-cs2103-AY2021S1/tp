package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CalendarTimeTest {

    @Test
    void testToString() {
        CalendarTime time = new CalendarTime("12 Oct 2012");
        String timeTest = "12 Oct 2012";
        assertTrue(timeTest.equals(time.toString()));

        CalendarTime timeError = new CalendarTime("14 Oct 2012");
        String timeDiff = "12 Oct 2012";
        assertFalse(timeDiff.equals(timeError.toString()));
    }

    @Test
    void testEquals() {
        CalendarTime time = new CalendarTime("12 Oct 2012");
        CalendarTime timeSecond = new CalendarTime("12 Oct 2012");
        assertTrue(time.equals(timeSecond));

        CalendarTime timeDiff = new CalendarTime("12 Oct 2012");
        CalendarTime timeDiffSecond = new CalendarTime("14 Oct 2012");
        assertFalse(timeDiff.equals(timeDiffSecond));
    }

    @Test
    void testHashCode() {
        CalendarVenue venue = new CalendarVenue("bedok");
        CalendarVenue venueSecond = new CalendarVenue("bedok");
        assertTrue(venue.hashCode() == venueSecond.hashCode());

        CalendarVenue venueDiff = new CalendarVenue("bedok");
        CalendarVenue venueDiffSecond = new CalendarVenue("tampines");
        assertFalse(venueDiff.hashCode() == venueDiffSecond.hashCode());
    }
}
