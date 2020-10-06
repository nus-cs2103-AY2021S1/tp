package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.INGREDIENT_1;

import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null, null));
    }

    @Test
    public void constructor_invalidIngredient_throwsAssertionException() {
        Integer x = 1;
        Integer y = -1;

        assertThrows(AssertionError.class, () -> new Ingredient(x, y));
    }

    @Test
    public void isItem() {
        int sameItemId = 1;
        int differentItemId = 2;

        // Ensure that a known item id and equivilent int value returns true
        assertTrue(INGREDIENT_1.isItem(sameItemId));
        // Known item id and non equivilent int value returns false.
        assertFalse(INGREDIENT_1.isItem(differentItemId));
    }
}
