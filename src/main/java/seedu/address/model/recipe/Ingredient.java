package seedu.address.model.recipe;

import javafx.util.Pair;

/**
 * Convenience class to define a {@code Pair<Integer, Integer>}.
 */
public class Ingredient extends Pair<Integer, Integer> {

    /**
     * Creates a new ingredient.
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public Ingredient(Integer key, Integer value) {
        super(key, value);
        assert value > 0 : "Value should never be 0 or negative";
    }

    /**
     * Utilty function to check if itemId is equivalent to contained key of Ingredient.
     * @param itemId identity key of item
     * @return boolean check whether the key stored in this ingredient is the queried item.
     */
    public boolean isItem(int itemId) {
        return getKey().equals(itemId);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getKey());
    }
}
