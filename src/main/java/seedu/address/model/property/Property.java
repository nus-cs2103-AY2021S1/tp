package seedu.address.model.property;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.id.Id;
import seedu.address.model.price.Price;

/**
 * Represents a Property in the property book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Uniquely identified by the property id.
 */
public class Property {

    // Identity fields
    private final Id propertyId;
    private final Address address;

    // Data fields
    private final PropertyName propertyName;
    private final Id sellerId;
    private final Price askingPrice;
    private final PropertyType propertyType;
    private final boolean isRental;
    private final boolean isClosedDeal;

    /**
     * Every field must be present and not null.
     */
    public Property(Id propertyId, PropertyName propertyName, Id sellerId, Address address, Price askingPrice,
                    PropertyType propertyType, boolean isRental, boolean isClosedDeal) {
        requireAllNonNull(propertyName, sellerId, address, askingPrice, propertyType, isRental, isClosedDeal);
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.sellerId = sellerId;
        this.address = address;
        this.askingPrice = askingPrice;
        this.propertyType = propertyType;
        this.isRental = isRental;
        this.isClosedDeal = isClosedDeal;
    }

    public Id getPropertyId() {
        return propertyId;
    }

    public PropertyName getPropertyName() {
        return propertyName;
    }

    public Address getAddress() {
        return address;
    }

    public Id getSellerId() {
        return sellerId;
    }

    public Price getAskingPrice() {
        return askingPrice;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public boolean isRental() {
        return isRental;
    }

    public boolean isClosedDeal() {
        return isClosedDeal;
    }

    /**
     * Returns true if either the property id is the same or if the address is the same.
     *
     * @param otherProperty The other property.
     * @return True if both property objects represent the same property.
     */
    public boolean isSameProperty(Property otherProperty) {
        return this == otherProperty
                || this.getPropertyId().equals(otherProperty.getPropertyId())
                || this.getAddress().equals(otherProperty.getAddress());
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
                && this.isRental() == otherProperty.isRental()
                && this.isClosedDeal() == otherProperty.isClosedDeal();
    }

    @Override
    public int hashCode() {
        return Objects.hash(propertyId, propertyName, address,
                askingPrice, propertyType, sellerId, isRental, isClosedDeal);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (isClosedDeal()) {
            builder.append("[CLOSED] ");
        }
        builder.append(getPropertyName())
                .append(" Property Id: ")
                .append(getPropertyId())
                .append(" Address: ")
                .append(getAddress())
                .append(" Property type: ")
                .append(getPropertyType())
                .append(" Asking price: ")
                .append(getAskingPrice())
                .append(" Seller Id: ")
                .append(getSellerId());
        if (isRental()) {
            builder.append(" Rental");
        }
        return builder.toString();
    }
}
