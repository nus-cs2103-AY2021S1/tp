package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramAddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramAddress(null));
    }

    @Test
    public void constructor_invalidTelegramAddress_throwsIllegalArgumentException() {
        String invalidTelegramAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramAddress(invalidTelegramAddress));
    }

    @Test
    public void isValidTelegramAddress() {
        // null telegram address
        assertThrows(NullPointerException.class, () -> TelegramAddress.isValidTelegramAddress(null));

        // blank telegram address
        assertFalse(TelegramAddress.isValidTelegramAddress("")); // empty string
        assertFalse(TelegramAddress.isValidTelegramAddress(" ")); // spaces only

        // invalid telegram address
        assertFalse(TelegramAddress.isValidTelegramAddress("jake")); // 4 characters
        assertFalse(TelegramAddress.isValidTelegramAddress("j123456789012345678901234567890ee")); // 33 characters
        assertFalse(TelegramAddress.isValidTelegramAddress("j!kee")); // special characters
        assertFalse(TelegramAddress.isValidTelegramAddress("j@kee")); // special characters
        assertFalse(TelegramAddress.isValidTelegramAddress("j#kee")); // special characters
        assertFalse(TelegramAddress.isValidTelegramAddress("j.kee")); // special characters
        assertFalse(TelegramAddress.isValidTelegramAddress("peter jack")); // spaces in name
        assertFalse(TelegramAddress.isValidTelegramAddress("1jake")); // number ae first letter
        assertFalse(TelegramAddress.isValidTelegramAddress("_jake1")); // underscore as first character
        assertFalse(TelegramAddress.isValidTelegramAddress("jake1_")); // underscore as last character


        // valid telegram address
        assertTrue(TelegramAddress.isValidTelegramAddress("jakee")); // alphabets only
        assertTrue(TelegramAddress.isValidTelegramAddress("a1234")); // numbers only
        assertTrue(TelegramAddress.isValidTelegramAddress("a___b")); //underscores only
        assertTrue(TelegramAddress.isValidTelegramAddress("jake123")); // alphanumeric
        assertTrue(TelegramAddress.isValidTelegramAddress("jak_123")); // mixture of alphanumeric and underscores
        assertTrue(TelegramAddress.isValidTelegramAddress("j___e")); // alphabet as first and last character
        assertTrue(TelegramAddress.isValidTelegramAddress("j___1")); // alphabet as first and number as last character
        assertTrue(TelegramAddress.isValidTelegramAddress("j1234567890123456789012345678901")); // 32 characters
    }
}
