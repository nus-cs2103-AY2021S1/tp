package seedu.address.testutil.property;

import seedu.address.logic.commands.property.EditPropertyCommand.EditPropertyDescriptor;
import seedu.address.model.id.SellerId;
import seedu.address.model.price.Price;
import seedu.address.model.property.Address;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;

/**
 * A utility class to help with building EditPropertyDescriptor objects.
 */
public class EditPropertyDescriptorBuilder {

    private EditPropertyDescriptor descriptor;

    public EditPropertyDescriptorBuilder() {
        descriptor = new EditPropertyDescriptor();
    }

    public EditPropertyDescriptorBuilder(EditPropertyDescriptor descriptor) {
        this.descriptor = new EditPropertyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPropertyDescriptor} with fields containing {@code property}'s details
     */
    public EditPropertyDescriptorBuilder(Property property) {
        descriptor = new EditPropertyDescriptor();
        descriptor.setPropertyName(property.getPropertyName());
        descriptor.setAddress(property.getAddress());
        descriptor.setPropertyType(property.getPropertyType());
        descriptor.setSellerId(property.getSellerId());
        descriptor.setAskingPrice(property.getAskingPrice());
        descriptor.setIsRental(property.getIsRental());
        descriptor.setPropertyId(property.getPropertyId());
    }

    /**
     * Sets the {@code PropertyName} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withPropertyName(String propertyName) {
        descriptor.setPropertyName(new PropertyName(propertyName));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code PropertyType} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withPropertyType(String propertyType) {
        descriptor.setPropertyType(new PropertyType(propertyType));
        return this;
    }

    /**
     * Sets the {@code SellerId} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withSellerId(String sellerId) {
        descriptor.setSellerId(new SellerId(sellerId));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withAskingPrice(double askingPrice) {
        descriptor.setAskingPrice(new Price(askingPrice));
        return this;
    }

    /**
     * Sets the {@code IsRental} of the {@code EditPropertyDescriptor} that we are building.
     */
    public EditPropertyDescriptorBuilder withIsRental(String isRental) {
        descriptor.setIsRental(new IsRental(isRental));
        return this;
    }

    public EditPropertyDescriptor build() {
        return descriptor;
    }
}
