package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.calendar.CalendarBidderId;
import seedu.address.model.calendar.CalendarMeeting;
import seedu.address.model.calendar.CalendarPropertyId;
import seedu.address.model.calendar.CalendarTime;
import seedu.address.model.calendar.CalendarVenue;
import seedu.address.model.id.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ID_PREFIX = "Serangoon";
    public static final String DEFAULT_TIME = "12";


    private CalendarPropertyId name;
    private CalendarBidderId phone;
    private CalendarTime tags;
    private CalendarVenue venue;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public MeetingBuilder() {
        name = new CalendarPropertyId(DEFAULT_NAME);
        phone = new CalendarBidderId(DEFAULT_PHONE);
        tags = new CalendarTime(DEFAULT_TIME);
        venue = new CalendarVenue(DEFAULT_ID_PREFIX);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public MeetingBuilder(CalendarMeeting bidderToCopy) {
        name = bidderToCopy.getCalendarPropertyId();
        phone = bidderToCopy.getCalendarBidderId();
        tags = bidderToCopy.getCalendarTime();
        venue = bidderToCopy.getCalendarVenue();
    }

    /**
     * Sets the {@code Name} of the {@code Seller} that we are building.
     */
    public MeetingBuilder withName(String name) {
        this.name = new CalendarPropertyId(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Seller} that we are building.
     */
    public MeetingBuilder withTags(String venue) {
        this.tags = new CalendarTime(venue);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Seller} that we are building.
     */
    public MeetingBuilder withPhone(String phone) {
        this.phone = new CalendarBidderId(phone);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Seller} that we are building.
     */
    public MeetingBuilder withId(String prefix, int number) {
        this.venue = new CalendarVenue(prefix);
        return this;
    }

    public CalendarMeeting build() {
        return new CalendarMeeting(phone, name, tags, venue);
    }

}
