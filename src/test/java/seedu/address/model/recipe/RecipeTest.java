package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_ID_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_QUANTITY_TWO;
import static seedu.address.testutil.TypicalRecipes.APPLE_PIE;
import static seedu.address.testutil.TypicalRecipes.BANANA_PIE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipeBuilder;

public class RecipeTest {
    /**
     * Tests for recipe equality, defined as two recipes having the same id.
     */
    @Test
    public void isSameRecipe() {
        // same object -> returns true
        assertTrue(APPLE_PIE.isSameRecipe(APPLE_PIE));

        // null -> returns false
        assertFalse(APPLE_PIE.isSameRecipe(null));

        // different quantity and description -> returns true
        Recipe editedApplePie = new RecipeBuilder(APPLE_PIE)
                .withDescription(VALID_RECIPE_DESC)
                .withQuantity(VALID_RECIPE_QUANTITY_TWO)
                .build();
        assertTrue(APPLE_PIE.isSameRecipe(editedApplePie));

        // different id -> returns false
        editedApplePie = new RecipeBuilder(APPLE_PIE).withId(VALID_RECIPE_ID_TWO).build();
        assertFalse(APPLE_PIE.isSameRecipe(editedApplePie));
    }

    /**
     * Test for strict item equality, defined as two items having the exact
     * same fields.
     */
    @Test
    public void equals() {
        // same values -> returns true
        Recipe applePieCopy = new RecipeBuilder(APPLE_PIE).build();
        assertTrue(APPLE_PIE.equals(applePieCopy));

        // same object -> returns true
        assertTrue(APPLE_PIE.equals(APPLE_PIE));

        // null -> returns false
        assertFalse(APPLE_PIE.equals(null));

        // different type -> returns false
        assertFalse(APPLE_PIE.equals(5));

        // different item -> returns false
        assertFalse(APPLE_PIE.equals(BANANA_PIE));

        // different id -> returns false
        Recipe editedApplePie = new RecipeBuilder(APPLE_PIE).withId(VALID_RECIPE_ID_TWO).build();
        assertFalse(APPLE_PIE.equals(editedApplePie));

        // different ingredients -> returns false
        IngredientList ingredientList = new IngredientList();
        editedApplePie = new RecipeBuilder(APPLE_PIE).withIngredients(ingredientList).build();
        assertFalse(APPLE_PIE.equals(editedApplePie));

        // different quantity -> returns false
        editedApplePie = new RecipeBuilder(APPLE_PIE).withQuantity(VALID_RECIPE_QUANTITY_TWO).build();
        assertFalse(APPLE_PIE.equals(editedApplePie));

        // different description -> returns false
        editedApplePie = new RecipeBuilder(APPLE_PIE).withDescription(VALID_RECIPE_DESC).build();
        assertFalse(APPLE_PIE.equals(editedApplePie));
    }
}
