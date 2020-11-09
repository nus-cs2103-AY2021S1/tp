package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItineraryAttractionBuilder;

class ItineraryAttractionIndexCounterTest {

    private final ItineraryAttraction itineraryAttraction = new ItineraryAttractionBuilder().build();
    private final ItineraryAttraction editedItineraryAttraction = new ItineraryAttractionBuilder()
            .withAddress("Address is different").build();
    private final ItineraryAttractionIndexCounter sampleIndex1 =
            new ItineraryAttractionIndexCounter(itineraryAttraction, 1);
    private final ItineraryAttractionIndexCounter sampleIndex2 =
            new ItineraryAttractionIndexCounter(itineraryAttraction, 2);
    private final ItineraryAttractionIndexCounter editedIndex1 =
            new ItineraryAttractionIndexCounter(editedItineraryAttraction, 1);

    @Test
    void getItineraryAttraction() {
        assertEquals(sampleIndex1.getItineraryAttraction(), itineraryAttraction);
        assertEquals(sampleIndex2.getItineraryAttraction(), itineraryAttraction);
        assertEquals(editedIndex1.getItineraryAttraction(), editedItineraryAttraction);

        // test getItineraryAttraction() for ItineraryAttraction
        assertEquals(itineraryAttraction.getItineraryAttraction(), null);
    }

    @Test
    void getIndex() {
        assertEquals(sampleIndex1.getIndex(), 1);
        assertEquals(sampleIndex2.getIndex(), 2);
        assertEquals(editedIndex1.getIndex(), 1);

        // test getIndex() for ItineraryAttraction
        assertEquals(itineraryAttraction.getIndex(), 0);
    }

    @Test
    void testEquals() {
        assertEquals(sampleIndex1, sampleIndex1);
        assertNotEquals(sampleIndex1, editedIndex1);
    }

    @Test
    void testToString() {
        assertEquals(sampleIndex1.toString(), "1");
        assertEquals(sampleIndex2.toString(), "2");
        assertEquals(editedIndex1.toString(), "1");
    }
}
