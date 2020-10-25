package seedu.address.testutil;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.Venue;



/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_PROPERTY_ID = "P1";
    public static final String DEFAULT_BIDDER_ID = "B1";
    public static final String DEFAULT_VENUE = "Serangoon";
    public static final String DEFAULT_TIME = "10-12-2021";


    private PropertyId propertyId;
    private BidderId bidderId;
    private Time time;
    private Venue venue;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public MeetingBuilder() {
        propertyId = new PropertyId(DEFAULT_PROPERTY_ID);
        bidderId = new BidderId(DEFAULT_BIDDER_ID);
        venue = new Venue(DEFAULT_VENUE);
        time = new Time(DEFAULT_TIME);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public MeetingBuilder(Meeting bidderToCopy) {
        propertyId = bidderToCopy.getPropertyId();
        bidderId = bidderToCopy.getBidderId();
        time = bidderToCopy.getTime();
        venue = bidderToCopy.getVenue();
    }

    /**
     * Sets the {@code PropertyId} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withPropertyId(String name) {
        this.propertyId = new PropertyId(name);
        return this;
    }

    /**
     * Parses the {@code time} into a {@code Set<Tag>} and set it to the {@code Seller} that we are building.
     */
    public MeetingBuilder withTime(String time) {
        this.time = new Time(time);
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

    public Meeting build() {
        return new Meeting(bidderId, propertyId, time, venue);
    }

}
