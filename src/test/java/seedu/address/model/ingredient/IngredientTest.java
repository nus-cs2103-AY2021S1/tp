package seedu.address.model.ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IngredientTest {

    private static final Ingredient BLACK_TEA = new Ingredient(new IngredientName("Black Tea"));
    private static final Ingredient BLACK_TEA_VALID_AMOUNT = new Ingredient(new IngredientName(
            "Black Tea"), new Amount("90"));
    private static final Ingredient BOBA_VALID_AMOUNT = new Ingredient(new IngredientName(
            "Boba"), new Amount("20"));
    private static final Ingredient BOBA_AMOUNT_IN_SHORTAGE = new Ingredient(new IngredientName(
            "Boba"), new Amount("19"));

    @Test
    public void isSameIngredient() {
        // same object -> returns true
        assertTrue(BLACK_TEA.isSameIngredient(BLACK_TEA));

        // null -> returns false
        assertFalse(BLACK_TEA.isSameIngredient(null));

        // different amount -> returns false
        assertFalse(BLACK_TEA.isSameIngredient(BLACK_TEA_VALID_AMOUNT));

        // different name -> returns false
        Ingredient editedBlackTea = new Ingredient(new IngredientName("Boba"));
        assertFalse(BLACK_TEA.isSameIngredient(editedBlackTea));

    }

    @Test
    public void testGetAmount() {
        assertEquals(BLACK_TEA_VALID_AMOUNT.getAmount(), new Amount("90"));
    }

    @Test
    public void testIsSolidIngredient() {
        assertFalse(BLACK_TEA_VALID_AMOUNT.isSolidIngredient());
    }

    @Test
    public void testIsIngredientInShortage() {
        assertFalse(BOBA_VALID_AMOUNT.isIngredientInShortage());
    }

    @Test
    public void testIsSolidIngredientInShortage() {
        assertFalse(BOBA_VALID_AMOUNT.isSolidIngredientInShortage());
    }

    @Test
    public void testIsLiquidIngredientInShortage() {
        assertFalse(BLACK_TEA_VALID_AMOUNT.isLiquidIngredientInShortage());
    }

    @Test
    public void testAmountNeededToReachRestockLevel() {
        assertEquals(BOBA_AMOUNT_IN_SHORTAGE.amountNeededToReachRestockLevel(), "1");
    }

    @Test
    public void testEquals() {
        // same values -> returns true
        Ingredient oolongCopy = new Ingredient(new IngredientName("Black Tea"));
        assertTrue(BLACK_TEA.equals(oolongCopy));

        // same object -> returns true
        assertTrue(BLACK_TEA.equals(BLACK_TEA));

        // null -> returns false
        assertFalse(BLACK_TEA.equals(null));

        // different type -> returns false
        assertFalse(BLACK_TEA.equals(5));

        // different ingredient name -> returns false
        assertFalse(BLACK_TEA.equals(new Ingredient(new IngredientName("Boba"))));

        // different amount -> returns false
        assertFalse(BLACK_TEA.equals(BLACK_TEA_VALID_AMOUNT));

    }
}
