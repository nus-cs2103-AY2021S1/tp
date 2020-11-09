package seedu.address.model.itinerary.exceptions;

/**
 * Signals that the operation will result in duplicate itinerary attractions in an itinerary (Itinerary
 * attractions are considered duplicates if they have the same identity).
 */
public class DuplicateItineraryAttractionException extends RuntimeException {
    public DuplicateItineraryAttractionException() {
        super("Operation would result in duplicate attractions in the itinerary");
    }
}
