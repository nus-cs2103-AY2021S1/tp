package jimmy.mcgymmy.model.food;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jimmy.mcgymmy.commons.core.index.Index;
import jimmy.mcgymmy.commons.util.CollectionUtil;
import jimmy.mcgymmy.model.food.exceptions.DuplicateFoodException;
import jimmy.mcgymmy.model.food.exceptions.FoodNotFoundException;

/**
 * A list of food items that allows repeated elements and does not allow nulls.
 * Supports a minimal set of list operations.
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
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a food item to the list.
     */
    public void add(Food toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Replaces the food {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The food identity of {@code editedPerson} must not be the same as another existing food in the list.
     */
    public void setFood(Food target, Food editedFood) {
        CollectionUtil.requireAllNonNull(target, editedFood);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FoodNotFoundException();
        }

        if (!target.equals(editedFood) && contains(editedFood)) {
            throw new DuplicateFoodException();
        }

        internalList.set(index, editedFood);
    }

    /**
     * Replaces the food item at the {@code index} position in the list with {@code editedFood}.
     * {@code target} must exist in the list.
     */
    public void setFood(Index index, Food editedFood) {
        CollectionUtil.requireAllNonNull(editedFood, index);
        internalList.set(index.getZeroBased(), editedFood);
    }

    /**
     * Removes the food item at the position index from the list.
     * The food item must exist in the list.
     */
    public void remove(Index removeIndex) {
        requireNonNull(removeIndex);
        internalList.remove(removeIndex.getZeroBased());
    }

    /**
     * Remove the food item from the list.
     *
     * @param food Food item to be removed.
     */
    public void remove(Food food) {
        requireNonNull(food);
        internalList.remove(internalList.indexOf(food));
    }

    public void setFoods(Fridge replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code foods}.
     */
    public void setFoods(List<Food> foods) {
        CollectionUtil.requireAllNonNull(foods);
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
