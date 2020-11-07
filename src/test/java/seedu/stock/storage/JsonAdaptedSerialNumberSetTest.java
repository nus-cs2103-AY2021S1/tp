package seedu.stock.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stock.storage.JsonAdaptedSerialNumberSet.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalSerialNumberSets.NTUC;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.model.stock.AccumulatedQuantity;
import seedu.stock.model.stock.Source;

public class JsonAdaptedSerialNumberSetTest {

    private static final String INVALID_SOURCE = "   ";
    private static final String INVALID_ACCUMULATED_QUANTITY = "-1";

    private static final String VALID_SOURCE = NTUC.getSource().value;
    private static final String VALID_ACCUMULATED_QUANTITY = NTUC.getAccumulatedQuantity()
                                                                    .getValue();

    @Test
    public void toModelType_validSerialNumberSet_returnsSerialNumberSet() throws Exception {
        JsonAdaptedSerialNumberSet serialNumberSet = new JsonAdaptedSerialNumberSet(NTUC);
        assertEquals(NTUC, serialNumberSet.toModelType());
    }

    @Test
    public void toModelType_validSerialNumberSetDetails_returnsSerialNumberSet() throws Exception {
        JsonAdaptedSerialNumberSet serialNumberSet =
                new JsonAdaptedSerialNumberSet(VALID_SOURCE, VALID_ACCUMULATED_QUANTITY);
        assertEquals(NTUC, serialNumberSet.toModelType());
    }

    @Test
    public void toModelType_nullSource_throwsIllegalValueException() {
        JsonAdaptedSerialNumberSet serialNumberSet =
                new JsonAdaptedSerialNumberSet(null, VALID_ACCUMULATED_QUANTITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Source.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, serialNumberSet::toModelType);
    }

    @Test
    public void toModelType_nullAccumulatedQuantity_throwsIllegalValueException() {
        JsonAdaptedSerialNumberSet serialNumberSet =
                new JsonAdaptedSerialNumberSet(VALID_SOURCE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                        AccumulatedQuantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, serialNumberSet::toModelType);
    }

    @Test
    public void toModelType_invalidSource_throwsIllegalValueException() {
        JsonAdaptedSerialNumberSet serialNumberSet =
                new JsonAdaptedSerialNumberSet(INVALID_SOURCE, VALID_ACCUMULATED_QUANTITY);
        String expectedMessage = Source.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, serialNumberSet::toModelType);
    }

    @Test
    public void toModelType_invalidAccumulatedQuantity_throwsIllegalValueException() {
        JsonAdaptedSerialNumberSet serialNumberSet =
                new JsonAdaptedSerialNumberSet(VALID_SOURCE, INVALID_ACCUMULATED_QUANTITY);
        String expectedMessage = AccumulatedQuantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, serialNumberSet::toModelType);
    }
}
