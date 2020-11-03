package seedu.address.model.property;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.id.PropertyId.DEFAULT_PROPERTY_ID;

import java.util.Objects;

import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.price.Price;

/**
 * Represents a Property in the property book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Uniquely identified by the property id.
 */
public class Property {

    // Identity fields
    private final PropertyId propertyId;
    private final Address address;

    // Data fields
    private final PropertyName propertyName;
    private final SellerId sellerId;
    private final Price askingPrice;
    private final PropertyType propertyType;
    private final IsRental isRental;
    private final IsClosedDeal isClosedDeal;

    /**
     * Every field must be present and not null.
     */
    public Property(PropertyId propertyId, PropertyName propertyName, SellerId sellerId, Address address,
                    Price askingPrice, PropertyType propertyType, IsRental isRental, IsClosedDeal isClosedDeal) {
        requireAllNonNull(propertyId, propertyName, sellerId, address, askingPrice, propertyType,
                isRental, isClosedDeal);
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.sellerId = sellerId;
        this.address = address;
        this.askingPrice = askingPrice;
        this.propertyType = propertyType;
        this.isRental = isRental;
        this.isClosedDeal = isClosedDeal;
    }

    /**
     * Every field must be present and not null.
     */
    public Property(PropertyName propertyName, SellerId sellerId, Address address, Price askingPrice,
                    PropertyType propertyType, IsRental isRental, IsClosedDeal isClosedDeal) {
        requireAllNonNull(propertyName, sellerId, address, askingPrice, propertyType, isRental, isClosedDeal);
        this.propertyId = DEFAULT_PROPERTY_ID;
        this.propertyName = propertyName;
        this.sellerId = sellerId;
        this.address = address;
        this.askingPrice = askingPrice;
        this.propertyType = propertyType;
        this.isRental = isRental;
        this.isClosedDeal = isClosedDeal;
    }

    /**
     * Creates a new Property with the specified id.
     *
     * @param propertyId The specified id.
     * @return The new property.
     */
    public Property setId(PropertyId propertyId) {
        return new Property(propertyId, propertyName, sellerId, address, askingPrice, propertyType,
                isRental, isClosedDeal);
    }

    public PropertyId getPropertyId() {
        return propertyId;
    }

    public PropertyName getPropertyName() {
        return propertyName;
    }

    public Address getAddress() {
        return address;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public Price getAskingPrice() {
        return askingPrice;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public IsRental getIsRental() {
        return isRental;
    }

    public boolean isRental() {
        return isRental.isRental();
    }

    public IsClosedDeal isClosedDeal() {
        return isClosedDeal;
    }

    /**
     * Returns true if either the property id is the same or if the address is the same.
     *
     * @param otherProperty The other property.
     * @return True if both property objects represent the same property.
     */
    public boolean isSameProperty(Property otherProperty) {
        return otherProperty != null && (this == otherProperty
                || this.getPropertyId().equals(otherProperty.getPropertyId())
                || this.getAddress().equals(otherProperty.getAddress()));
    }

    /**
     * Returns true if both properties have the same identity and data fields.
     * This defines a stronger notion of equality between two properties.
     *
     * @param obj The other object.
     * @return True if both properties are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Property)) {
            return false;
        }

        Property otherProperty = (Property) obj;
        return this.getPropertyId().equals(otherProperty.getPropertyId())
                && this.getAddress().equals(otherProperty.getAddress())
                && this.getAskingPrice().equals(otherProperty.getAskingPrice())
                && this.getPropertyName().equals(otherProperty.getPropertyName())
                && this.getPropertyType().equals(otherProperty.getPropertyType())
                && this.getSellerId().equals(otherProperty.getSellerId())
                && this.isRental() == (otherProperty.isRental())
                && this.isClosedDeal().equals(otherProperty.isClosedDeal());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (isClosedDeal().isClosedDeal) {
            builder.append("[CLOSED]\n");
        }
        builder.append(getPropertyName())
                .append("\nProperty Id: ")
                .append(getPropertyId())
                .append("\nAddress: ")
                .append(getAddress())
                .append("\nProperty type: ")
                .append(getPropertyType())
                .append("\nAsking price: ")
                .append(getAskingPrice())
                .append("\nSeller Id: ")
                .append(getSellerId());
        if (isRental()) {
            builder.append("\nRental");
        }
        return builder.toString();
    }
}
