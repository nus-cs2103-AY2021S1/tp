package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.location.Location;
import seedu.address.model.location.UniqueLocationList;

/**
 * Wraps all data at the location-list level
 * Duplicates are not allowed (by .isSameLocation comparison)
 */
public class LocationList implements ReadOnlyLocationList {

    private final UniqueLocationList locations;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        locations = new UniqueLocationList();
    }

    public LocationList() {}

    /**
     * Creates an LocationList using the Locations in the {@code toBeCopied}
     */
    public LocationList(ReadOnlyLocationList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the location list with {@code locations}.
     * {@code locations} must not contain duplicate locations.
     */
    public void setLocations(List<Location> locations) {
        this.locations.setLocations(locations);
    }

    /**
     * Resets the existing data of this {@code LocationList} with {@code newData}.
     */
    public void resetData(ReadOnlyLocationList newData) {
        requireNonNull(newData);

        setLocations(newData.getLocationList());
    }

    //// location-level operations

    /**
     * Returns true if a location with the same identity as {@code location} exists in the location list.
     */
    public boolean hasLocation(Location location) {
        requireNonNull(location);
        return locations.contains(location);
    }

    /**
     * Returns location ID of location to be found
     */
    public int findLocationID(Location location) {
        requireNonNull(location);
        return locations.findLocationID(location);
    }

    /**
     * Adds a location to the location list.
     * The location must not already exist in the location list.
     */
    public void addLocation(Location p) {
        locations.add(p);
    }

    /**
     * Replaces the given location {@code target} in the list with {@code editedLocation}.
     * {@code target} must exist in the location list.
     * The location identity of {@code editedLocation} must not be the
     * same as another existing location in the location list.
     */
    public void setLocation(Location target, Location editedLocation) {
        requireNonNull(editedLocation);

        locations.setLocation(target, editedLocation);
    }

    /**
     * Removes {@code key} from this {@code LocationList}.
     * {@code key} must exist in the location list.
     */
    public void removeLocation(Location key) {
        locations.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return locations.asUnmodifiableObservableList().size() + " locations";
        // TODO: refine later
    }

    @Override
    public ObservableList<Location> getLocationList() {
        return locations.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LocationList // instanceof handles nulls
                && locations.equals(((LocationList) other).locations));
    }

    @Override
    public int hashCode() {
        return locations.hashCode();
    }
}
