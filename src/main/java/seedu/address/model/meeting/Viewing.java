package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;

/**
 * A meeting object where the type if of viewing.
 */
public class Viewing extends Meeting {

    private static final String PREFIX = "v";

    /**
     *  Constructor for viewing meeting.
     * @param bidderId Bidder ID.
     * @param propertyId Property ID.
     * @param time Time.
     * @param venue Venue.
     */
    public Viewing(BidderId bidderId, PropertyId propertyId, Time time, Venue venue) {
        super(bidderId, propertyId, time, venue);
        requireAllNonNull(bidderId, propertyId, time, venue);
        super.isViewing = true;
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
                || (other instanceof Viewing // instanceof handles nulls
                && this.bidderId.equals(((Viewing) other).getBidderId())
                && this.propertyId.equals(((Viewing) other).getPropertyId())
                && this.time.equals(((Viewing) other).getTime())
                && this.venue.equals(((Viewing) other).getVenue())); // state check
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
