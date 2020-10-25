package seedu.address.testutil;

import static seedu.address.testutil.TypicalIngredients.getTypicalSecondIngredientList;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.APPLE_PIE_ITEM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.RecipeList;
import seedu.address.model.recipe.Recipe;

public class TypicalRecipes {

    public static final Recipe APPLE_PIE = new RecipeBuilder().withId(1)
            .withDescription("Apple-y!")
            .withQuantity("1")
            .withProduct(APPLE_PIE_ITEM)
            .build(); //apple pie
    public static final Recipe BANANA_PIE = new RecipeBuilder().withId(2)
            .withDescription("Banana-y!") // banana pie
            .withQuantity("1").build();
    public static final Recipe FOUND_APPLE = new RecipeBuilder().withId(3)
            .withDescription("Make Apple.inc")
            .withIngredients(getTypicalSecondIngredientList())
            .withProduct(APPLE)
            .withQuantity("1")
            .build();

    private TypicalRecipes() {} // prevents instantiation

    /**
     * Returns an {@code RecipeList} with all the typical recipes.
     */
    public static RecipeList getTypicalRecipeList() {
        RecipeList recipeList = new RecipeList();
        for (Recipe recipe : getTypicalRecipes()) {
            recipeList.addRecipe(new RecipeBuilder(recipe).build());
        }
        return recipeList;
    }

    public static List<Recipe> getTypicalRecipes() {
        return new ArrayList<>(Arrays.asList(APPLE_PIE, BANANA_PIE, FOUND_APPLE));
    }
}
