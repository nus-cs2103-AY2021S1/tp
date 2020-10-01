package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.RecipeList;
import seedu.address.model.recipe.Recipe;

public class TypicalRecipes {

    public static final Recipe APPLE_PIE = new RecipeBuilder().withId(1)
            .withDescription("Apple-y!")
            .withQuantity("1").build();
    public static final Recipe BANANA_PIE = new RecipeBuilder().withId(2)
            .withDescription("Banana-y!")
            .withQuantity("1").build();

    private TypicalRecipes() {} // prevents instantiation

    /**
     * Returns an {@code RecipeList} with all the typical recipes.
     */
    public static RecipeList getTypicalRecipeList() {
        RecipeList ab = new RecipeList();
        for (Recipe recipe : getTypicalRecipes()) {
            ab.addRecipe(recipe);
        }
        return ab;
    }

    public static List<Recipe> getTypicalRecipes() {
        return new ArrayList<>(Arrays.asList(APPLE_PIE, BANANA_PIE));
    }
}
