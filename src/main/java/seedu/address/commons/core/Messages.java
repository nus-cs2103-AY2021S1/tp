package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n%1$s";
    public static final String MESSAGE_INVALID_ATTRACTION_DISPLAYED_INDEX = "The attraction index provided is invalid.";
    public static final String MESSAGE_ATTRACTIONS_LISTED_OVERVIEW = "%1$d attractions listed!";
    public static final String MESSAGE_ATTRACTION_LISTED_OVERVIEW = "%1$d attraction listed!";
    public static final String MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX = "The itinerary index provided is invalid.";
    public static final String MESSAGE_ITINERARIES_LISTED_OVERVIEW = "%1$d itineraries listed!";
    public static final String MESSAGE_ITINERARY_LISTED_OVERVIEW = "%1$d itinerary listed!";
    public static final String MESSAGE_ITINERARY_NOT_SELECTED = "Please select an itinerary to work on.";

    // Itinerary Attraction
    public static final String MESSAGE_DUPLICATE_ATTRACTION = "This attraction with the same start time, end time and "
            + "day visiting already exist in the itinerary.";
    public static final String MESSAGE_TIMING_CLASH = "The timing clashes with another attraction in the itinerary.";
    public static final String MESSAGE_INVALID_ITINERARY_DAY = "Your day does not exist in the itinerary.";
    public static final String MESSAGE_INVALID_START_TIME = "The start time must come before the end time.";

}
