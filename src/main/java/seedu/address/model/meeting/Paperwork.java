package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;

/**
 * A meeting object where the type of it is of paperwork.
 */
public class Paperwork extends Meeting {

    /**
     * Constructor for paperwork meeting.
     * @param bidderId Bidder ID.
     * @param propertyId Property ID.
     * @param time Time.
     * @param venue Venue.
     */
    public Paperwork(BidderId bidderId, PropertyId propertyId,
                     Time time, Venue venue) {
        super(bidderId, propertyId, time, venue);
        requireAllNonNull(bidderId, propertyId, time, venue);
        super.isPaperWork = true;
    }

    /**
     * Returns true if either the venue, time, bidderId and propertyId is the same.
     *
     * @param other The other meeting.
     * @return True if both meeting objects represent the same meeting.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Paperwork // instanceof handles nulls
                && this.bidderId.equals(((Paperwork) other).getBidderId())
                && this.propertyId.equals(((Paperwork) other).getPropertyId())
                && this.time.equals(((Paperwork) other).getTime())
                && this.venue.equals(((Paperwork) other).getVenue())); // state check
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
