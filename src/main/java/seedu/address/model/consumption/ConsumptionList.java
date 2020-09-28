package seedu.address.model.consumption;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.recipe.Recipe;

/**
 * A list of consumption that does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Recipe#isSameRecipe(Recipe)
 */
public class ConsumptionList implements Iterable<Consumption> {

    private final ObservableList<Consumption> internalList = FXCollections.observableArrayList();
    private final ObservableList<Consumption> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a recipe to the consumption list.
     * The recipe must already exist in the list.
     */
    public void eat(Consumption toEat) {
        requireNonNull(toEat);
        internalList.add(toEat);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Consumption> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Consumption> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ConsumptionList // instanceof handles nulls
                && internalList.equals(((ConsumptionList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
