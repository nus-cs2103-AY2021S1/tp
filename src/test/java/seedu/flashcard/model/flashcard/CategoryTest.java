package seedu.flashcard.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_invalidCategoryName_throwsIllegalArgumentException() {
        String invalidCategoryName = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invalidCategoryName));
    }

    @Test
    public void isValidCategoryName() {
        // 50 character category
        assertTrue(Category.isValidCategory("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        // 50 character category with space
        assertTrue(Category.isValidCategory("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaa"));
        // 51 characters long
        assertFalse(Category.isValidCategory("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa."));
        // null category name
        assertThrows(NullPointerException.class, () -> Category.isValidCategory(null));
    }

}
