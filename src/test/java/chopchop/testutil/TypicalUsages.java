package chopchop.testutil;

import java.time.LocalDateTime;
import java.util.Arrays;
import chopchop.model.UsageList;
import chopchop.model.attributes.units.Mass;
import chopchop.model.usage.IngredientUsage;
import chopchop.model.usage.RecipeUsage;

public class TypicalUsages {
    /*
    Objects are named this way. <RECIPE/INGREDIENT>_<NAME BY ALPHA ORDER>_<DATE, A BEING THE EARLIEST>
     */
    public static final RecipeUsage RECIPE_A_A =
        new RecipeUsage("A", LocalDateTime.of(1,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_A_B =
        new RecipeUsage("A", LocalDateTime.of(200,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_A_C =
        new RecipeUsage("A", LocalDateTime.of(2019,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_A_D =
        new RecipeUsage("A", LocalDateTime.of(2020,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_A_E =
        new RecipeUsage("A", LocalDateTime.of(2021,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_A_F =
        new RecipeUsage("A", LocalDateTime.of(2050,  1, 1, 1, 1));

    public static final RecipeUsage RECIPE_B_A =
        new RecipeUsage("B", LocalDateTime.of(1,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_B_B =
        new RecipeUsage("B", LocalDateTime.of(200,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_B_C =
        new RecipeUsage("B", LocalDateTime.of(2019,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_B_D =
        new RecipeUsage("B", LocalDateTime.of(2020,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_B_E =
        new RecipeUsage("B", LocalDateTime.of(2021,  1, 1, 1, 1));
    public static final RecipeUsage RECIPE_B_F =
        new RecipeUsage("B", LocalDateTime.of(2050,  1, 1, 1, 1));

    public static final RecipeUsage RECIPE_C_A =
        new RecipeUsage("C", LocalDateTime.of(1,  1, 1, 1, 1));

    public static final IngredientUsage INGREDIENT_A_A =
        new IngredientUsage("A", LocalDateTime.of(1,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_A_B =
        new IngredientUsage("A", LocalDateTime.of(200,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_A_C =
        new IngredientUsage("A", LocalDateTime.of(2019,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_A_D =
        new IngredientUsage("A", LocalDateTime.of(2020,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_A_E =
        new IngredientUsage("A", LocalDateTime.of(2021,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_A_F =
        new IngredientUsage("A", LocalDateTime.of(2050,  1, 1, 1, 1),
            Mass.grams(1));

    public static final IngredientUsage INGREDIENT_B_A =
        new IngredientUsage("B", LocalDateTime.of(1,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_B_B =
        new IngredientUsage("B", LocalDateTime.of(200,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_B_C =
        new IngredientUsage("B", LocalDateTime.of(2019,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_B_D =
        new IngredientUsage("B", LocalDateTime.of(2020,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_B_E =
        new IngredientUsage("B", LocalDateTime.of(2021,  1, 1, 1, 1),
            Mass.grams(1));
    public static final IngredientUsage INGREDIENT_B_F =
        new IngredientUsage("B", LocalDateTime.of(2050,  1, 1, 1, 1),
            Mass.grams(1));

    /**
     * Returns a UsageList with its usages in Chronological order. Both A and B recipes are in it.
     */
    public static UsageList<RecipeUsage> getRecipeUsageList() {
        return new UsageList<>(Arrays.asList(RECIPE_A_A, RECIPE_B_A, RECIPE_A_B, RECIPE_B_B, RECIPE_A_C, RECIPE_B_C,
            RECIPE_A_D, RECIPE_B_D, RECIPE_A_E, RECIPE_B_E, RECIPE_A_F, RECIPE_B_F));
    }
    public static UsageList<IngredientUsage> getIngredientUsageList() {
        return new UsageList<>(Arrays.asList(INGREDIENT_A_A, INGREDIENT_B_A, INGREDIENT_A_B, INGREDIENT_B_B,
            INGREDIENT_A_C, INGREDIENT_B_C, INGREDIENT_A_D, INGREDIENT_B_D, INGREDIENT_A_E, INGREDIENT_B_E,
            INGREDIENT_A_F, INGREDIENT_B_F));
    }
}
