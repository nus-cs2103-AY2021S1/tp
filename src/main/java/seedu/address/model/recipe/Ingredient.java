package seedu.address.model.recipe;

import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.model.item.Item;
import seedu.address.model.recipe.exceptions.IngredientNotFoundException;



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

    /**
     * Displays the name of the ingredient and the quantity, based on the item list given.
     * @param filteredItemList The current item list in the model.
     * @return String representation of the ingredient ready for display.
     */
    public String toString(ObservableList<Item> filteredItemList) {
        HashMap<Integer, String> hm = new HashMap<>();
        filteredItemList.forEach(item -> hm.put(item.getId(), item.getName()));
        if (!hm.containsKey(this.getKey())) {
            throw new IngredientNotFoundException();
        }
        return hm.get(this.getKey()) + " [" + this.getValue() + "]";
    }
}
