package seedu.stock.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stock.storage.JsonAdaptedStock.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalStocks.BANANA;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;

public class JsonAdaptedStockTest {
    private static final String INVALID_NAME = "@PPLE";
    private static final String INVALID_SOURCE = " ";
    private static final String INVALID_QUANTITY = "-100";
    private static final String INVALID_LOW_QUANTITY = "-1000";
    private static final String INVALID_LOCATION = " ";
    private static final String INVALID_SERIAL_NUMBER = " ";

    private static final String VALID_NAME = BANANA.getName().toString();
    private static final String VALID_SOURCE = BANANA.getSource().toString();
    private static final String VALID_QUANTITY = BANANA.getQuantity().toString();
    private static final String VALID_LOCATION = BANANA.getLocation().toString();
    private static final String VALID_SERIAL_NUMBER = BANANA.getSerialNumber().toString();
    private static final String VALID_LOW_QUANTITY = BANANA.getQuantity().lowQuantity;
    private static final List<String> VALID_NOTE = BANANA.getNotesValues();
    private static final boolean VALID_IS_BOOKMARKED = BANANA.getIsBookmarked();

    @Test
    public void toModelType_validStockDetails_returnsStock() throws Exception {
        JsonAdaptedStock stock = new JsonAdaptedStock(BANANA);
        assertEquals(BANANA, stock.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(INVALID_NAME, VALID_SERIAL_NUMBER, VALID_SOURCE,
                        VALID_QUANTITY, VALID_LOW_QUANTITY, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStock stock = new JsonAdaptedStock(null, VALID_SERIAL_NUMBER, VALID_SOURCE,
                VALID_QUANTITY, VALID_LOW_QUANTITY, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_invalidSerialNumber_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, INVALID_SERIAL_NUMBER, VALID_SOURCE,
                        VALID_QUANTITY, VALID_LOW_QUANTITY, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = SerialNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_nullSerialNumber_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, null, VALID_SOURCE,
                        VALID_QUANTITY, VALID_LOW_QUANTITY, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, SerialNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_invalidSource_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, VALID_SERIAL_NUMBER, INVALID_SOURCE,
                        VALID_QUANTITY, VALID_LOW_QUANTITY, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = Source.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_nullSource_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, VALID_SERIAL_NUMBER, null,
                        VALID_QUANTITY, VALID_LOW_QUANTITY, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Source.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, VALID_SERIAL_NUMBER, VALID_SOURCE,
                        INVALID_QUANTITY, VALID_LOW_QUANTITY, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, VALID_SERIAL_NUMBER, VALID_SOURCE,
                        null, VALID_LOW_QUANTITY, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_invalidLowQuantity_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, VALID_SERIAL_NUMBER, VALID_SOURCE,
                        VALID_QUANTITY, INVALID_LOW_QUANTITY, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = Quantity.LOW_QUANTITY_MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_nullLowQuantity_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, VALID_SERIAL_NUMBER, VALID_SOURCE,
                        VALID_QUANTITY, null, VALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "lowQuantity");
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);

    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, VALID_SERIAL_NUMBER, VALID_SOURCE,
                        VALID_QUANTITY, VALID_LOW_QUANTITY, INVALID_LOCATION, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedStock stock =
                new JsonAdaptedStock(VALID_NAME, VALID_SERIAL_NUMBER, VALID_SOURCE,
                        VALID_QUANTITY, VALID_LOW_QUANTITY, null, VALID_NOTE, VALID_IS_BOOKMARKED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stock::toModelType);
    }

}
