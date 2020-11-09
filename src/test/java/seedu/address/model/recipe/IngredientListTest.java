package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIngredients.INGREDIENT_1;
import static seedu.address.testutil.TypicalIngredients.INGREDIENT_2;
import static seedu.address.testutil.TypicalIngredients.getTypicalIngredientList;
import static seedu.address.testutil.TypicalIngredients.getTypicalSecondIngredientList;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.recipe.exceptions.DuplicateIngredientException;
import seedu.address.testutil.TypicalIngredients;

public class IngredientListTest {
    /**
     * Tests for exception when duplicate ingredient added.
     */
    @Test
    public void duplicate() {
        IngredientList a = TypicalIngredients.getTypicalIngredientList();
        // duplicate ingredient added -> throw exception
        assertThrows(DuplicateIngredientException.class, () -> a.add(INGREDIENT_1));
    }

    /**
     * Test for contains.
     */
    @Test
    public void contains() {
        IngredientList a = TypicalIngredients.getTypicalIngredientList();
        // list contains ingredient -> return true
        assertTrue(a.contains(INGREDIENT_1));
        assertTrue(a.contains(INGREDIENT_2));

        a.remove(INGREDIENT_1);
        // list does not contain ingredient -> return false
        assertFalse(a.contains(INGREDIENT_1));
    }

    /**
     * Test setItems.
     */
    @Test
    public void setItems() {
        IngredientList base = TypicalIngredients.getTypicalIngredientList();
        base.setItems(getTypicalSecondIngredientList());

        // replaced ingredient list -> true
        assertEquals(getTypicalSecondIngredientList(), base);

        // replace with list with duplicates -> throw exception
        List<Ingredient> duplicateIngredients = new ArrayList<>();
        duplicateIngredients.add(INGREDIENT_1);
        duplicateIngredients.add(INGREDIENT_1);

        assertThrows(DuplicateIngredientException.class, () -> base.setItems(duplicateIngredients));

        // replace with list -> true
        List<Ingredient> newIngredients = new ArrayList<>();
        newIngredients.add(INGREDIENT_1);
        newIngredients.add(INGREDIENT_2);
        base.setItems(newIngredients);

        assertEquals(base, getTypicalIngredientList());
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
        // same ingredients -> return true
        assertTrue(a.equals(b));

        a = new IngredientList();
        a.add(INGREDIENT_1);
        b = new IngredientList();
        b.add(INGREDIENT_2);
        // different ingredients -> return false
        assertFalse(a.equals(b));

        // one list strict subset of the other -> return false
        a.add(INGREDIENT_2);
        assertFalse(a.equals(b));

        // same ingredients -> return true
        a.remove(INGREDIENT_1);
        assertTrue(a.equals(b));
    }
}
