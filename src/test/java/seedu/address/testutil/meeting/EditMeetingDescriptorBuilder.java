package seedu.address.testutil.meeting;

import seedu.address.logic.commands.meetingcommands.EditMeetingCommand;
import seedu.address.logic.commands.meetingcommands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.meeting.Venue;

/**
 * A utility class to help with building EditBidderDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {

    private EditMeetingCommand.EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBidderDescriptor} with fields containing {@code bidder}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setMeetingBidderId(meeting.getBidderId());
        descriptor.setMeetingPropertyId(meeting.getPropertyId());
        descriptor.setMeetingDate(meeting.getMeetingDate());
        descriptor.setMeetingVenue(meeting.getVenue());
        descriptor.setMeetingStartTime(meeting.getStartTime());
        descriptor.setMeetingEndTime(meeting.getEndTime());
    }

    /**
     * Sets the {@code Name} of the {@code EditBidderDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withPropertyId(String propertyId) {
        descriptor.setMeetingPropertyId(new PropertyId(propertyId));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditBidderDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withBidderId(String bidderId) {
        descriptor.setMeetingBidderId(new BidderId(bidderId));
        return this;
    }

    /**
     * Parses the {@code id} into a {@code Id} and set it to the {@Code EditBidderDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withVenue(String venue) {
        descriptor.setMeetingVenue(new Venue(venue));
        return this;
    }

    /**
     * Parses the {@code id} into a {@code Id} and set it to the {@Code EditBidderDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withDate(String date) {
        descriptor.setMeetingDate(new MeetingDate(date));
        return this;
    }

    /**
     * Parses the {@code id} into a {@code Id} and set it to the {@Code EditBidderDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withStartTime(String startTime) {
        descriptor.setMeetingStartTime(new StartTime(startTime));
        return this;
    }

    /**
     * Parses the {@code id} into a {@code Id} and set it to the {@Code EditBidderDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withEndTime(String endTime) {
        descriptor.setMeetingEndTime(new EndTime(endTime));
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }
}
