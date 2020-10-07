package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;

public class IngredientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null, ""));
    }

    /*@Test
    public void constructor_invalidIngredient_throwsIllegalArgumentException() {
        String invalidIngredient = "";
        assertThrows(IllegalArgumentException.class, () -> new Ingredient(invalidIngredient));
    }*/

    @Test
    public void isValidIngredient() {
        // invalid ingredient names
        assertFalse(ParserUtil.isValidIngredient(new Ingredient("", "2 cups"))); // empty name
        assertFalse(ParserUtil.isValidIngredient(new Ingredient("fishc@ke", "2 cups"))); // empty name
        assertFalse(ParserUtil.isValidIngredient(new Ingredient("fishcake!", "2 cups"))); // empty name

        // valid ingredient names
        assertTrue(ParserUtil.isValidIngredient(new Ingredient("white cabbage", "2 cups"))); // space within name
        assertTrue(ParserUtil.isValidIngredient(new Ingredient("pea", "2 cups")));
        assertTrue(ParserUtil.isValidIngredient(new Ingredient("93121534", "2 cups")));
        assertTrue(ParserUtil.isValidIngredient(new Ingredient("loooooooooooongbean", "2 cups"))); // long

        // valid ingredient quantities
        assertTrue(ParserUtil.isValidIngredient(new Ingredient("cabbage", "2 cups"))); // space within quantity
        assertTrue(ParserUtil.isValidIngredient(new Ingredient("cabbage", "2cups"))); // no space within quantity
        assertTrue(ParserUtil.isValidIngredient(new Ingredient("cabbage", "pinch"))); // no numbers in quantity
        assertTrue(ParserUtil.isValidIngredient(new Ingredient("cabbage", ""))); // no quantity

        // invalid ingredient quantities
        assertFalse(ParserUtil.isValidIngredient(new Ingredient("fishcake", "2 c@ps"))); // empty name
        assertFalse(ParserUtil.isValidIngredient(new Ingredient("fishcake", "2 cups!"))); // empty name

    }
}
