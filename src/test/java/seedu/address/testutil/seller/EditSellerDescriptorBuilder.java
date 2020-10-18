package seedu.address.testutil.seller;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.sellercommands.EditSellerCommand.EditSellerDescriptor;
import seedu.address.model.id.Id;
import seedu.address.model.id.SellerId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditSellerDescriptor objects.
 */
public class EditSellerDescriptorBuilder {

    private EditSellerDescriptor descriptor;

    public EditSellerDescriptorBuilder() {
        descriptor = new EditSellerDescriptor();
    }

    public EditSellerDescriptorBuilder(EditSellerDescriptor descriptor) {
        this.descriptor = new EditSellerDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditSellerDescriptor} with fields containing {@code seller}'s details
     */
    public EditSellerDescriptorBuilder(Seller seller) {
        descriptor = new EditSellerDescriptor();
        descriptor.setName(seller.getName());
        descriptor.setPhone(seller.getPhone());
        descriptor.setTags(seller.getTags());
        descriptor.setId(seller.getId());
    }

    /**
     * Sets the {@code Name} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditSellerDescriptor} that we are building.
     */
    public EditSellerDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditSellerDescriptor}
     * that we are building.
     */
    public EditSellerDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code id} into a {@code Id} and set it to the {@Code EditSellerDescriptor}
     * that we are building.
     */
    public EditSellerDescriptorBuilder withId(String id) {
        descriptor.setId(new SellerId(id));
        return this;
    }

    public EditSellerDescriptor build() {
        return descriptor;
    }
}
