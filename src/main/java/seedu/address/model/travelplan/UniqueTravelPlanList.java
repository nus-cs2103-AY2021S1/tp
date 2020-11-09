package seedu.address.model.travelplan;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.travelplan.exceptions.DuplicateTravelPlanException;
import seedu.address.model.travelplan.exceptions.TravelPlanNotFoundException;

/**
 * A list of travel plans that enforces uniqueness between its elements and does not allow nulls.
 * A travel plan is considered unique by comparing using {@code TravelPlan#isSameTravelPlan(TravelPlan)}. As such,
 * adding and updating of travel plans uses TravelPlan#isSameTravelPlan(TravelPlan) for equality so as to ensure that
 * the travel plan being added or updated is unique in terms of identity in the UniqueTravelPlanList. However, the
 * removal of a travel plan uses TravelPlan#equals(Object) so as to ensure that the travel plan with exactly the same
 * fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see TravelPlan#isSameTravelPlan(TravelPlan)
 */
public class UniqueTravelPlanList implements Iterable<TravelPlan> {

    private final ObservableList<TravelPlan> internalList = FXCollections.observableArrayList();
    private final ObservableList<TravelPlan> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent travel plan as the given argument.
     */
    public boolean contains(TravelPlan toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTravelPlan);
    }

    /**
     * Adds a travel plan to the list.
     * The travel plan must not already exist in the list.
     */
    public void add(TravelPlan toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTravelPlanException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the travel plan {@code target} in the list with {@code editedTravelPlan}.
     * {@code target} must exist in the list.
     * The travel plan identity of {@code editedTravelPlan} must not be the same as another existing travel plan in the
     * list.
     */
    public void setTravelPlan(TravelPlan target, TravelPlan editedTravelPlan) {
        requireAllNonNull(target, editedTravelPlan);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TravelPlanNotFoundException();
        }

        if (!target.isSameTravelPlan(editedTravelPlan) && contains(editedTravelPlan)) {
            throw new DuplicateTravelPlanException();
        }

        internalList.set(index, editedTravelPlan);
    }

    /**
     * Removes the equivalent travel plan from the list.
     * The travel plan must exist in the list.
     */
    public void remove(TravelPlan toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TravelPlanNotFoundException();
        }
    }

    public void setTravelPlans(UniqueTravelPlanList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList.stream()
                .map(tp -> new TravelPlan(tp))
                .collect(Collectors.toList()));
    }

    /**
     * Replaces the contents of this list with {@code travelPlans}.
     * {@code travelPlans} must not contain duplicate travel plans.
     */
    public void setTravelPlans(List<TravelPlan> travelPlans) {
        requireAllNonNull(travelPlans);
        if (!travelPlansAreUnique(travelPlans)) {
            throw new DuplicateTravelPlanException();
        }

        internalList.setAll(travelPlans.stream().map(tp -> new TravelPlan(tp)).collect(Collectors.toList()));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<TravelPlan> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<TravelPlan> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTravelPlanList // instanceof handles nulls
                && internalList.equals(((UniqueTravelPlanList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code travelPlans} contains only unique travel plans.
     */
    private boolean travelPlansAreUnique(List<TravelPlan> travelPlans) {
        for (int i = 0; i < travelPlans.size() - 1; i++) {
            for (int j = i + 1; j < travelPlans.size(); j++) {
                if (travelPlans.get(i).isSameTravelPlan(travelPlans.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
