package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;


/**
 * Represents a meeting in the meeting book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Meeting {

    // Identity fields
    protected boolean isPaperWork;
    protected boolean isViewing;
    protected boolean isAdmin;
    protected final BidderId bidderId;
    protected final PropertyId propertyId;
    protected final MeetingDate meetingDate;
    protected final Venue venue;
    protected final StartTime startTime;
    protected final EndTime endTime;
    protected boolean isMeeting;

    /**
     * Every field must be present and not null.
     */
    public Meeting(BidderId bidderId, PropertyId propertyId, MeetingDate meetingDate, Venue venue,
                   StartTime startTime, EndTime endTime) {
        requireAllNonNull(bidderId, propertyId, meetingDate, venue, startTime, endTime);
        this.bidderId = bidderId;
        this.propertyId = propertyId;
        this.meetingDate = meetingDate;
        this.venue = venue;
        this.startTime = startTime;
        this.endTime = endTime;
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
    public Meeting createMeeting(String type, BidderId bidderId, PropertyId propertyId, MeetingDate meetingDate,
                                 Venue venue, StartTime startTime, EndTime endTime) {
        if (type.equalsIgnoreCase("Paperwork")) {
            return new Paperwork(bidderId, propertyId, meetingDate, venue, startTime, endTime);
        } else if (type.equalsIgnoreCase("Admin")) {
            return new Admin(bidderId, propertyId, meetingDate, venue, startTime, endTime);
        } else if (type.equalsIgnoreCase("Viewing")) {
            return new Viewing(bidderId, propertyId, meetingDate, venue, startTime, endTime);
        } else {
            throw new AssertionError("Invalid meeting type is keyed in.");
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
     * Getter for the meetingDate associated with the meeting.
     */
    public MeetingDate getMeetingDate() {
        return this.meetingDate;
    }

    /**
     * Getter for the venue associated with the meeting.
     */
    public Venue getVenue() {
        return this.venue;
    }

    /**
     * Getter for the startTime associated with the meeting.
     */
    public StartTime getStartTime() {
        return this.startTime;
    }

    /**
     * Getter for the endTime associated with the meeting.
     */
    public EndTime getEndTime() {
        return this.endTime;
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
     * Returns true if either the venue, meetingDate, bidderId and propertyId is the same.
     *
     * @param other The other meeting.
     * @return True if both meeting objects represent the same meeting.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Meeting // instanceof handles nulls
                && this.bidderId.equals(((Meeting) other).getBidderId())
                && this.propertyId.equals(((Meeting) other).getPropertyId())
                && this.meetingDate.equals(((Meeting) other).getMeetingDate())
                && this.venue.equals(((Meeting) other).getVenue())
                && this.startTime.equals(((Meeting) other).getStartTime())
                && this.endTime.equals(((Meeting) other).getEndTime())); // state check
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\nMeeting Type: " + checkMeetingType());
        builder.append("\nBidder Id: ")
                .append(getBidderId())
                .append("\nProperty Id: ")
                .append(getPropertyId())
                .append("\nVenue: ")
                .append(getVenue())
                .append("\nDate: ")
                .append(getMeetingDate())
                .append("\nStart Time: ")
                .append(getStartTime())
                .append("\nEnd Time: ")
                .append(getEndTime());
        return builder.toString();
    }
}

