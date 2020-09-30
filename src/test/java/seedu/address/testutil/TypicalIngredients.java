package seedu.address.testutil;

import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;

public class TypicalIngredients {

    public static final Ingredient INGREDIENT_1 = new Ingredient(1, 1);
    public static final Ingredient INGREDIENT_2 = new Ingredient(2, 2);

    private TypicalIngredients() {} // prevents instantiation

    /**
     * Returns an {@code ItemList} with all the typical items.
     */
    public static IngredientList getTypicalIngredientList() {
        IngredientList ab = new IngredientList();
        ab.add(INGREDIENT_1);
        ab.add(INGREDIENT_2);
        return ab;
    }
}
