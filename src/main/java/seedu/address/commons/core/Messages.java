package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command.";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_START_END_DATE = "Start Date must be before End Date!";

    // person
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid.";
    public static final String MESSAGE_INVALID_PERSON_ID = "PersonId must be a positive integer.";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_PERSON_ID_MISSING = "No existing personId can be found.";

    // room
    public static final String MESSAGE_ROOM_ID_MISSING = "No existing roomId can be be found. "
            + "RoomId should be within 2103 to 2132.";
    public static final String MESSAGE_INVALID_ROOM_TYPE = "Invalid Room Type. Only 1, 2, 3 allowed.\n"
            + "1 for Single Rooms.\n2 for Double Rooms. \n3 for Suite Rooms.";

    // booking
    public static final String MESSAGE_INVALID_BOOKING_DISPLAYED_ID = "The bookingId provided is invalid.";
    public static final String MESSAGE_BOOKINGS_LISTED_OVERVIEW = "%1$d booking listed!";
    public static final String MESSAGE_CONFLICTING_BOOKING = "The room has already been booked during this period.";
    public static final String MESSAGE_BOOKING_MISSING = "No valid booking can be found.";
    public static final String MESSAGE_DUPLICATE_BOOKING = "This booking already exists in the booking book.";
    public static final String MESSAGE_EXCEED_DURATION = "Booking duration cannot be longer than 30 nights.";

}

