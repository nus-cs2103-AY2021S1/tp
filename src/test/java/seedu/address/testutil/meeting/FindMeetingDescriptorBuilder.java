package seedu.address.testutil.meeting;

import java.util.Arrays;

import seedu.address.logic.commands.meetingcommands.FindMeetingCommand;
import seedu.address.logic.commands.meetingcommands.FindMeetingCommand.FindMeetingDescriptor;
import seedu.address.model.meeting.BidderIdContainsKeywordsPredicate;
import seedu.address.model.meeting.DateContainsKeywordsPredicate;
import seedu.address.model.meeting.EndTime;
import seedu.address.model.meeting.EndTimeContainsKeywordsPredicate;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.meeting.StartTime;
import seedu.address.model.meeting.StartTimeContainsKeywordsPredicate;
import seedu.address.model.meeting.VenueContainsKeywordsPredicate;

/**
 * A utility class to help with building FindPropertyDescriptor objects.
 * @author christophermervyn
 */
public class FindMeetingDescriptorBuilder {

    private FindMeetingCommand.FindMeetingDescriptor descriptor;

    public FindMeetingDescriptorBuilder() {
        descriptor = new FindMeetingDescriptor();
    }

    public FindMeetingDescriptorBuilder(FindMeetingDescriptor descriptor) {
        this.descriptor = new FindMeetingDescriptor(descriptor);
    }

    /**
     * Sets the {@code PropertyNameContainsKeywordsPredicate} of the {@code FindPropertyDescriptor}
     * that we are building.
     */
    public FindMeetingDescriptorBuilder withMeetingPropertyIdPredicate(String keywords) {
        descriptor.setPropertyIdContainsKeywordsPredicate(
                new PropertyIdContainsKeywordsPredicate(
                        Arrays.asList(keywords.split("\\s+")))
        );
        return this;
    }

    /**
     * Sets the {@code PropertyAddressContainsKeywordsPredicate} of the {@code FindPropertyDescriptor}
     * that we are building.
     */
    public FindMeetingDescriptorBuilder withMeetingBidderIdPredicate(String keywords) {
        descriptor.setBidderIdContainsKeywordsPredicate(
                new BidderIdContainsKeywordsPredicate(
                        Arrays.asList(keywords.split("\\s+")))
        );
        return this;
    }

    /**
     * Sets the {@code PropertyTypeContainsKeywordsPredicate} of the {@code FindPropertyDescriptor}
     * that we are building.
     */
    public FindMeetingDescriptorBuilder withMeetingVenuePredicate(String keywords) {
        descriptor.setVenueContainsKeywordsPredicate(
                new VenueContainsKeywordsPredicate(
                        Arrays.asList(keywords.split("\\s+")))
        );
        return this;
    }

    /**
     * Sets the {@code SellerIdContainsKeywordsPredicate} of the {@code FindPropertyDescriptor}
     * that we are building.
     */
    public FindMeetingDescriptorBuilder withMeetingDatePredicate(String keywords) {
        descriptor.setDateContainsKeywordsPredicate(
                new DateContainsKeywordsPredicate(new MeetingDate(keywords))
        );
        return this;
    }

    /**
     * Sets the {@code AskingPricePredicate} of the {@code FindPropertyDescriptor} that we are building.
     */
    public FindMeetingDescriptorBuilder withMeetingStartTimePredicate(String keywords) {
        descriptor.setStartTimeContainsKeywordsPredicate(
                new StartTimeContainsKeywordsPredicate(new StartTime(keywords))
        );
        return this;
    }

    /**
     * Sets the {@code PropertyIsRentalPredicate} of the {@code FindPropertyDescriptor} that we are building.
     */
    public FindMeetingDescriptorBuilder withMeetingEndTimePredicate(String keywords) {
        descriptor.setEndTimeContainsKeywordsPredicate(
                new EndTimeContainsKeywordsPredicate(new EndTime(keywords))
        );
        return this;
    }

    public FindMeetingDescriptor build() {
        return descriptor;
    }
}
