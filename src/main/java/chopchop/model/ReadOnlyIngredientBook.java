package chopchop.model;

import chopchop.model.ingredient.Ingredient;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyIngredientBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Ingredient> getIngredientList();

}
