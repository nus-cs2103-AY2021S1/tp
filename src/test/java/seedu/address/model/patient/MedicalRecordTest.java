package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class MedicalRecordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalRecord(null));
    }

    @Test
    public void constructor_invalidUrl_throwsIllegalArgumentException() {
        String invalidUrl = "ww.c";
        assertThrows(IllegalArgumentException.class, () -> new MedicalRecord(invalidUrl));
    }

    @Test
    void isValidUrl() {
        // null url
        assertThrows(NullPointerException.class, () -> MedicalRecord.isValidUrl(null));

        // invalid url
        assertFalse(MedicalRecord.isValidUrl("")); // empty string
        assertFalse(MedicalRecord.isValidUrl(" ")); // spaces only
        assertFalse(MedicalRecord.isValidUrl("ww.c")); // domain and top level domain too short

        // valid url
        assertTrue(MedicalRecord.isValidUrl("https://www.validsample.com/01")); // starts with https://
        assertTrue(MedicalRecord.isValidUrl("https://validsample.com/01")); // starts with https:// and without www.
        assertTrue(MedicalRecord.isValidUrl("http://www.validsample.com/01")); // starts with http://
        assertTrue(MedicalRecord.isValidUrl("http://validsample.com/01")); // starts with http:// and without www.
        assertTrue(MedicalRecord.isValidUrl("www.validsample.com/01")); // starts with www.
        assertTrue(MedicalRecord.isValidUrl("validsample.com/01")); // starts with domain name
        assertTrue(MedicalRecord.isValidUrl("validsample.com")); // ends without path
    }
}
