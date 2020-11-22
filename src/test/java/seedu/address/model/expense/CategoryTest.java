package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    public void constructor_invalidCategory_throwsIllegalArgumentException() {
        String invalidCategory = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidCategory));
    }

    @Test
    public void isValidCategory() {
        // null name
        assertThrows(NullPointerException.class, () -> Category.isValidCategory(null));

        // invalid name
        assertFalse(Category.isValidCategory("")); // empty string
        assertFalse(Category.isValidCategory(" ")); // spaces only
        assertFalse(Category.isValidCategory("^")); // only non-alphanumeric characters
        assertFalse(Category.isValidCategory("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Category.isValidCategory("peter jack")); // alphabets only
        assertTrue(Category.isValidCategory("12345")); // numbers only
        assertTrue(Category.isValidCategory("peter the 2nd")); // alphanumeric characters
        assertTrue(Category.isValidCategory("Capital Tan")); // with capital letters
        assertTrue(Category.isValidCategory("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Category.isValidCategory("FoodBeverage"));
        assertTrue(Category.isValidCategory("FOODBEVERAGE")); // all capital letters
        assertTrue(Category.isValidCategory("entertainment"));
        assertTrue(Category.isValidCategory("ENTERTAINMENT"));
    }
}
