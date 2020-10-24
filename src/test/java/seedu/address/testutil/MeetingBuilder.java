package seedu.address.testutil;

import seedu.address.model.meeting.Time;
import seedu.address.model.meeting.Venue;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_NAME = "P1";
    public static final String DEFAULT_PHONE = "B1";
    public static final String DEFAULT_ID_PREFIX = "Serangoon";
    public static final String DEFAULT_TIME = "12";


    private PropertyId propertyId;
    private BidderId bidderId;
    private Time time;
    private Venue venue;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public MeetingBuilder() {
        propertyId = new PropertyId(DEFAULT_NAME);
        bidderId = new BidderId(DEFAULT_PHONE);
        time = new Time(DEFAULT_TIME);
        venue = new Venue(DEFAULT_ID_PREFIX);
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
