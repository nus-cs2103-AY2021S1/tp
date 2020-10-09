package chopchop.model.ingredient;

import chopchop.model.ReadOnlyFoodEntryBook;
import javafx.collections.ObservableList;

public interface ReadOnlyIngredientBook extends ReadOnlyFoodEntryBook {

    /**
     * Returns an unmodifiable view of the ingredient list.
     * This list will not contain any duplicate ingredients.
     */
    @Override
    ObservableList<Ingredient> getFoodEntryList();

}
