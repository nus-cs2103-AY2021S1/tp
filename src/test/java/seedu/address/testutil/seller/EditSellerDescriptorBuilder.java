package seedu.address.testutil.seller;

import seedu.address.logic.commands.sellercommands.EditSellerCommand.EditSellerDescriptor;
import seedu.address.model.id.SellerId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.seller.Seller;

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
        descriptor.setTag(seller.getTag());
        descriptor.setId((SellerId) seller.getId());
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

    public EditSellerDescriptor buildWithDefaultId() {
        descriptor.setId(SellerId.DEFAULT_SELLER_ID);
        return descriptor;
    }
}
