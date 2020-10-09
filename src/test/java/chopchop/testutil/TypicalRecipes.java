package chopchop.testutil;

import chopchop.model.attributes.Step;
import chopchop.model.recipe.Recipe;
import chopchop.model.recipe.RecipeBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static chopchop.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_APRICOT_SALAD;
import static chopchop.logic.commands.CommandTestUtil.VALID_RECIPE_NAME_BANANA_SALAD;
import static chopchop.testutil.TypicalIngredients.APRICOT;
import static chopchop.testutil.TypicalIngredients.BANANA;

public class TypicalRecipes {

    public static final Step STEP_APRICOT_SALAD = new Step(
            "Cut the apricot, add salad dressing, and mix well in a bowl.");
    public static final Step STEP_BANANA_SALAD = new Step(
            "Cut the banana, add salad dressing, and mix well in a bowl.");

    public static final Recipe APRICOT_SALAD = new RecipeBuilder().withName(VALID_RECIPE_NAME_APRICOT_SALAD)
            .withIngredients(new ArrayList<>(Arrays.asList(APRICOT)))
            .withSteps(new ArrayList<>(Arrays.asList(STEP_APRICOT_SALAD)))
            .build();

    public static final Recipe BANANA_SALAD = new RecipeBuilder().withName(VALID_RECIPE_NAME_BANANA_SALAD)
            .withIngredients(new ArrayList<>(Arrays.asList(BANANA)))
            .withSteps(new ArrayList<>(Arrays.asList(STEP_BANANA_SALAD)))
            .build();

    /**
     * Returns an {@code IngredientBook} with all the typical ingredients.
     */
    public static RecipeBook getTypicalRecipeBook() {
        RecipeBook rb = new RecipeBook();
        for (Recipe recipe : getTypicalRecipes()) {
            rb.addRecipe(recipe);
        }
        return rb;
    }

    public static List<Recipe> getTypicalRecipes() {
        return new ArrayList<>(Arrays.asList(APRICOT_SALAD, BANANA_SALAD));
    }

}
