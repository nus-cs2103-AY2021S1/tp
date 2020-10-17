package seedu.address.testutil.property;

import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.price.Price;
import seedu.address.model.property.Address;
import seedu.address.model.property.IsClosedDeal;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;

/**
 * A utility class to help with building Property objects.
 */
public class PropertyBuilder {

    public static final int DEFAULT_PROPERTY_ID = 1;
    public static final String DEFAULT_ADDRESS = "123, Ang Mo Kio Drive, #01-23";
    public static final String DEFAULT_PROPERTY_NAME = "Ang Mo Kio Residence";
    public static final int DEFAULT_SELLER_ID = 1;
    public static final double DEFAULT_ASKING_PRICE = 123.45;
    public static final String DEFAULT_PROPERTY_TYPE = "HDB 5 room";
    public static final String DEFAULT_IS_RENTAL = "No";
    public static final String DEFAULT_IS_CLOSED_DEAL = "Active";

    private PropertyId propertyId;
    private Address address;
    private PropertyName propertyName;
    private SellerId sellerId;
    private Price askingPrice;
    private PropertyType propertyType;
    private IsRental isRental;
    private IsClosedDeal isClosedDeal;

    /**
     * Creates a {@code PropertyBuilder} with the default details.
     */
    public PropertyBuilder() {
        propertyId = new PropertyId(DEFAULT_PROPERTY_ID);
        address = new Address(DEFAULT_ADDRESS);
        propertyName = new PropertyName(DEFAULT_PROPERTY_NAME);
        sellerId = new SellerId(DEFAULT_SELLER_ID);
        askingPrice = new Price(DEFAULT_ASKING_PRICE);
        propertyType = new PropertyType(DEFAULT_PROPERTY_TYPE);
        isRental = new IsRental(DEFAULT_IS_RENTAL);
        isClosedDeal = new IsClosedDeal(DEFAULT_IS_CLOSED_DEAL);
    }

    /**
     * Initializes the PropertyBuilder with the data of {@code propertyToCopy}.
     */
    public PropertyBuilder(Property propertyToCopy) {
        propertyId = propertyToCopy.getPropertyId();
        address = propertyToCopy.getAddress();
        propertyName = propertyToCopy.getPropertyName();
        sellerId = propertyToCopy.getSellerId();
        askingPrice = propertyToCopy.getAskingPrice();
        propertyType = propertyToCopy.getPropertyType();
        isRental = propertyToCopy.getIsRental();
        isClosedDeal = propertyToCopy.isClosedDeal();
    }

    /**
     * Sets the {@code propertyId} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPropertyId(String propertyId) {
        this.propertyId = new PropertyId(propertyId);
        return this;
    }


    /**
     * Sets the {@code address} of the {@code Property} that we are building.
     */
    public PropertyBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code propertyName} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPropertyName(String propertyName) {
        this.propertyName = new PropertyName(propertyName);
        return this;
    }

    /**
     * Sets the {@code sellerId} of the {@code Property} that we are building.
     */
    public PropertyBuilder withSellerId(String sellerId) {
        this.sellerId = new SellerId(sellerId);
        return this;
    }

    /**
     * Sets the {@code askingPrice} of the {@code Property} that we are building.
     */
    public PropertyBuilder withAskingPrice(double askingPrice) {
        this.askingPrice = new Price(askingPrice);
        return this;
    }

    /**
     * Sets the {@code propertyType} of the {@code Property} that we are building.
     */
    public PropertyBuilder withPropertyType(String propertyType) {
        this.propertyType = new PropertyType(propertyType);
        return this;
    }

    /**
     * Sets the {@code isRental} of the {@code Property} that we are building.
     */
    public PropertyBuilder withIsRental(String isRental) {
        this.isRental = new IsRental(isRental);
        return this;
    }

    /**
     * Sets the {@code isClosedDeal} of the {@code Property} that we are building.
     */
    public PropertyBuilder withIsClosedDeal(String isClosedDeal) {
        this.isClosedDeal = new IsClosedDeal(isClosedDeal);
        return this;
    }

    /**
     * Builds the property.
     *
     * @return The property.
     */
    public Property build() {
        return new Property(propertyId, propertyName, sellerId, address,
                askingPrice, propertyType, isRental, isClosedDeal);
    }

}
