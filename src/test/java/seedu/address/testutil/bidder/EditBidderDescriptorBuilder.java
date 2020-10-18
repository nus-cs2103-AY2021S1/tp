package seedu.address.testutil.bidder;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.biddercommands.EditBidderCommand.EditBidderDescriptor;
import seedu.address.model.id.BidderId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.tag.Tag;

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
        descriptor.setTags(bidder.getTags());
        descriptor.setBidderId(bidder.getId());
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditBidderDescriptor}
     * that we are building.
     */
    public EditBidderDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code id} into a {@code Id} and set it to the {@Code EditBidderDescriptor}
     * that we are building.
     */
    public EditBidderDescriptorBuilder withId(String id) {
        descriptor.setBidderId(new BidderId(id));
        return this;
    }

    public EditBidderDescriptor build() {
        return descriptor;
    }
}
