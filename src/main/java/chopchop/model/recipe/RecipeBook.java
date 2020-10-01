package chopchop.model.recipe;

import chopchop.model.FoodEntryBook;
import chopchop.model.ReadOnlyFoodEntryBook;

public class RecipeBook extends FoodEntryBook {

    public RecipeBook() {
        super();
    }

    /**
     * Creates an RecipeBook using the Ingredients in the {@code toBeCopied}
     */
    public RecipeBook(ReadOnlyFoodEntryBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }
}
