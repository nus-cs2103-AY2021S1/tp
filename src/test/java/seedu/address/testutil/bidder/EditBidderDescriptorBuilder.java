package seedu.address.testutil.bidder;

import static seedu.address.model.person.bidder.Bidder.BIDDER_TAG;

import seedu.address.logic.commands.biddercommands.EditBidderCommand.EditBidderDescriptor;
import seedu.address.model.id.BidderId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.bidder.Bidder;

/**
 * A utility class to help with building EditBidderDescriptor objects.
 */
public class EditBidderDescriptorBuilder {

    private EditBidderDescriptor descriptor;

    public EditBidderDescriptorBuilder() {
        descriptor = new EditBidderDescriptor();
    }

    public EditBidderDescriptorBuilder(EditBidderDescriptor descriptor) {
        this.descriptor = new EditBidderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBidderDescriptor} with fields containing {@code bidder}'s details
     */
    public EditBidderDescriptorBuilder(Bidder bidder) {
        descriptor = new EditBidderDescriptor();
        descriptor.setName(bidder.getName());
        descriptor.setPhone(bidder.getPhone());
        descriptor.setTag(bidder.getTag());
        descriptor.setBidderId((BidderId) bidder.getId());
    }

    /**
     * Creates a EditBidderDescriptorBuilder with default id and tag.
     *
     */
    public EditBidderDescriptorBuilder withDefaultIdAndTag() {
        descriptor.setBidderId(BidderId.DEFAULT_BIDDER_ID);
        descriptor.setTag(BIDDER_TAG);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditBidderDescriptor} that we are building.
     */
    public EditBidderDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditBidderDescriptor} that we are building.
     */
    public EditBidderDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Parses the {@code id} into a {@code Id} and set it to the {@Code EditBidderDescriptor}
     * that we are building.
     */
    public EditBidderDescriptorBuilder withBidderId(String id) {
        descriptor.setBidderId(new BidderId(id));
        return this;
    }

    public EditBidderDescriptor build() {
        return descriptor;
    }
}
