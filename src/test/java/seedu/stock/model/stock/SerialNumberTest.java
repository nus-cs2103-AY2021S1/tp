package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SerialNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SerialNumber(null));
    }

    @Test
    public void constructor_invalidSerialNumber_throwsIllegalArgumentException() {
        String invalidSerialNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new SerialNumber(invalidSerialNumber));
    }

    @Test
    public void generatedSerialNumberIsFixed() {
        SerialNumber defaultSerialNumber = SerialNumber.generateDefaultSerialNumber();
        SerialNumber expectedDefaultSerialNumber = new SerialNumber("00");
        assertEquals(defaultSerialNumber, expectedDefaultSerialNumber);
    }

    @Test
    public void isValidSerialNumber() {
        // null serial number
        assertThrows(NullPointerException.class, () -> SerialNumber.isValidSerialNumber(null));

        // invalid serial number
        assertFalse(SerialNumber.isValidSerialNumber("")); // empty string
        assertFalse(SerialNumber.isValidSerialNumber("   ")); // spaces only
        assertFalse(SerialNumber.isValidSerialNumber("Ntuc")); // no numbers
        assertFalse(SerialNumber.isValidSerialNumber("5")); // only 1 digit

        // valid serial number
        assertTrue(SerialNumber.isValidSerialNumber("Ntuc1"));
        assertTrue(SerialNumber.isValidSerialNumber("11")); //at least 2 digits
        assertTrue(SerialNumber.isValidSerialNumber(
                "AAAAAAAAAAAAAAAAAAAAAAAAA100000")); // long serial number
    }
}
