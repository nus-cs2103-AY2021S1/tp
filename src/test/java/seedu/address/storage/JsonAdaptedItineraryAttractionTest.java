package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedItineraryAttraction.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttractions.JURONG_BIRD_PARK;
import static seedu.address.testutil.TypicalItineraryAttractions.NIGHT_SAFARI;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.itinerary.ItineraryTime;

public class JsonAdaptedItineraryAttractionTest {
    private static final String INVALID_TIME = "10";

    private static final JsonAdaptedAttraction VALID_ATTRACTION = new JsonAdaptedAttraction(JURONG_BIRD_PARK);
    private static final String VALID_START_TIME = NIGHT_SAFARI.getStartTime().toString();
    private static final String VALID_END_TIME = NIGHT_SAFARI.getEndTime().toString();

    @Test
    public void toModelType_validItineraryAttractionDetails_returnsItineraryAttraction() throws Exception {
        JsonAdaptedItineraryAttraction itineraryAttraction = new JsonAdaptedItineraryAttraction(NIGHT_SAFARI);
        assertEquals(NIGHT_SAFARI, itineraryAttraction.toModelType());
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedItineraryAttraction itineraryAttraction =
                new JsonAdaptedItineraryAttraction(VALID_ATTRACTION, INVALID_TIME, VALID_END_TIME);
        String expectedMessage = ItineraryTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itineraryAttraction::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedItineraryAttraction itineraryAttraction =
                new JsonAdaptedItineraryAttraction(VALID_ATTRACTION, null, VALID_END_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItineraryTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itineraryAttraction::toModelType);
    }

    @Test
    public void toModelType_invalidEndTime_throwsIllegalValueException() {
        JsonAdaptedItineraryAttraction itineraryAttraction =
                new JsonAdaptedItineraryAttraction(VALID_ATTRACTION, VALID_START_TIME, INVALID_TIME);
        String expectedMessage = ItineraryTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itineraryAttraction::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedItineraryAttraction itineraryAttraction =
                new JsonAdaptedItineraryAttraction(VALID_ATTRACTION, VALID_START_TIME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItineraryTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itineraryAttraction::toModelType);
    }
}
