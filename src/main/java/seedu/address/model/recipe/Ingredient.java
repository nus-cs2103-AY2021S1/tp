package seedu.address.model.recipe;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.model.item.Item;
import seedu.address.model.recipe.exceptions.IngredientNotFoundException;

import java.util.HashMap;

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

    public String toString(ObservableList<Item> filteredItemList) {
        HashMap<Integer, String> hm = new HashMap<>();
        filteredItemList.forEach(item -> hm.put(item.getId(), item.getName()));
        if (!hm.containsKey(this.getKey())) {
            throw new IngredientNotFoundException();
        }
        return hm.get(this.getKey()) + " [" + this.getValue() + "]";
    }
}
