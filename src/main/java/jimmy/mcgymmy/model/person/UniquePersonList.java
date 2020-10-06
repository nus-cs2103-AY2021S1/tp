package jimmy.mcgymmy.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jimmy.mcgymmy.commons.util.CollectionUtil;
import jimmy.mcgymmy.model.person.exceptions.DuplicatePersonException;
import jimmy.mcgymmy.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A food is considered unique by comparing using {@code Food#isSamePerson(Food)}. As such, adding and updating of
 * persons uses Food#isSamePerson(Food) for equality so as to ensure that the food being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a food uses Food#equals(Object) so
 * as to ensure that the food with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Food#isSamePerson(Food)
 */
public class UniquePersonList implements Iterable<Food> {

    private final ObservableList<Food> internalList = FXCollections.observableArrayList();
    private final ObservableList<Food> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent food as the given argument.
     */
    public boolean contains(Food toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a food to the list.
     * The food must not already exist in the list.
     */
    public void add(Food toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the food {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in the list.
     * The food identity of {@code editedFood} must not be the same as another existing food in the list.
     */
    public void setPerson(Food target, Food editedFood) {
        CollectionUtil.requireAllNonNull(target, editedFood);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedFood) && contains(editedFood)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedFood);
    }

    /**
     * Removes the equivalent food from the list.
     * The food must exist in the list.
     */
    public void remove(Food toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code foods}.
     * {@code foods} must not contain duplicate foods.
     */
    public void setPersons(List<Food> foods) {
        CollectionUtil.requireAllNonNull(foods);
        if (!personsAreUnique(foods)) {
            throw new DuplicatePersonException();
        }

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
            || (other instanceof UniquePersonList // instanceof handles nulls
            && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code foods} contains only unique foods.
     */
    private boolean personsAreUnique(List<Food> foods) {
        for (int i = 0; i < foods.size() - 1; i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                if (foods.get(i).isSamePerson(foods.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
