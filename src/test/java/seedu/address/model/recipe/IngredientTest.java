package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        // null ingredients number
        assertThrows(NullPointerException.class, () -> IngredientString.isValidIngredient(null));

        // invalid ingredients numbers
        assertFalse(IngredientString.isValidIngredient("")); // empty string
        //        assertFalse(IngredientString.isValidIngredient("91")); // less than 3 numbers
        //        assertFalse(IngredientString.isValidIngredient("ingredients")); // non-numeric
        //        assertFalse(IngredientString.isValidIngredient("9011p041")); // alphabets within digits
        assertTrue(IngredientString.isValidIngredient("9312 1534")); // spaces within digits

        // valid ingredients numbers
        assertTrue(IngredientString.isValidIngredient("911")); // exactly 3 numbers
        assertTrue(IngredientString.isValidIngredient("93121534"));
        assertTrue(IngredientString.isValidIngredient("124293842033123")); // long ingredients numbers
    }
}
