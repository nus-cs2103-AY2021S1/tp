package chopchop.model.recipe;

import chopchop.model.ReadOnlyFoodEntryBook;
import javafx.collections.ObservableList;
import chopchop.model.recipe.Recipe;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyRecipeBook extends ReadOnlyFoodEntryBook {

    /**
     * Returns an unmodifiable view of the recipes list.
     * This list will not contain any duplicate recipes.
     */
    ObservableList<Recipe> getFoodEntryList();

}
