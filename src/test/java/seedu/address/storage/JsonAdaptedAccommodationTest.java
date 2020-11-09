package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedAccommodation.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.typicals.TypicalAccommodations.ALICEHOTEL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;

public class JsonAdaptedAccommodationTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_START_DATE = "20200202";
    private static final String INVALID_END_DATE = "20200203";
    private static final String INVALID_COST = "-23";
    private static final String INVALID_LOCATION = " ";

    private static final String VALID_NAME = ALICEHOTEL.getName().getValue();
    private static final String VALID_START_DATE = ALICEHOTEL.getStartDate().getDate();
    private static final String VALID_END_DATE = ALICEHOTEL.getEndDate().getDate();
    private static final String VALID_COST = ALICEHOTEL.getCost().getValue();
    private static final String VALID_LOCATION = ALICEHOTEL.getLocation().getValue();

    @Test
    public void toModelType_validAccommodationDetails_returnsAccommodation() throws Exception {
        JsonAdaptedAccommodation accommodation = new JsonAdaptedAccommodation(ALICEHOTEL);
        assertEquals(ALICEHOTEL, accommodation.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(INVALID_NAME, VALID_START_DATE, VALID_END_DATE, VALID_COST,
                        VALID_LOCATION);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(null, VALID_START_DATE, VALID_END_DATE, VALID_COST, VALID_LOCATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(VALID_NAME, INVALID_START_DATE, VALID_END_DATE, VALID_COST,
                        VALID_LOCATION);
        String expectedMessage = WanderlustDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(VALID_NAME, null, VALID_END_DATE, VALID_COST, VALID_LOCATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, WanderlustDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(VALID_NAME, VALID_START_DATE, INVALID_END_DATE, VALID_COST,
                        VALID_LOCATION);
        String expectedMessage = WanderlustDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(VALID_NAME, VALID_START_DATE, null, VALID_COST, VALID_LOCATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, WanderlustDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }

    @Test
    public void toModelType_invalidCost_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(VALID_NAME, VALID_START_DATE, VALID_END_DATE, INVALID_COST,
                        VALID_LOCATION);
        String expectedMessage = Cost.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }

    @Test
    public void toModelType_nullCost_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(VALID_NAME, VALID_START_DATE, VALID_END_DATE, null,
                        VALID_LOCATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Cost.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(VALID_NAME, VALID_START_DATE, VALID_END_DATE, VALID_COST,
                        INVALID_LOCATION);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedAccommodation accommodation =
                new JsonAdaptedAccommodation(VALID_NAME, VALID_START_DATE, VALID_END_DATE, VALID_COST, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, accommodation::toModelType);
    }
}
