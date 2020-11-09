package seedu.address.testutil.seller;

import static seedu.address.model.person.seller.Seller.SELLER_TAG;

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
        descriptor.setSellerId((SellerId) seller.getId());
    }

    /**
     * Creates a EditSellerDescriptorBuilder with default id and tag.
     */
    public EditSellerDescriptorBuilder withDefaultIdAndTag() {
        this.descriptor.setSellerId(SellerId.DEFAULT_SELLER_ID);
        this.descriptor.setTag(SELLER_TAG);
        return this;
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
        descriptor.setSellerId(new SellerId(id));
        return this;
    }

    public EditSellerDescriptor build() {
        return descriptor;
    }

}
