package chopchop.model;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a food entry book
 */
public interface ReadOnlyFoodEntryBook <F extends FoodEntry> {

    /**
     * Returns an unmodifiable view of the food entry list.
     * This list will not contain any duplicate food entries.
     */
    ObservableList<F> getFoodEntryList();

}
