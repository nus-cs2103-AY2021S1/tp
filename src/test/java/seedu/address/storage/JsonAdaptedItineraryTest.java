package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedItinerary.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItineraries.PARIS_TRIP;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.Budget;
import seedu.address.model.itinerary.ItineraryDate;

public class JsonAdaptedItineraryTest {
    private static final String INVALID_NAME = "Tr!p";
    private static final String INVALID_DATE = "23-13-2019";
    private static final String INVALID_BUDGET = "-1";

    private static final String VALID_NAME = PARIS_TRIP.getName().toString();
    private static final String VALID_DESCRIPTION = PARIS_TRIP.getDescription().toString();
    private static final String VALID_START_DATE = PARIS_TRIP.getStartDate().toString();
    private static final String VALID_END_DATE = PARIS_TRIP.getEndDate().toString();
    private static final String VALID_BUDGET = PARIS_TRIP.getBudget().value;
    private static final List<JsonAdaptedDay> VALID_DAYS = PARIS_TRIP.getDays().stream()
            .map(JsonAdaptedDay::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validItineraryDetails_returnsItinerary() throws Exception {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(PARIS_TRIP);
        assertEquals(PARIS_TRIP, itinerary.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(INVALID_NAME, VALID_DESCRIPTION, VALID_START_DATE,
                       VALID_END_DATE, VALID_BUDGET, VALID_DAYS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(null, VALID_DESCRIPTION,
                VALID_START_DATE, VALID_END_DATE, VALID_BUDGET, VALID_DAYS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(VALID_NAME, null,
                VALID_START_DATE, VALID_END_DATE, VALID_BUDGET, VALID_DAYS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(VALID_NAME, VALID_DESCRIPTION, INVALID_DATE,
                        VALID_END_DATE, VALID_BUDGET, VALID_DAYS);
        String expectedMessage = ItineraryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(VALID_NAME, VALID_DESCRIPTION,
                null, VALID_END_DATE, VALID_BUDGET, VALID_DAYS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItineraryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(VALID_NAME, VALID_DESCRIPTION, VALID_START_DATE, INVALID_DATE,
                        VALID_BUDGET, VALID_DAYS);
        String expectedMessage = ItineraryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(VALID_NAME, VALID_DESCRIPTION,
                VALID_START_DATE, null, VALID_BUDGET, VALID_DAYS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ItineraryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_invalidBudget_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary =
                new JsonAdaptedItinerary(VALID_NAME, VALID_DESCRIPTION, VALID_START_DATE, VALID_END_DATE,
                        INVALID_BUDGET, VALID_DAYS);
        String expectedMessage = Budget.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }

    @Test
    public void toModelType_nullBudget_throwsIllegalValueException() {
        JsonAdaptedItinerary itinerary = new JsonAdaptedItinerary(VALID_NAME, VALID_DESCRIPTION,
                VALID_START_DATE, VALID_END_DATE, null, VALID_DAYS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Budget.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itinerary::toModelType);
    }
}
