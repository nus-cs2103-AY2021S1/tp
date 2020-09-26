package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.UniqueAttractionList;

/**
 * Wraps all data at the trackpad level
 * Duplicates are not allowed (by .isSameAttraction comparison)
 */
public class TrackPad implements ReadOnlyTrackPad {

    private final UniqueAttractionList attractions;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        attractions = new UniqueAttractionList();
    }

    public TrackPad() {}

    /**
     * Creates an TrackPad using the Attractions in the {@code toBeCopied}
     */
    public TrackPad(ReadOnlyTrackPad toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the attraction list with {@code attractions}.
     * {@code attractions} must not contain duplicate attractions.
     */
    public void setAttractions(List<Attraction> attractions) {
        this.attractions.setAttractions(attractions);
    }

    /**
     * Resets the existing data of this {@code TrackPad} with {@code newData}.
     */
    public void resetData(ReadOnlyTrackPad newData) {
        requireNonNull(newData);

        setAttractions(newData.getAttractionList());
    }

    //// attraction-level operations

    /**
     * Returns true if a attraction with the same identity as {@code attraction} exists in the address book.
     */
    public boolean hasAttraction(Attraction attraction) {
        requireNonNull(attraction);
        return attractions.contains(attraction);
    }

    /**
     * Adds a attraction to the address book.
     * The attraction must not already exist in the address book.
     */
    public void addAttraction(Attraction a) {
        attractions.add(a);
    }

    /**
     * Replaces the given attraction {@code target} in the list with {@code editedAttraction}.
     * {@code target} must exist in the track pad.
     * The person identity of {@code editedAttraction} must not be the same as another existing attraction in the address book.
     */
    public void setAttraction(Attraction target, Attraction editedAttraction) {
        requireNonNull(editedAttraction);

        attractions.setAttraction(target, editedAttraction);
    }

    /**
     * Removes {@code key} from this {@code TrackPad}.
     * {@code key} must exist in the track pad.
     */
    public void removeAttraction(Attraction key) {
        attractions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return attractions.asUnmodifiableObservableList().size() + " attractions";
        // TODO: refine later
    }

    @Override
    public ObservableList<Attraction> getAttractionList() {
        return attractions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrackPad // instanceof handles nulls
                && attractions.equals(((TrackPad) other).attractions));
    }

    @Override
    public int hashCode() {
        return attractions.hashCode();
    }
}
