package seedu.address.commons.core.category;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;



public class CategoryTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_invalidAmount_throwsIllegalArgumentException() {
        String invalidCategory = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidCategory));
    }

    @Test
    public void isValidAmount_success() {
        // null category
        assertThrows(NullPointerException.class, () -> Category.isValidCategory(null));

        // blank category
        assertFalse(Category.isValidCategory("")); // empty string
        assertFalse(Category.isValidCategory(" ")); // spaces only

        // invalid category
        assertFalse(Category.isValidCategory("hello"));
        assertFalse(Category.isValidCategory("expenses"));
        assertFalse(Category.isValidCategory("expense1"));
        assertFalse(Category.isValidCategory("reve nue"));
        assertFalse(Category.isValidCategory("revenuee"));


        // valid category
        assertTrue(Category.isValidCategory("E"));
        assertTrue(Category.isValidCategory("e"));
        assertTrue(Category.isValidCategory("R"));
        assertTrue(Category.isValidCategory("r"));
        assertTrue(Category.isValidCategory("Expense"));
        assertTrue(Category.isValidCategory("expense"));
        assertTrue(Category.isValidCategory("ExPeNsE"));
        assertTrue(Category.isValidCategory("Revenue"));
        assertTrue(Category.isValidCategory("revenue"));
        assertTrue(Category.isValidCategory("rEvEnUe"));
    }

}
