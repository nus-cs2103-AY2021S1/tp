package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;

public class IngredientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null));
    }

    /*@Test
    public void constructor_invalidIngredient_throwsIllegalArgumentException() {
        String invalidIngredient = "";
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(invalidIngredient));
    }*/

    @Test
    public void isValidIngredient() {
        // invalid ingredients numbers
        assertFalse(ParserUtil.isValidIngredient("")); // empty string
        assertTrue(ParserUtil.isValidIngredient("9312 1534")); // spaces within digits

        // valid ingredients numbers
        assertTrue(ParserUtil.isValidIngredient("911")); // exactly 3 numbers
        assertTrue(ParserUtil.isValidIngredient("93121534"));
        assertTrue(ParserUtil.isValidIngredient("124293842033123")); // long ingredients numbers
    }
}
