package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItineraryAttractionBuilder;

class ItineraryAttractionDayCounterTest {

    private final ItineraryAttraction itineraryAttraction = new ItineraryAttractionBuilder().build();
    private final ItineraryAttractionDayCounter sampleDay1 =
            new ItineraryAttractionDayCounter(itineraryAttraction, 1);
    private final ItineraryAttractionDayCounter sampleDay2 =
            new ItineraryAttractionDayCounter(itineraryAttraction, 2);

    @Test
    void getDay() {
        assertEquals(sampleDay1.getDay(), 1);
        assertEquals(sampleDay2.getDay(), 2);
    }

    @Test
    void testEquals() {
        assertEquals(sampleDay1, sampleDay1);
        assertNotEquals(sampleDay1, sampleDay2);
    }

    @Test
    void testHashCode() {
        assertEquals(sampleDay1.hashCode(), sampleDay1.hashCode());
        assertNotEquals(sampleDay1.hashCode(), sampleDay2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(sampleDay1.toString(), "Day 1");
        assertEquals(sampleDay2.toString(), "Day 2");

        assertNotEquals(sampleDay1.toString(), "Day ");
    }
}
