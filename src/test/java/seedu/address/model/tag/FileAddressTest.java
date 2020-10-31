package seedu.address.model.tag;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class FileAddressTest {

    @Test
    public void constructor_validSpecialCharacter_success() {
        char[] validChars = new char[]{'(', ')', '!', '@', '#', '$', '%', '^', '&', ';', '\'', '=', '+',
            '-', '_', ',', '.', '`', '~', '[', ']', '{', '}'};
        String basePathWindows = "C:\\User\\";
        String basePathLinux = "/usr/";

        // Exhaustively check for all possible valid path
        for (char c : validChars) {
            new FileAddress(basePathWindows + c);
            new FileAddress(basePathLinux + c);
        }
    }

    @Test
    public void constructor_invalidSpecialCharacter_throwIllegalArgumentException() {
        char [] invalidChars = new char[]{':', '*', '"', '<', '>', '|', '?'};
        String basePathWindows = "C:\\User\\";
        String basePathLinux = "/usr/";

        // Exhaustively check for all possible valid path
        for (char c : invalidChars) {
            assertThrows(IllegalArgumentException.class, () -> new FileAddress(basePathWindows + c));
            assertThrows(IllegalArgumentException.class, () -> new FileAddress(basePathLinux + c));
        }
    }

}
