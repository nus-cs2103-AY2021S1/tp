package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;

/**
 * A meeting object where the type if of administration.
 */
public class Admin extends Meeting {

    private static final String PREFIX = "a";

    /**
     *  Constructor for admin meeting.
     * @param bidderId Bidder ID.
     * @param propertyId Property ID.
     * @param time Calendar Time.
     * @param venue Calendar Venue.
     */
    public Admin(BidderId bidderId, PropertyId propertyId, Time time, Venue venue) {
        super(bidderId, propertyId, time, venue);
        requireAllNonNull(bidderId, propertyId, time, venue);
        super.isAdmin = true;
    }

    /**
     * Returns true if either the venue, time, bidderId and propertyId is the same.
     *
     * @param other The other property.
     * @return True if both property objects represent the same meeting.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Admin // instanceof handles nulls
                && this.bidderId.equals(((Admin) other).getBidderId())
                && this.propertyId.equals(((Admin) other).getPropertyId())
                && this.time.equals(((Admin) other).getTime())
                && this.venue.equals(((Admin) other).getVenue())); // state check
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
