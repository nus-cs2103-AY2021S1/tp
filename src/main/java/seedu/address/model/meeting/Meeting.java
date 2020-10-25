package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;


/**
 * Represents a meeting in the meeting book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Meeting {

    // Identity fields
    protected boolean isPaperWork;
    protected boolean isViewing;
    protected boolean isAdmin;
    protected final BidderId bidderId;
    protected final PropertyId propertyId;
    protected final Time time;
    protected final Venue venue;
    protected boolean isMeeting;

    /**
     * Every field must be present and not null.
     */
    public Meeting(BidderId bidderId, PropertyId propertyId, Time time, Venue venue) {
        requireAllNonNull(bidderId, propertyId, time, venue);
        this.bidderId = bidderId;
        this.propertyId = propertyId;
        this.time = time;
        this.venue = venue;
        this.isMeeting = true;
        this.isPaperWork = false;
        this.isViewing = false;
        this.isAdmin = false;
    }

    /**
     * Checks the type of meeting.
     * @return String containing the correct meeting type.
     */
    public String checkMeetingType() {
        if (isPaperWork) {
            return "Paperwork";
        } else if (isViewing) {
            return "Viewing";
        } else if (isAdmin) {
            return "Admin";
        } else {
            //throw new AssertionError("no such meeting type");
            return "General";
        }
    }

    /**
     * Creates the type of meeting based on the meeting type.
     * @return Meeting of the specific meeting type.
     */
    public Meeting createMeeting(String type, BidderId bidderId, PropertyId propertyId, Time time, Venue venue) {
        if (type.equalsIgnoreCase("Paperwork")) {
            return new Paperwork(bidderId, propertyId, time, venue);
        } else if (type.equalsIgnoreCase("Admin")) {
            return new Admin(bidderId, propertyId, time, venue);
        } else if (type.equalsIgnoreCase("Viewing")) {
            return new Viewing(bidderId, propertyId, time, venue);
        } else {
            return new Meeting(bidderId, propertyId, time, venue);
        }
    }

    /**
     * Getter for the bidder id associated with the meeting.
     */
    public BidderId getBidderId() {
        return this.bidderId;
    }

    /**
     * Getter for the property id associated with the meeting.
     */
    public PropertyId getPropertyId() {
        return this.propertyId;
    }

    /**
     * Getter for the time associated with the meeting.
     */
    public Time getTime() {
        return this.time;
    }

    /**
     * Getter for the venue associated with the meeting.
     */
    public Venue getVenue() {
        return this.venue;
    }

    /**
     * Checks if the meeting type is of a viewing.
     */
    public boolean isViewing() {
        return this.isViewing;
    }

    /**
     * Checks if the meeting type is of paperwork.
     */
    public boolean isPaperWork() {
        return this.isPaperWork;
    }

    /**
     * Checks if the meeting type is of administration.
     */
    public boolean isAdmin() {
        return this.isAdmin;
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
                || (other instanceof Meeting // instanceof handles nulls
                && this.bidderId.equals(((Meeting) other).getBidderId())
                && this.propertyId.equals(((Meeting) other).getPropertyId())
                && this.time.equals(((Meeting) other).getTime())
                && this.venue.equals(((Meeting) other).getVenue())); // state check
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(checkMeetingType());

        builder.append(" Bidder Id: ")
                .append(getBidderId())
                .append(" Property Id: ")
                .append(getPropertyId())
                .append(" Venue: ")
                .append(getVenue())
                .append(" Time: ")
                .append(getTime());
        return builder.toString();
    }
}

