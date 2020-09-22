package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.UniqueRecipeList;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the item-list level
 * Duplicates are not allowed (by .isSameItem comparison)
 */
public class RecipeList implements ReadOnlyRecipeList {

    private final UniqueRecipeList recipes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        recipes = new UniqueRecipeList();
    }

    public RecipeList() {}

    /**
     * Creates an ItemList using the Items in the {@code toBeCopied}
     */
    public RecipeList(ReadOnlyRecipeList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes.setRecipes(recipes);
    }

    /**
     * Resets the existing data of this {@code ItemList} with {@code newData}.
     */
    public void resetData(ReadOnlyRecipeList newData) {
        requireNonNull(newData);

        setRecipes(newData.getRecipeList());
    }

    //// item-level operations

    /**
     * Returns true if a item with the same identity as {@code item} exists in the item list.
     */
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return recipes.contains(recipe);
    }

    /**
     * Adds a item to the item list.
     * The item must not already exist in the item list.
     */
    public void addRecipe(Recipe p) {
        recipes.add(p);
    }

    /**
     * Replaces the given item {@code target} in the list with {@code editedItem}.
     * {@code target} must exist in the item list.
     * The item identity of {@code editedItem} must not be the same as another existing item in the item list.
     */
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireNonNull(editedRecipe);

        recipes.setRecipe(target, editedRecipe);
    }

    /**
     * Removes {@code key} from this {@code ItemList}.
     * {@code key} must exist in the item list.
     */
    public void removeRecipe(Recipe key) {
        recipes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return recipes.asUnmodifiableObservableList().size() + " recipes";
        // TODO: refine later
    }

    @Override
    public ObservableList<Recipe> getRecipeList() {
        return recipes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RecipeList // instanceof handles nulls
                && recipes.equals(((RecipeList) other).recipes));
    }

    @Override
    public int hashCode() {
        return recipes.hashCode();
    }
}
