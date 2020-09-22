package seedu.address.model.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.recipe.exceptions.DuplicateIngredientException;
import seedu.address.model.recipe.exceptions.IngredientNotFoundException;

import java.util.Iterator;
import java.util.List;

public class IngredientList implements Iterable<Ingredient> {

    private final ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList();
    private final ObservableList<Ingredient> ingredientUnmodifiableList =
            FXCollections.unmodifiableObservableList(ingredientList);

    public boolean contains(Ingredient toCheck) {
        requireNonNull(toCheck);
        return ingredientList.stream().anyMatch(toCheck::equals);
    }

    public void add(Ingredient toAdd) {
        requireNonNull(toAdd);
        if (contains((toAdd))) {
            throw new DuplicateIngredientException();
        }
        ingredientList.add(toAdd);
    }

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
     * Replaces the contents of this list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItems(List<Ingredient> ingredients) {
        requireAllNonNull(ingredients);
        if (!itemsAreUnique(ingredients)) {
            throw new DuplicateItemException();
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
     * Returns true if {@code items} contains only unique items.
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
