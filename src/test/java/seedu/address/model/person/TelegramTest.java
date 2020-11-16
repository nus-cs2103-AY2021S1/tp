package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        // null address
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid addresses
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("Joe")); // 4 characters
        assertFalse(Telegram.isValidTelegram("000011112222333355556666777788889")); // 33 characters

        // valid addresses
        assertTrue(Telegram.isValidTelegram("Joeyy")); // 5 characters
        assertTrue(Telegram.isValidTelegram("00001111222233335555666677778888")); // 32 characters
        assertTrue(Telegram.isValidTelegram("Jackson123")); // With numbers
        assertTrue(Telegram.isValidTelegram("Jackson_123")); // With underscores
    }
}
