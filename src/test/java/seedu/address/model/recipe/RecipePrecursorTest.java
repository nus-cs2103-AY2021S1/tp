package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_ID_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECIPE_QUANTITY_TWO;
import static seedu.address.testutil.TypicalRecipePrecursors.APPLE_PIE_PRECURSOR;
import static seedu.address.testutil.TypicalRecipePrecursors.BANANA_PIE_PRECURSOR;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecipePrecursorBuilder;

public class RecipePrecursorTest {
    /**
     * Test for strict recipe precursor equality, defined as two recipe precursors having the exact
     * same fields.
     */
    @Test
    public void equals() {
        // same values -> returns true
        RecipePrecursor applePieCopy = new RecipePrecursorBuilder(APPLE_PIE_PRECURSOR).build();
        assertTrue(APPLE_PIE_PRECURSOR.equals(applePieCopy));

        // same object -> returns true
        assertTrue(APPLE_PIE_PRECURSOR.equals(APPLE_PIE_PRECURSOR));

        // null -> returns false
        assertFalse(APPLE_PIE_PRECURSOR.equals(null));

        // different type -> returns false
        assertFalse(APPLE_PIE_PRECURSOR.equals(5));

        // different item -> returns false
        assertFalse(APPLE_PIE_PRECURSOR.equals(BANANA_PIE_PRECURSOR));

        // different id -> returns false
        RecipePrecursor editedApplePiePrecursor = new RecipePrecursorBuilder(APPLE_PIE_PRECURSOR)
                .withId(VALID_RECIPE_ID_TWO).build();
        assertFalse(APPLE_PIE_PRECURSOR.equals(editedApplePiePrecursor));

        // different ingredients -> returns false
        List<IngredientPrecursor> ingredientList = new ArrayList<>();
        editedApplePiePrecursor = new RecipePrecursorBuilder(APPLE_PIE_PRECURSOR)
                .withIngredients(ingredientList).build();
        assertFalse(APPLE_PIE_PRECURSOR.equals(editedApplePiePrecursor));

        // different quantity -> returns false
        editedApplePiePrecursor = new RecipePrecursorBuilder(APPLE_PIE_PRECURSOR)
                .withQuantity(VALID_RECIPE_QUANTITY_TWO).build();
        assertFalse(APPLE_PIE_PRECURSOR.equals(editedApplePiePrecursor));

        // different description -> returns false
        editedApplePiePrecursor = new RecipePrecursorBuilder(APPLE_PIE_PRECURSOR)
                .withDescription(VALID_RECIPE_DESC).build();
        assertFalse(APPLE_PIE_PRECURSOR.equals(editedApplePiePrecursor));
    }
}
