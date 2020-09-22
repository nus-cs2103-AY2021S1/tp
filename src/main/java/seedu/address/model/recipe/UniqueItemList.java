package seedu.address.model.recipe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.recipe.exceptions.RecipeNotFoundException;
import seedu.address.model.recipe.exceptions.DuplicateRecipeException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


/**
 * A list of items that enforces uniqueness between its elements and does not allow nulls.
 * A item is considered unique by comparing using {@code Recipe#isSameItem(Recipe)}. As such, adding and updating of
 * items uses Recipe#isSameItem(Recipe) for equality so as to ensure that the item being added or updated is
 * unique in terms of identity in the UniqueItemList. However, the removal of a item uses Recipe#equals(Object) so
 * as to ensure that the item with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Recipe#isSameRecipe(Recipe)
 */
public class UniqueItemList implements Iterable<Recipe> {

    private final ObservableList<Recipe> internalList = FXCollections.observableArrayList();
    private final ObservableList<Recipe> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent item as the given argument.
     */
    public boolean contains(Recipe toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRecipe);
    }

    /**
     * Adds a item to the list.
     * The item must not already exist in the list.
     */
    public void add(Recipe toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRecipeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the item {@code target} in the list with {@code editedRecipe}.
     * {@code target} must exist in the list.
     * The item identity of {@code editedRecipe} must not be the same as another existing item in the list.
     */
    public void setItem(Recipe target, Recipe editedRecipe) {
        requireAllNonNull(target, editedRecipe);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RecipeNotFoundException();
        }

        if (!target.isSameRecipe(editedRecipe) && contains(editedRecipe)) {
            throw new DuplicateRecipeException();
        }

        internalList.set(index, editedRecipe);
    }

    /**
     * Removes the equivalent item from the list.
     * The item must exist in the list.
     */
    public void remove(Recipe toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RecipeNotFoundException();
        }
    }

    public void setItems(UniqueItemList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code recipes}.
     * {@code recipes} must not contain duplicate recipes.
     */
    public void setItems(List<Recipe> recipes) {
        requireAllNonNull(recipes);
        if (!itemsAreUnique(recipes)) {
            throw new DuplicateRecipeException();
        }

        internalList.setAll(recipes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Recipe> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Recipe> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueItemList // instanceof handles nulls
                && internalList.equals(((UniqueItemList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code recipes} contains only unique recipes.
     */
    private boolean itemsAreUnique(List<Recipe> recipes) {
        for (int i = 0; i < recipes.size() - 1; i++) {
            for (int j = i + 1; j < recipes.size(); j++) {
                if (recipes.get(i).isSameRecipe(recipes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
