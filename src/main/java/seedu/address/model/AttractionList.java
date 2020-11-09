package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.UniqueAttractionList;

/**
 * Wraps all data at trackPad's attraction list level
 * Duplicates are not allowed (by .isSameAttraction comparison)
 */
public class AttractionList implements ReadOnlyAttractionList {

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

    public AttractionList() {}

    /**
     * Creates an AttractionList using the Attractions in the {@code toBeCopied}
     */
    public AttractionList(ReadOnlyAttractionList toBeCopied) {
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
     * Resets the existing data of this {@code AttractionList} with {@code newData}.
     */
    public void resetData(ReadOnlyAttractionList newData) {
        requireNonNull(newData);

        setAttractions(newData.getAttractionList());
    }

    //// attraction-level operations

    /**
     * Returns true if a attraction with the same identity as {@code attraction} exists in the attraction list.
     */
    public boolean hasAttraction(Attraction attraction) {
        requireNonNull(attraction);
        return attractions.contains(attraction);
    }

    /**
     * Adds a attraction to the attraction list.
     * The attraction must not already exist in the attraction list.
     */
    public void addAttraction(Attraction a) {
        attractions.add(a);
    }

    /**
     * Replaces the given attraction {@code target} in the list with {@code editedAttraction}.
     * {@code target} must exist in the attraction list.
     * The attraction identity of {@code editedAttraction} must not be the same as another existing
     * attraction in the attraction list.
     */
    public void setAttraction(Attraction target, Attraction editedAttraction) {
        requireNonNull(editedAttraction);

        attractions.setAttraction(target, editedAttraction);
    }

    /**
     * Removes {@code key} from this {@code AttractionList}.
     * {@code key} must exist in the attraction list.
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
                || (other instanceof AttractionList // instanceof handles nulls
                && attractions.equals(((AttractionList) other).attractions));
    }

    @Override
    public int hashCode() {
        return attractions.hashCode();
    }
}
