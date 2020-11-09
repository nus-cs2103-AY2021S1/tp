package seedu.address.testutil.bids;

import seedu.address.logic.commands.bidcommands.EditBidCommand.EditBidDescriptor;
import seedu.address.model.bid.Bid;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.price.Price;

/**
 * A utility class to help with building EditBidDescriptor objects.
 */
public class EditBidDescriptorBuilder {

    private EditBidDescriptor descriptor;

    public EditBidDescriptorBuilder() {
        descriptor = new EditBidDescriptor();
    }

    public EditBidDescriptorBuilder(EditBidDescriptor descriptor) {
        this.descriptor = new EditBidDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditBidDescriptor} with fields containing {@code bid}'s details
     */
    public EditBidDescriptorBuilder(Bid bid) {
        descriptor = new EditBidDescriptor();
        descriptor.setPropertyId(bid.getPropertyId());
        descriptor.setBidderId(bid.getBidderId());
        descriptor.setBidAmount(bid.getBidAmount());
    }

    /**
     * Sets the {@code PropertyId} of the {@code EditBidDescriptor} that we are building.
     */
    public EditBidDescriptorBuilder withPropertyId(String id) {
        descriptor.setPropertyId(new PropertyId(id));
        return this;
    }

    /**
     * Sets the {@code BidderId} of the {@code EditBidDescriptor} that we are building.
     */
    public EditBidDescriptorBuilder withBidderId(String id) {
        descriptor.setBidderId(new BidderId(id));
        return this;
    }

    /**
     * Parses the {@code BidAmount} and set it to the {@code EditBidDescriptor}
     * that we are building.
     */
    public EditBidDescriptorBuilder withBidAmount(double amt) {
        descriptor.setBidAmount(new Price(amt));
        return this;
    }

    public EditBidDescriptor build() {
        return descriptor;
    }
}
