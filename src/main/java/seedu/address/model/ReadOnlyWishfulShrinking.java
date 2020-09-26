package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Recipe;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyWishfulShrinking {

    /**
     * Returns an unmodifiable view of the recipes list.
     * This list will not contain any duplicate recipes.
     */
    ObservableList<Recipe> getRecipeList();

    /**
     * Returns an unmodifiable view of the ingredients list.
     * This list will not contain any duplicate ingredients.
     */
    ObservableList<Ingredient> getIngredientList();

}
