package seedu.address.testutil;

import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;

public class TypicalIngredients {

    public static final Ingredient INGREDIENT_1 = new Ingredient(1, 1); // 1 apple
    public static final Ingredient INGREDIENT_2 = new Ingredient(2, 2); // 2 banana
    public static final Ingredient INGREDIENT_3 = new Ingredient(3, 1); // 1 pear

    private TypicalIngredients() {} // prevents instantiation

    /**
     * Returns an {@code IngredientList} with all the typical ingredients.
     */
    public static IngredientList getTypicalIngredientList() {
        IngredientList ingredients = new IngredientList();
        ingredients.add(INGREDIENT_1);
        ingredients.add(INGREDIENT_2);
        return ingredients;
    }

    /**
     * Returns an {@code IngredientList} with other typical ingredients.
     */
    public static IngredientList getTypicalSecondIngredientList() {
        IngredientList ingredients = new IngredientList();
        ingredients.add(INGREDIENT_2);
        ingredients.add(INGREDIENT_3);
        return ingredients;
    }
}
