package seedu.address.model.food;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.food.exceptions.FoodNotFoundException;

/**
 * A list of food items that allows repeated elements and does not allow nulls.
 * Supports a minimal set of list operations.
 *
 * @see Food#isSameFood(Food)
 */
public class Fridge implements Iterable<Food> {
    private final ObservableList<Food> internalList = FXCollections.observableArrayList();
    private final ObservableList<Food> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent food item as the given argument.
     */
    public boolean contains(Food toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFood);
    }

    /**
     * Adds a food item to the list.
     */
    public void add(Food toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the food item {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in the list.
     */
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FoodNotFoundException();
        }
        internalList.set(index, editedFood);
    }

    /**
     * Removes the equivalent food item from the list.
     * The food item must exist in the list.
     */
    public void remove(Food toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FoodNotFoundException();
        }
    }

    public void setFoods(Fridge replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code foods}.
     */
    public void setFoods(List<Food> foods) {
        requireAllNonNull(foods);

        internalList.setAll(foods);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Food> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Food> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Fridge // instanceof handles nulls
            && internalList.equals(((Fridge) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
