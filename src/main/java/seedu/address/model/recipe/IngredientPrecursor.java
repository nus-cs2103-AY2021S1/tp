package seedu.address.model.recipe;

import javafx.util.Pair;

public class IngredientPrecursor extends Pair<String, Integer> {

    public IngredientPrecursor(String key, Integer value) {
        super(key, value);
    }

    public Ingredient toIngredient(int ingredientId) {
        return new Ingredient(ingredientId, getValue());
    }
}
