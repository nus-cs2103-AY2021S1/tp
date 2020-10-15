package seedu.taskmaster.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.taskmaster.testutil.Assert.assertThrows;

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
        // null telegram account
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid telegram accounts
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("91")); // less than 5 numbers
        assertFalse(Telegram.isValidTelegram("@")); // invalid character

        // valid telegram accounts
        assertTrue(Telegram.isValidTelegram("91112")); // exactly 5 characters
        assertTrue(Telegram.isValidTelegram("asd_aws"));
        assertTrue(Telegram.isValidTelegram("12429asdfadsf3842033123")); // long account
    }
}
