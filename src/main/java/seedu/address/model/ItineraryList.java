package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.itinerary.UniqueItineraryList;

/**
 * Wraps all data at trackPad's itinerary list level
 * Duplicates are not allowed (by .isSameItinerary comparison)
 */
public class ItineraryList implements ReadOnlyItineraryList {

    private final UniqueItineraryList itineraries;
    private Itinerary currentItinerary;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        itineraries = new UniqueItineraryList();
    }

    public ItineraryList() {
    }

    /**
     * Creates an ItineraryList using the Itineraries in the {@code toBeCopied}
     */
    public ItineraryList(ReadOnlyItineraryList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the itinerary list with {@code itineraries}.
     * {@code itineraries} must not contain duplicate itineraries.
     */
    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries.setItineraries(itineraries);
    }

    /**
     * Resets the existing data of this {@code ItineraryList} with {@code newData}.
     */
    public void resetData(ReadOnlyItineraryList newData) {
        requireNonNull(newData);

        setItineraries(newData.getItineraryList());
    }

    //// itinerary-level operations

    /**
     * Returns true if an itinerary with the same identity as {@code itinerary} exists in the itinerary list.
     */
    public boolean hasItinerary(Itinerary itinerary) {
        requireNonNull(itinerary);
        return itineraries.contains(itinerary);
    }

    /**
     * Adds an itinerary to the itinerary list.
     * The itinerary must not already exist in the itinerary list.
     */
    public void addItinerary(Itinerary a) {
        itineraries.add(a);
    }

    /**
     * Replaces the given itinerary {@code target} in the list with {@code editedItinerary}.
     * {@code target} must exist in the itinerary list.
     * The itinerary identity of {@code editedItinerary} must not be the same as another existing
     * itinerary in the itinerary list.
     */
    public void setItinerary(Itinerary target, Itinerary editedItinerary) {
        requireNonNull(editedItinerary);

        itineraries.setItinerary(target, editedItinerary);
    }

    /**
     * Removes {@code key} from this {@code ItineraryList}.
     * {@code key} must exist in the itinerary list.
     */
    public void removeItinerary(Itinerary key) {
        itineraries.remove(key);
    }

    //// current Itinerary methods

    public void setCurrentItinerary(Itinerary currentItinerary) {
        this.currentItinerary = currentItinerary;
    }

    public Itinerary getCurrentItinerary() {
        return this.currentItinerary;
    }

    //// util methods

    @Override
    public String toString() {
        return itineraries.asUnmodifiableObservableList().size() + " itineraries";
        // TODO: refine later
    }

    @Override
    public ObservableList<Itinerary> getItineraryList() {
        return itineraries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItineraryList // instanceof handles nulls
                && itineraries.equals(((ItineraryList) other).itineraries))
                && Optional.ofNullable(currentItinerary)
                .equals(Optional.ofNullable(((ItineraryList) other).currentItinerary));
    }

    @Override
    public int hashCode() {
        return itineraries.hashCode();
    }
}
