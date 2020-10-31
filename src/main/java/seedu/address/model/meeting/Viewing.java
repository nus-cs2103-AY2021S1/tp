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
     * @param meetingDate MeetingDate.
     * @param venue Venue.
     * @param startTime Start Time.
     * @param endTime End Time.
     */
    public Viewing(BidderId bidderId, PropertyId propertyId, MeetingDate meetingDate, Venue venue,
                   StartTime startTime, EndTime endTime) {
        super(bidderId, propertyId, meetingDate, venue, startTime, endTime);
        requireAllNonNull(bidderId, propertyId, meetingDate, venue, startTime, endTime);
        super.isViewing = true;
    }

    /**
     * Returns true if either the venue, meetingDate, bidderId, start time, end time and propertyId is the same.
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
                && this.meetingDate.equals(((Viewing) other).getMeetingDate())
                && this.venue.equals(((Viewing) other).getVenue())
                && this.startTime.equals(((Viewing) other).getStartTime())
                && this.endTime.equals(((Viewing) other).getEndTime())); // state check
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
