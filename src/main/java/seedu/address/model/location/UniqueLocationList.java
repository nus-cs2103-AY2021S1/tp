package seedu.address.model.location;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.location.exceptions.DuplicateLocationException;
import seedu.address.model.location.exceptions.LocationNotFoundException;


/**
 * A list of locations that enforces uniqueness between its elements and does not allow nulls.
 * A location is considered unique by comparing using {@code Location#isSameLocation(Location)}.
 * As such, adding and updating of
 * locations uses Location#isSameLocation(Location) for equality so as to ensure that the
 * location being added or updated is
 * unique in terms of identity in the UniqueLocationList. However, the removal of a location uses
 * Location#equals(Object) so
 * as to ensure that the location with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Location#isSameLocation(Location)
 */
public class UniqueLocationList implements Iterable<Location> {

    private final ObservableList<Location> internalList = FXCollections.observableArrayList();
    private final ObservableList<Location> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent location as the given argument.
     */
    public boolean contains(Location toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameLocation);
    }

    /**
     * Returns location ID of given location
     */
    public int findLocationID(Location toFind) {
        requireNonNull(toFind);
        return internalList.stream().filter(toFind::isSameLocation).findFirst().get().getId();
    }

    /**
     * For use by a querying class to retrieve the textual location
     * @param id id of Location
     * @return an Optional Location that maybe exists.
     */
    public Optional<Location> findLocationFromId(int id) {
        return internalList.stream()
                .filter(x -> x.getId() == id)
                .findFirst();
    }


    /**
     * Adds a location to the list.
     * The location must not already exist in the list.
     */
    public void add(Location toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateLocationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the location {@code target} in the list with {@code editedLocation}.
     * {@code target} must exist in the list.
     * The location identity of {@code editedLocation} must not be the same as another existing location in the list.
     */
    public void setLocation(Location target, Location editedLocation) {
        requireAllNonNull(target, editedLocation);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new LocationNotFoundException();
        }

        if (!target.isSameLocation(editedLocation) && contains(editedLocation)) {
            throw new DuplicateLocationException();
        }

        internalList.set(index, editedLocation);
    }

    /**
     * Removes the equivalent location from the list.
     * The location must exist in the list.
     */
    public void remove(Location toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new LocationNotFoundException();
        }
    }

    public void setLocations(UniqueLocationList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code locations}.
     * {@code locations} must not contain duplicate locations.
     */
    public void setLocations(List<Location> locations) {
        requireAllNonNull(locations);
        if (!locationsAreUnique(locations)) {
            throw new DuplicateLocationException();
        }

        internalList.setAll(locations);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Location> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Location> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueLocationList // instanceof handles nulls
                && internalList.equals(((UniqueLocationList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code locations} contains only unique locations.
     */
    private boolean locationsAreUnique(List<Location> locations) {
        for (int i = 0; i < locations.size() - 1; i++) {
            for (int j = i + 1; j < locations.size(); j++) {
                if (locations.get(i).isSameLocation(locations.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
