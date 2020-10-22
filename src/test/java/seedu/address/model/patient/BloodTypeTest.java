package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BloodTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BloodType(null));
    }

    @Test
    public void constructor_invalidBloodType_throwsIllegalArgumentException() {
        String invalidBloodType = "";
        assertThrows(IllegalArgumentException.class, () -> new BloodType(invalidBloodType));
    }

    @Test
    public void isValidBloodType() {
        // null bloodType
        assertThrows(NullPointerException.class, () -> BloodType.isValidBloodType(null));

        // blank bloodType
        assertFalse(BloodType.isValidBloodType("")); // empty string
        assertFalse(BloodType.isValidBloodType(" ")); // spaces only

        // missing parts
        assertFalse(BloodType.isValidBloodType("+")); // missing ABO system
        assertFalse(BloodType.isValidBloodType("A")); // missing RHD system

        // invalid parts
        assertFalse(BloodType.isValidBloodType(" A+")); // leading space
        assertFalse(BloodType.isValidBloodType("A+ ")); // trailing space
        assertFalse(BloodType.isValidBloodType("A +")); // space between ABO system and RHD system
        assertFalse(BloodType.isValidBloodType("C+")); // invalid alphabet in ABO system
        assertFalse(BloodType.isValidBloodType("1+")); // digit in ABO system
        assertFalse(BloodType.isValidBloodType("!+")); // special character in ABO system
        assertFalse(BloodType.isValidBloodType("AA")); // two invalid alphabets in ABO system
        assertFalse(BloodType.isValidBloodType("A1")); // digit in RHD system
        assertFalse(BloodType.isValidBloodType("A@")); // invalid special character in RHD system
        assertFalse(BloodType.isValidBloodType("A!@")); // two special characters in RHD system

        // valid bloodType
        assertTrue(BloodType.isValidBloodType("A+")); // A RhD positive
        assertTrue(BloodType.isValidBloodType("B+")); // B RhD positive
        assertTrue(BloodType.isValidBloodType("O+")); // O RhD positive
        assertTrue(BloodType.isValidBloodType("AB+")); // AB RhD positive
        assertTrue(BloodType.isValidBloodType("A-")); // A RhD negative
        assertTrue(BloodType.isValidBloodType("B-")); // B RhD negative
        assertTrue(BloodType.isValidBloodType("O-")); // O RhD negative
        assertTrue(BloodType.isValidBloodType("AB-")); // AB RhD negative
        assertTrue(BloodType.isValidBloodType("a+")); // lower-case alphabet in ABO system
        assertTrue(BloodType.isValidBloodType("N/A")); // N/A is a valid placeholder
    }
}
