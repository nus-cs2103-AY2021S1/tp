package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.recipe.Recipe;

/**
 * Unmodifiable view of a recipe list.
 */
public interface ReadOnlyRecipeList {

    /**
     * Returns an unmodifiable view of the recipe list.
     * This list will not contain any duplicate recipes.
     */
    ObservableList<Recipe> getRecipeList();

}
