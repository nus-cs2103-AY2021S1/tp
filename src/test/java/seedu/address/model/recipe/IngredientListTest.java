package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIngredients.INGREDIENT_1;
import static seedu.address.testutil.TypicalIngredients.INGREDIENT_2;

import org.junit.jupiter.api.Test;

import seedu.address.model.recipe.exceptions.DuplicateIngredientException;
import seedu.address.testutil.TypicalIngredients;

public class IngredientListTest {
    /**
     * Test for exception when duplicate ingredient added.
     */
    @Test
    public void duplicate() {
        IngredientList a = TypicalIngredients.getTypicalIngredientList();
        assertThrows(DuplicateIngredientException.class, () -> a.add(INGREDIENT_1));
    }

    /**
     * Test for contains.
     */
    @Test
    public void contains() {
        IngredientList a = TypicalIngredients.getTypicalIngredientList();
        assertTrue(a.contains(INGREDIENT_1));
        assertTrue(a.contains(INGREDIENT_2));

        a.remove(INGREDIENT_1);
        assertFalse(a.contains(INGREDIENT_1));
    }

    /**
     * Test for equality.
     */
    @Test
    public void equals() {
        IngredientList a = TypicalIngredients.getTypicalIngredientList();
        IngredientList b = new IngredientList();
        b.add(INGREDIENT_1);
        b.add(INGREDIENT_2);
        assertTrue(a.equals(b));

        a = new IngredientList();
        a.add(INGREDIENT_1);
        b = new IngredientList();
        b.add(INGREDIENT_2);
        assertFalse(a.equals(b));

        a.add(INGREDIENT_2);
        assertFalse(a.equals(b));

        a.remove(INGREDIENT_1);
        assertTrue(a.equals(b));
    }
}
