package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalStocks.APPLE;

import org.junit.jupiter.api.Test;

public class SerialNumberSetTest {

    @Test
    public void constructor_bothNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SerialNumberSet(null, null));
    }

    @Test
    public void constructor_sourceNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SerialNumberSet(null, new AccumulatedQuantity("1")));
    }

    @Test
    public void constructor_accumulatedQuantityNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SerialNumberSet(APPLE.getSource(), null));
    }

    @Test
    public void sameSourceDifferentAccumulatedQuantity_returnsFalse_forEquals() {
        Source source = APPLE.getSource();
        AccumulatedQuantity accumulatedQuantity = new AccumulatedQuantity("1");
        AccumulatedQuantity differentAccumulatedQuantity = new AccumulatedQuantity("2");

        SerialNumberSet serialNumberSet = new SerialNumberSet(source, accumulatedQuantity);
        SerialNumberSet differentSerialNumberSet = new SerialNumberSet(source, differentAccumulatedQuantity);
        assertFalse(serialNumberSet.equals(differentSerialNumberSet));
    }

    @Test
    public void sameSourceDifferentAccumulatedQuantity_returnsFalse_forIsSameSerialNumberSet() {
        Source source = APPLE.getSource();
        AccumulatedQuantity accumulatedQuantity = new AccumulatedQuantity("1");
        AccumulatedQuantity differentAccumulatedQuantity = new AccumulatedQuantity("2");

        SerialNumberSet serialNumberSet = new SerialNumberSet(source, accumulatedQuantity);
        SerialNumberSet differentSerialNumberSet = new SerialNumberSet(source, differentAccumulatedQuantity);
        assertFalse(serialNumberSet.isSameSerialNumberSet(differentSerialNumberSet));
    }

    @Test
    public void sameSourceDifferentAccumulatedQuantity_returnsTrue_forIsSameSerialNumberSetSource() {
        Source source = APPLE.getSource();
        AccumulatedQuantity accumulatedQuantity = new AccumulatedQuantity("1");
        AccumulatedQuantity differentAccumulatedQuantity = new AccumulatedQuantity("2");

        SerialNumberSet serialNumberSet = new SerialNumberSet(source, accumulatedQuantity);
        SerialNumberSet differentSerialNumberSet = new SerialNumberSet(source, differentAccumulatedQuantity);
        assertTrue(serialNumberSet.isSameSerialNumberSetSource(differentSerialNumberSet));
    }

    @Test
    public void sameSourceSameAccumulatedQuantity_returnsTrue_anyEqualsMethod() {
        Source source = APPLE.getSource();
        AccumulatedQuantity accumulatedQuantity = new AccumulatedQuantity("1");

        SerialNumberSet serialNumberSet = new SerialNumberSet(source, accumulatedQuantity);
        SerialNumberSet serialNumberSetCopy = new SerialNumberSet(source, accumulatedQuantity);

        assertTrue(serialNumberSet.isSameSerialNumberSet(serialNumberSetCopy));
        assertTrue(serialNumberSet.isSameSerialNumberSet(serialNumberSetCopy));
        assertTrue(serialNumberSet.isSameSerialNumberSetSource(serialNumberSetCopy));
    }

    @Test
    public void correctlyIncrementedSerialNumberSet() {
        Source source = APPLE.getSource();
        AccumulatedQuantity accumulatedQuantity = new AccumulatedQuantity("1");
        AccumulatedQuantity incrementedAccumulatedQuantity = new AccumulatedQuantity("2");

        SerialNumberSet serialNumberSet = new SerialNumberSet(source, accumulatedQuantity);
        SerialNumberSet expectedSerialNumberSet = new SerialNumberSet(source, incrementedAccumulatedQuantity);
        SerialNumberSet incrementedSerialNumberSet = serialNumberSet.getNewIncrementedSerialNumberSet();

        assertNotEquals(serialNumberSet, expectedSerialNumberSet);
        assertEquals(expectedSerialNumberSet, incrementedSerialNumberSet);
    }

    @Test
    public void isValidSerialNumberSet() {
        // null serial number
        assertThrows(NullPointerException.class, () -> SerialNumberSet.isValidSerialNumberSet(null, null));

        Source validSource = APPLE.getSource();
        AccumulatedQuantity validAccumulatedQuantity = new AccumulatedQuantity("1");

        // valid serial number set
        assertTrue(SerialNumberSet.isValidSerialNumberSet(validSource, validAccumulatedQuantity));
    }
}
