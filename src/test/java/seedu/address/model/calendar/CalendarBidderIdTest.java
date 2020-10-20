package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CalendarBidderIdTest {

    @Test
    void testToString() {
        CalendarBidderId bidderId = new CalendarBidderId("b1");
        String bidderIdTest = "b1";
        assert (bidderId.bidderId.equals("b1"));
        assertTrue(bidderIdTest.equals(bidderId.toString()));


        CalendarBidderId bidderIdError = new CalendarBidderId("b1");
        String bidderIdDiff = "b2";
        assertFalse(bidderIdDiff.equals(bidderIdError.toString()));
    }

    @Test
    void testEquals() {
        CalendarBidderId bidderId = new CalendarBidderId("b1");
        CalendarBidderId bidderIdTest = new CalendarBidderId("b1");
        assertTrue(bidderIdTest.equals(bidderId));

        CalendarPropertyId propertyIdError = new CalendarPropertyId("b1");
        CalendarPropertyId propertyIdDiff = new CalendarPropertyId("b2");
        assertFalse(propertyIdDiff.equals(propertyIdError));
    }

    @Test
    void testHashCode() {
        CalendarBidderId bidderId = new CalendarBidderId("b1");
        CalendarBidderId bidderIdTest = new CalendarBidderId("b1");
        assertTrue(bidderIdTest.hashCode() == bidderId.hashCode());

        CalendarBidderId bidderIdError = new CalendarBidderId("b1");
        CalendarBidderId bidderIdDiff = new CalendarBidderId("b2");
        assertFalse(bidderIdDiff.hashCode() == bidderIdError.hashCode());
    }
}
