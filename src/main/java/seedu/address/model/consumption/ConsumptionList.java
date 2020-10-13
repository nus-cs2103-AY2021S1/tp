package seedu.address.model.consumption;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.consumption.exceptions.ConsumptionNotFoundException;

/**
 * A list of consumption that does not allow nulls.
 *
 * Supports a minimal set of list operations.
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
     * Removes the equivalent food eaten from the list.
     * The food must exist in the list.
     */
    public void remove(Consumption toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ConsumptionNotFoundException();
        }
    }

    public void setConsumption(Consumption target, Consumption editedConsumption) {
        requireAllNonNull(target, editedConsumption);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ConsumptionNotFoundException();
        }

        internalList.set(index, editedConsumption);
    }

    public void setConsumptions(ConsumptionList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code consumptions}.
     * {@code consumptions} must not contain duplicate consumptions.
     */
    public void setConsumptions(List<Consumption> consumptions) {
        requireAllNonNull(consumptions);

        internalList.setAll(consumptions);
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
