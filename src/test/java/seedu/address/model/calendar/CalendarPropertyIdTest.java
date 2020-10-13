package seedu.address.model.calendar;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class CalendarPropertyIdTest {

    @Test
    void testToString() {
        CalendarPropertyId propertyId = new CalendarPropertyId("p1");
        String propertyIdTest = "p1";
        assertTrue(propertyIdTest.equals(propertyId.toString()));

        CalendarPropertyId propertyIdError = new CalendarPropertyId("p1");
        String propertyIdDiff = "p2";
        assertFalse(propertyIdDiff.equals(propertyIdError.toString()));
    }

    @Test
    void testEquals() {
        CalendarPropertyId propertyId = new CalendarPropertyId("p1");
        CalendarPropertyId propertyIdTest = new CalendarPropertyId("p1");
        assertTrue(propertyIdTest.equals(propertyId));

        CalendarPropertyId propertyIdError = new CalendarPropertyId("p1");
        CalendarPropertyId propertyIdDiff = new CalendarPropertyId("p2");
        assertFalse(propertyIdDiff.equals(propertyIdError));
    }

    @Test
    void testHashCode() {
        CalendarPropertyId propertyId = new CalendarPropertyId("p1");
        CalendarPropertyId propertyIdTest = new CalendarPropertyId("p1");
        assertTrue(propertyIdTest.hashCode() == propertyId.hashCode());

        CalendarPropertyId propertyIdError = new CalendarPropertyId("p1");
        CalendarPropertyId propertyIdDiff = new CalendarPropertyId("p2");
        assertFalse(propertyIdDiff.hashCode() == propertyIdError.hashCode());
    }
}
