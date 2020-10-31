package seedu.address.testutil;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.meeting.Venue;



/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_PROPERTY_ID = "P1";
    public static final String DEFAULT_BIDDER_ID = "B1";
    public static final String DEFAULT_VENUE = "Serangoon";
    public static final String DEFAULT_TIME = "10-12-2021";
    public static final String DEFAULT_STARTTIME = "14:00";
    public static final String DEFAULT_ENDTIME = "14:50";

    private PropertyId propertyId;
    private BidderId bidderId;
    private MeetingDate meetingDate;
    private Venue venue;
    private StartTime startTime;
    private EndTime endTime;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public MeetingBuilder() {
        propertyId = new PropertyId(DEFAULT_PROPERTY_ID);
        bidderId = new BidderId(DEFAULT_BIDDER_ID);
        venue = new Venue(DEFAULT_VENUE);
        meetingDate = new MeetingDate(DEFAULT_TIME);
        startTime = new StartTime(DEFAULT_STARTTIME);
        endTime = new EndTime(DEFAULT_ENDTIME);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public MeetingBuilder(Meeting bidderToCopy) {
        propertyId = bidderToCopy.getPropertyId();
        bidderId = bidderToCopy.getBidderId();
        meetingDate = bidderToCopy.getMeetingDate();
        venue = bidderToCopy.getVenue();
        startTime = bidderToCopy.getStartTime();
        endTime = bidderToCopy.getEndTime();
    }

    /**
     * Sets the {@code PropertyId} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withPropertyId(String name) {
        this.propertyId = new PropertyId(name);
        return this;
    }

    /**
     * Parses the {@code meetingDate} into a {@code Set<Tag>} and set it to the {@code Seller} that we are building.
     */
    public MeetingBuilder withTime(String time) {
        this.meetingDate = new MeetingDate(time);
        return this;
    }

    /**
     * Sets the {@code BidderId} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withBidderId(String bidderId) {
        this.bidderId = new BidderId(bidderId);
        return this;
    }

    /**
     * Sets the {@code Venue} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withVenue(String venue) {
        this.venue = new Venue(venue);
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withStartTime(String startTime) {
        this.startTime = new StartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withEndTime(String endTime) {
        this.endTime = new EndTime(endTime);
        return this;
    }

    public Meeting build() {
        return new Meeting(bidderId, propertyId, meetingDate, venue, startTime, endTime);
    }

}
