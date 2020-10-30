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
     * @param date Date.
     * @param venue Venue.
     * @param startTime Start Time.
     * @param endTime End Time.
     */
    public Paperwork(BidderId bidderId, PropertyId propertyId,
                     Date date, Venue venue, StartTime startTime, EndTime endTime) {
        super(bidderId, propertyId, date, venue, startTime, endTime);
        requireAllNonNull(bidderId, propertyId, date, venue, startTime, endTime);
        super.isPaperWork = true;
    }

    /**
     * Returns true if either the venue, date, bidderId, start time, end time and propertyId is the same.
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
                && this.date.equals(((Paperwork) other).getDate())
                && this.venue.equals(((Paperwork) other).getVenue())
                && this.startTime.equals(((Paperwork) other).getStartTime())
                && this.endTime.equals(((Paperwork) other).getEndTime())); // state check
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
