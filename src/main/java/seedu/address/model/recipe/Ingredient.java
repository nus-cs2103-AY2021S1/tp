package seedu.address.model.recipe;

import javafx.util.Pair;

/**
 * Convenience class to define a {@code Pair<Integer, Integer>}.
 */
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

    /**
     * Utilty function to check if itemid is equivelent to contained id
     * @param id identity key
     * @return boolean check that the id is the item
     */
    public boolean isItem(int id) {
        return getKey().equals(id);
    }
}
