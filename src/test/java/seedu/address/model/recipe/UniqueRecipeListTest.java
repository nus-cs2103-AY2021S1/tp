package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRecipes.APPLE_PIE;
import static seedu.address.testutil.TypicalRecipes.BANANA_PIE;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.recipe.exceptions.DuplicateRecipeException;
import seedu.address.model.recipe.exceptions.RecipeNotFoundException;

public class UniqueRecipeListTest {
    @Test
    public void contains() {
        UniqueRecipeList uList = new UniqueRecipeList();
        uList.add(APPLE_PIE);
        assertTrue(uList.contains(APPLE_PIE));
        assertFalse(uList.contains(BANANA_PIE));
    }

    @Test
    public void add() {
        UniqueRecipeList uList = new UniqueRecipeList();
        uList.add(APPLE_PIE);
        assertEquals(1, uList.asUnmodifiableObservableList().size());
    }

    @Test
    public void setRecipe() {
        UniqueRecipeList uList = new UniqueRecipeList();
        uList.add(APPLE_PIE);
        uList.setRecipe(APPLE_PIE, BANANA_PIE);
        assertTrue(uList.contains(BANANA_PIE));
        assertFalse(uList.contains(APPLE_PIE));
    }

    /**
     * Tests that setRecipe throws an exception when recipe is not found.
     */
    @Test
    public void setRecipe_throwsItemNotFoundException() {
        UniqueRecipeList uList = new UniqueRecipeList();
        uList.add(APPLE_PIE);
        assertThrows(RecipeNotFoundException.class, () -> uList.setRecipe(BANANA_PIE, APPLE_PIE));
    }

    /**
     * Tests that setRecipe throws an exception when input is a duplicate recipe.
     */
    @Test
    public void setRecipe_throwsDuplicateItemException() {
        UniqueRecipeList uList = new UniqueRecipeList();
        uList.add(APPLE_PIE);
        uList.add(BANANA_PIE);
        assertThrows(DuplicateRecipeException.class, () -> uList.setRecipe(BANANA_PIE, APPLE_PIE));
    }

    @Test
    public void remove() {
        UniqueRecipeList uList = new UniqueRecipeList();
        uList.add(APPLE_PIE);
        uList.remove(APPLE_PIE);
        assertFalse(uList.contains(APPLE_PIE));
    }

    /**
     * Tests that exception is thrown when attempting to
     * remove recipe which does not exist.
     */
    @Test
    public void remove_throwsRecipeNotFoundException() {
        UniqueRecipeList uList = new UniqueRecipeList();
        uList.add(APPLE_PIE);
        assertThrows(RecipeNotFoundException.class, ()-> uList.remove(BANANA_PIE));
    }

    /**
     * Tests replacing a UniqueRecipeList with another.
     */
    @Test
    public void setItems() {
        UniqueRecipeList uList = new UniqueRecipeList();
        UniqueRecipeList uList2 = new UniqueRecipeList();
        uList2.add(APPLE_PIE);
        uList.setRecipes(uList2);
        assertTrue(uList.contains(APPLE_PIE));
    }

    /**
     * Tests replacing a UniqueRecipeList with a list.
     */
    @Test
    public void setRecipes_success() {
        UniqueRecipeList uList = new UniqueRecipeList();
        ArrayList<Recipe> arr = new ArrayList<>();
        arr.add(APPLE_PIE);
        uList.setRecipes(arr);
        assertTrue(uList.contains(APPLE_PIE));
    }

    @Test
    public void setRecipes_throwsDuplicateRecipeException() {
        UniqueRecipeList uList = new UniqueRecipeList();
        ArrayList<Recipe> arr = new ArrayList<>();
        arr.add(APPLE_PIE);
        arr.add(APPLE_PIE);
        assertThrows(DuplicateRecipeException.class, () -> uList.setRecipes(arr));
    }

    @Test
    public void equals() {
        UniqueRecipeList uList = new UniqueRecipeList();
        uList.add(APPLE_PIE);
        // same object -> returns true
        assertTrue(uList.equals(uList));

        // null -> returns false
        assertFalse(uList.equals(null));

        // same internal lists
        UniqueRecipeList uList2 = new UniqueRecipeList();
        uList2.add(APPLE_PIE);
        assertTrue(uList.equals(uList2));

        // different internal lists
        UniqueRecipeList uList3 = new UniqueRecipeList();
        uList3.add(BANANA_PIE);
        assertFalse(uList.equals(uList3));
    }
}
