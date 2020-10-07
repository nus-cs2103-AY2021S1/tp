package chopchop.model.recipe;

import static chopchop.testutil.TypicalIngredients.BANANA;
import static chopchop.testutil.TypicalRecipes.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import chopchop.testutil.IngredientBuilder;
import chopchop.testutil.RecipeBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipeTest {

    @Test
    public void equals() {
        // same values -> returns true
        Recipe apricotSaladCopy = new RecipeBuilder(APRICOT_SALAD).build();
        assertTrue(APRICOT_SALAD.equals(apricotSaladCopy));

        // same object -> returns true
        assertTrue(APRICOT_SALAD.equals(APRICOT_SALAD));

        // null -> returns false
        assertFalse(APRICOT_SALAD.equals(null));

        // different type -> returns false
        assertFalse(APRICOT_SALAD.equals(new IngredientBuilder().build()));

        // different recipe -> returns false
        assertFalse(APRICOT_SALAD.equals(BANANA_SALAD));

        // different name -> returns false
        Recipe editedApricotSalad = new RecipeBuilder(APRICOT_SALAD).withName(VALID_NAME_BOB).build();
        assertFalse(APRICOT_SALAD.equals(editedApricotSalad));

        // different ingredients -> returns false. Different recipes can be of the same name but different ingredients
        editedApricotSalad = new RecipeBuilder(APRICOT_SALAD)
                .withIngredients(new ArrayList<>(Arrays.asList(BANANA))).build();
        assertFalse(APRICOT_SALAD.equals(editedApricotSalad));

        // different steps -> returns false. Different recipes can be of the same name but different steps
        editedApricotSalad = new RecipeBuilder(APRICOT_SALAD)
                .withSteps(new ArrayList<>(Arrays.asList(STEP_BANANA_SALAD))).build();
        assertFalse(APRICOT_SALAD.equals(editedApricotSalad));
    }
}
