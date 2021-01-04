package seedu.homerce.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ServiceCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ServiceCode(null));
    }

    @Test
    public void isValidServiceCode() {
        // null service code
        assertThrows(NullPointerException.class, () -> ServiceCode.isValidServiceCode(null));

        // invalid service code
        assertFalse(ServiceCode.isValidServiceCode("SC0000")); // 4 digits
        assertFalse(ServiceCode.isValidServiceCode("SC1999")); // Greater than 999
        assertFalse(ServiceCode.isValidServiceCode("SC00")); // Two digits
        assertFalse(ServiceCode.isValidServiceCode("ST000")); // Does not begin with "SC"

        // valid service code
        assertTrue(ServiceCode.isValidServiceCode("SC000"));
        assertTrue(ServiceCode.isValidServiceCode("SC999"));
        assertTrue(ServiceCode.isValidServiceCode("SC010"));
        assertTrue(ServiceCode.isValidServiceCode("sC000")); //
        assertTrue(ServiceCode.isValidServiceCode("sc000")); // lowercase SC
    }

    @Test
    public void equals() {
        ServiceCode serviceCode = new ServiceCode("SC000");
        ServiceCode serviceCodeCopy = new ServiceCode("SC000");
        ServiceCode serviceCode1 = new ServiceCode("SC001");

        // Same object -> returns true
        assertTrue(serviceCode.equals(serviceCode));

        // Same service code -> returns true
        assertTrue(serviceCode.equals(serviceCodeCopy));

        // null -> returns false
        assertFalse(serviceCode.equals(null));

        // different type -> returns false
        assertFalse(serviceCode.equals(5));

        // different service code -> returns false
        assertFalse(serviceCode.equals(serviceCode1));
    }

    @Test
    public void toUiString() {
        ServiceCode serviceCode = new ServiceCode("SC000");
        assertEquals(serviceCode.toString(), "SC000");
    }
}
