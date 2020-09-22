package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.recipe.Recipe;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyRecipeList {

    /**
     * Returns an unmodifiable view of the items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Recipe> getRecipeList();

}
