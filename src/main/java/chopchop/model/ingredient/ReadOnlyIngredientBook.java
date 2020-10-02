package chopchop.model.ingredient;

import chopchop.model.ReadOnlyFoodEntryBook;
import chopchop.model.ingredient.Ingredient;
import javafx.collections.ObservableList;

public interface ReadOnlyIngredientBook extends ReadOnlyFoodEntryBook {

    /**
     * Returns an unmodifiable view of the ingredient list.
     * This list will not contain any duplicate ingredients.
     */
    ObservableList<Ingredient> getFoodEntryList();

}
