package chopchop.model.ingredient;

import chopchop.model.FoodEntryBook;
import chopchop.model.ReadOnlyFoodEntryBook;

public class IngredientBook extends FoodEntryBook {

    public IngredientBook() {
        super();
    }

    /**
     * Creates an IngredientBook using the Ingredients in the {@code toBeCopied}
     */
    public IngredientBook(ReadOnlyFoodEntryBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }
}
