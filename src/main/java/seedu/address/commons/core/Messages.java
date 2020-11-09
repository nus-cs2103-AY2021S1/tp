package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    // ------------------------- BIDDER ---------------------------
    public static final String MESSAGE_INVALID_BIDDER_DISPLAYED_INDEX = "The bidder index provided is invalid";
    public static final String MESSAGE_BIDDERS_LISTED_OVERVIEW = "%1$d bidder(s) listed!";

    // ------------------------- SELLER ---------------------------
    public static final String MESSAGE_INVALID_SELLER_DISPLAYED_INDEX = "The seller index provided is invalid";
    public static final String MESSAGE_SELLERS_LISTED_OVERVIEW = "%1$d seller(s) listed!";

    // ------------------------- PROPERTY -------------------------
    public static final String MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX = "The property index provided is invalid";
    public static final String MESSAGE_PROPERTY_LISTED_OVERVIEW = "%1$d properties listed!";

    // ------------------------- MEETING -------------------------
    public static final String MESSAGE_INVALID_MEETING_DISPLAYED_INDEX = "The meeting index provided is invalid";
    public static final String MESSAGE_MEETINGS_LISTED_OVERVIEW = "%1$d meeting(s) listed!";
    public static final String MESSAGE_INVALID_ID = "Either the bidder or the property id does not exist!";
    public static final String MESSAGE_DUPLICATE_MEETING = "An identical meeting already exists in the meeting book";


    // ------------------------- BID -------------------------
    public static final String MESSAGE_INVALID_BID_DISPLAYED_INDEX = "The bid index provided is invalid";
    public static final String MESSAGE_INVALID_COMMAND_INPUT = "Invalid command input! \n%1$s";
    public static final String MESSAGE_BIDS_LISTED_OVERVIEW = "%1$d bid(s) listed!";
    public static final String MESSAGE_BIDS_LISTED_FAILURE = "Unable to find any related Bids. Displaying full list.";

}
