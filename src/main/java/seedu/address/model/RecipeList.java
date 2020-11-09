package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.UniqueRecipeList;

/**
 * Wraps all data at the recipe-list level
 * Duplicates are not allowed (by .isSameRecipe comparison)
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
     * Creates an RecipeList using the Recipes in the {@code toBeCopied}
     */
    public RecipeList(ReadOnlyRecipeList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the recipe list with {@code recipes}.
     * {@code recipes} must not contain duplicate recipes.
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes.setRecipes(recipes);
    }

    /**
     * Resets the existing data of this {@code RecipeList} with {@code newData}.
     */
    public void resetData(ReadOnlyRecipeList newData) {
        requireNonNull(newData);

        setRecipes(newData.getRecipeList());
    }

    //// recipe-level operations

    /**
     * Returns true if a recipe with the same identity as {@code recipe} exists in the recipe list.
     */
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return recipes.contains(recipe);
    }

    /**
     * Adds a recipe to the recipe list.
     * The recipe must not already exist in the recipe list.
     */
    public void addRecipe(Recipe p) {
        recipes.add(p);
    }

    /**
     * Replaces the given recipe {@code target} in the list with {@code editedRecipe}.
     * {@code target} must exist in the recipe list.
     * The recipe identity of {@code editedRecipe} must not be the same as another existing recipe in the recipe list.
     */
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireNonNull(editedRecipe);

        recipes.setRecipe(target, editedRecipe);
    }

    /**
     * Removes {@code key} from this {@code RecipeList}.
     * {@code key} must exist in the recipe list.
     */
    public void removeRecipe(Recipe key) {
        recipes.remove(key);
    }

    /**
     * Deletes {@code recipe} from this {@code RecipeList}.
     * {@code recipe} must exist in the recipe list.
     */
    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
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
