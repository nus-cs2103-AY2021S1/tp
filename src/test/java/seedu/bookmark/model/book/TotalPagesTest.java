package seedu.bookmark.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TotalPagesTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TotalPages(null));
    }

    @Test
    public void constructor_nonNumericalTotalPages_throwsIllegalArgumentException() {
        String invalidTotalPages = "total pages";
        assertThrows(IllegalArgumentException.class, () -> new TotalPages(invalidTotalPages));
    }

    @Test
    public void constructor_negativeTotalPages_throwsIllegalArgumentException() {
        String invalidTotalPages = "-1";
        assertThrows(IllegalArgumentException.class, () -> new TotalPages(invalidTotalPages));
    }

    @Test
    public void constructor_tooManyDigits_throwsIllegalArgumentException() {
        StringBuilder tooLong = new StringBuilder();
        // tooLong is one character too long
        tooLong.append("1".repeat(TotalPages.MAX_TOTAL_PAGES_LENGTH + 2));

        assertThrows(IllegalArgumentException.class, () -> new TotalPages(tooLong.toString()));
    }

    @Test
    public void isValidTotalPages() {
        // null total pages
        assertThrows(NullPointerException.class, () -> TotalPages.isValidTotalPages(null));

        // total pages = 0
        assertFalse(TotalPages.isValidTotalPages("0")); // 0 is invalid

        // blank total pages
        assertFalse(TotalPages.isValidTotalPages("")); // empty string
        assertFalse(TotalPages.isValidTotalPages(" ")); // spaces only

        // non-numerical total pages
        assertFalse(TotalPages.isValidTotalPages("abc"));

        // negative value total pages
        assertFalse(TotalPages.isValidTotalPages("-123"));

        // too many digits total pages
        String tooLong = "1".repeat(TotalPages.MAX_TOTAL_PAGES_LENGTH + 1);

        assertThrows(IllegalArgumentException.class, () -> new TotalPages(tooLong));

        // valid total pages
        assertTrue(TotalPages.isValidTotalPages("123"));
        assertTrue(TotalPages.isValidTotalPages("001234")); // leading zeroes
    }
}
