package seedu.address.model.recipe;

import javafx.util.Pair;

public class Ingredient extends Pair<Integer, Integer> {

    /**
     * Creates a new ingredient
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public Ingredient(Integer key, Integer value) {
        super(key, value);
    }
}
