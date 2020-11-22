package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The resident index provided is invalid";
    public static final String MESSAGE_INVALID_EVENT_DISPLAYED_INDEX = "The event index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d residents listed!";
    public static final String MESSAGE_EXTRA_PARAMETER = "Extra parameter exists! \n%1$s";
    public static final String MESSAGE_INVALID_BLOCK_ROOM_FORMAT = "BlockRoom must be in this format : <Block><Room>"
            + "\nBlock is a single alphabet, Room is a 3 digit number";
}
