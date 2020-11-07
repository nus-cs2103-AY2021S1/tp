package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

        // Exhaustively check for all possible invalid path
        for (char c : invalidChars) {
            assertThrows(IllegalArgumentException.class, () -> new FileAddress(basePathWindows + c));
            assertThrows(IllegalArgumentException.class, () -> new FileAddress(basePathLinux + c));
        }
    }

    @Test
    public void equals() {
        String validFileAddress = "C:\\d\\Univ files";
        FileAddress firstFileAddress = new FileAddress(validFileAddress);
        FileAddress secondFileAddress = new FileAddress("C:\\Videos");

        //same object -> returns true
        assertTrue(firstFileAddress.equals(firstFileAddress));

        //same values -> returns true
        FileAddress newFileAddress = new FileAddress(validFileAddress);
        assertTrue(firstFileAddress.equals(newFileAddress));

        //different type -> returns false
        assertFalse(firstFileAddress.equals(1));

        //null -> returns false
        assertFalse(firstFileAddress.equals(null));

        //different values -> returns false
        assertFalse(firstFileAddress.equals(secondFileAddress));
    }

}
