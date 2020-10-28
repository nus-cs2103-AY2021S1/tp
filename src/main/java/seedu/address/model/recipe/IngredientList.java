package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.recipe.exceptions.DuplicateIngredientException;
import seedu.address.model.recipe.exceptions.IngredientNotFoundException;

/**
 * A list of ingredients that enforces uniqueness between its elements and does not allow nulls.
 * <p>
 * Supports a minimal set of list operations.
 */
public class IngredientList implements Iterable<Ingredient> {

    private final ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
    private final ObservableList<Ingredient> ingredientUnmodifiableList =
            FXCollections.unmodifiableObservableList(ingredientList);

    /**
     * Returns true if the list contains an equivalent ingredient as the given ingredient.
     */
    public boolean contains(Ingredient toCheck) {
        requireNonNull(toCheck);
        return ingredientList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an ingredient to the list.
     * The ingredient must not already exist in the list.
     */
    public void add(Ingredient toAdd) {
        requireNonNull(toAdd);
        if (contains((toAdd))) {
            throw new DuplicateIngredientException();
        }
        ingredientList.add(toAdd);
    }

    /**
     * Removes the equivalent ingredient from the list.
     * The ingredient must exist in the list.
     */
    public void remove(Ingredient toRemove) {
        requireNonNull(toRemove);
        if (!ingredientList.remove(toRemove)) {
            throw new IngredientNotFoundException();
        }
    }

    public void setItems(IngredientList replacement) {
        requireNonNull(replacement);
        ingredientList.setAll(replacement.ingredientList);
    }

    /**
     * Replaces the contents of this list with {@code ingredients}.
     * {@code ingredients} must not contain duplicate ingredients.
     */
    public void setItems(List<Ingredient> ingredients) {
        requireAllNonNull(ingredients);
        if (!itemsAreUnique(ingredients)) {
            throw new DuplicateIngredientException();
        }

        ingredientList.setAll(ingredients);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Ingredient> asUnmodifiableObservableList() {
        return ingredientUnmodifiableList;
    }

    @Override
    public Iterator<Ingredient> iterator() {
        return ingredientList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientList // instanceof handles nulls
                && ingredientList.equals(((IngredientList) other).ingredientList));
    }

    @Override
    public int hashCode() {
        return ingredientList.hashCode();
    }

    /**
     * Returns true if {@code ingredients} contains only unique ingredients.
     */
    private boolean itemsAreUnique(List<Ingredient> ingredients) {
        for (int i = 0; i < ingredients.size() - 1; i++) {
            for (int j = i + 1; j < ingredients.size(); j++) {
                if (ingredients.get(i).equals(ingredients.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
