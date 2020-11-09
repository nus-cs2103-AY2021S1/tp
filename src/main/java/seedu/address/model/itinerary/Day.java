package seedu.address.model.itinerary;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.model.itinerary.exceptions.DuplicateItineraryAttractionException;
import seedu.address.model.itinerary.exceptions.ItineraryAttractionNotFoundException;

/**
 * Represents an Itinerary's day in TrackPad.
 */
public class Day {

    public static final String MESSAGE_CONSTRAINTS = "Day should be a positive number, not be blank, and"
            + " should exist within the date range of the itinerary.";
    public final Integer value;
    private final List<ItineraryAttraction> itineraryAttractions;

    /**
     * Constructs an empty {@code Day} of day {@code dayNumber}.
     *
     * @param dayNumber A valid day number.
     */
    public Day(Integer dayNumber) {
        requireNonNull(dayNumber);
        checkArgument(isValidDayNumber(dayNumber), MESSAGE_CONSTRAINTS);
        value = dayNumber;
        this.itineraryAttractions = new ArrayList<>();
    }

    /**
     * Constructs a {@code Day} of day {@code dayNumber} with the specified itinerary attractions.
     *
     * @param dayNumber            A valid day number.
     * @param itineraryAttractions Itinerary attractions to include in the Day.
     */
    public Day(Integer dayNumber, List<ItineraryAttraction> itineraryAttractions) {
        requireNonNull(dayNumber);
        checkArgument(isValidDayNumber(dayNumber), MESSAGE_CONSTRAINTS);
        value = dayNumber;
        this.itineraryAttractions = itineraryAttractions;
    }

    /**
     * Returns a list of all the itinerary attractions.
     */
    public List<ItineraryAttraction> getItineraryAttractions() {
        return itineraryAttractions;
    }

    /**
     * Adds an itinerary attraction and sorts them based on their start times.
     */
    public void addItineraryAttraction(ItineraryAttraction toAdd) {
        checkArgument(!hasTimingClash(toAdd), "The timing clashes with another attraction in the itinerary");
        itineraryAttractions.add(toAdd);
        itineraryAttractions.sort(new Comparator<ItineraryAttraction>() {
            @Override
            public int compare(ItineraryAttraction first, ItineraryAttraction second) {
                if (first.getStartTime().isEarlierThan(second.getStartTime())) {
                    return -1;
                } else if (first.getStartTime().isLaterThan(second.getStartTime())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    /**
     * Deletes the itinerary attraction specified by the index.
     */
    public void deleteItineraryAttraction(Index index) {
        itineraryAttractions.remove(index.getZeroBased());
    }

    /**
     * Edits the specified itinerary attraction.
     */
    public void editItineraryAttraction(ItineraryAttraction target, ItineraryAttraction editedItineraryAttraction) {
        int index = itineraryAttractions.indexOf(target);
        if (index == -1) {
            throw new ItineraryAttractionNotFoundException();
        }

        if (!target.isSameItineraryAttraction(editedItineraryAttraction) && contains(editedItineraryAttraction)) {
            throw new DuplicateItineraryAttractionException();
        }

        itineraryAttractions.set(index, editedItineraryAttraction);

    }

    /**
     * Returns true if the itinerary attraction is found in the Day.
     */
    public boolean contains(ItineraryAttraction itineraryAttraction) {
        return itineraryAttractions.contains(itineraryAttraction);
    }

    /**
     * Returns true if the itinerary attraction has a timing that clashes with another itinerary attraction already
     * in the Day.
     */
    public boolean hasTimingClash(ItineraryAttraction toCheck) {
        for (ItineraryAttraction itineraryAttraction : itineraryAttractions) {
            if (toCheck.isTimingClash(itineraryAttraction)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the itinerary attraction has a timing that clashes with another itinerary attraction already
     * in the Day.
     * Overloaded method only for EditItineraryAttractionCommand.
     * Does not run toCheck with the itineraryAttraction it is replacing.
     */
    public boolean hasTimingClash(ItineraryAttraction toCheck, ItineraryAttraction toReplace) {
        for (ItineraryAttraction itineraryAttraction : itineraryAttractions) {
            if (toReplace.equals(itineraryAttraction)) {
                continue;
            }
            if (toCheck.isTimingClash(itineraryAttraction)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a given int is a valid day number.
     */
    public static boolean isValidDayNumber(Integer test) {
        return test > 0;
    }

    @Override
    public String toString() {
        return String.format("Day %d", value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && value.equals(((Day) other).value)
                && itineraryAttractions.equals(((Day) other).itineraryAttractions)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, itineraryAttractions);
    }
}
