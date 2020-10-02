package chopchop.model.recipe;

import chopchop.model.FoodEntry;
import chopchop.model.FoodEntryBook;
import javafx.collections.ObservableList;

public class RecipeBook extends FoodEntryBook {

    public RecipeBook() {
        super();
    }

    /**
     * Creates an RecipeBook using the Ingredients in the {@code toBeCopied}
     */
    public RecipeBook(ReadOnlyRecipeBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }


    public ObservableList<Recipe> getFoodEntryList() {
        return entries.asUnmodifiableObservableList();
    }
}
