package seedu.address.storage.property;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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
 * Jackson-friendly version of {@link Property}.
 */
class JsonAdaptedProperty {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Property's %s field is missing!";

    private final String propertyId;
    private final String propertyName;
    private final String address;
    private final String sellerId;
    private final String propertyType;
    private final String askingPrice;
    private final String isRental;
    private final String isClosedDeal;

    /**
     * Constructs a {@code Property} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedProperty(@JsonProperty("propertyId") String propertyId,
                               @JsonProperty("propertyName") String propertyName,
                               @JsonProperty("address") String address,
                             @JsonProperty("sellerId") String sellerId,
                             @JsonProperty("propertyType") String propertyType,
                             @JsonProperty("askingPrice") String askingPrice,
                             @JsonProperty("isRental") String isRental,
                             @JsonProperty("isClosedDeal") String isClosedDeal) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.address = address;
        this.sellerId = sellerId;
        this.propertyType = propertyType;
        this.askingPrice = askingPrice;
        this.isRental = isRental;
        this.isClosedDeal = isClosedDeal;
    }

    /**
     * Converts a given {@code Property} into this class for Jackson use.
     */
    public JsonAdaptedProperty(Property source) {
        propertyId = source.getPropertyId().toString();
        propertyName = source.getPropertyName().toString();
        address = source.getAddress().toString();
        sellerId = source.getSellerId().toString();
        propertyType = source.getPropertyType().toString();
        askingPrice = String.valueOf(source.getAskingPrice().price);
        isRental = source.getIsRental().toString();
        isClosedDeal = source.isClosedDeal().toString();
    }

    /** Converts the Json propertyId string into the model's {@code PropertyId} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted id.
     */
    private PropertyId toModelPropertyId() throws IllegalValueException {
        if (propertyId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PropertyId.class.getSimpleName()));
        }
        if (!PropertyId.isValidId(propertyId)) {
            throw new IllegalValueException(PropertyId.MESSAGE_CONSTRAINTS);
        }
        return new PropertyId(propertyId);
    }

    /** Converts the Json propertyName string into the model's {@code PropertyName} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted PropertyName.
     */
    private PropertyName toModelPropertyName() throws IllegalValueException {
        if (propertyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PropertyName.class.getSimpleName()));
        }
        if (!PropertyName.isValidPropertyName(propertyName)) {
            throw new IllegalValueException(PropertyName.MESSAGE_CONSTRAINTS);
        }
        return new PropertyName(propertyName);
    }

    /** Converts the Json address string into the model's {@code Address} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Address.
     */
    private Address toModelAddress() throws IllegalValueException {
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(address);
    }

    /** Converts the Json sellerId string into the model's {@code SellerId} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Id.
     */
    private SellerId toModelSellerId() throws IllegalValueException {
        if (sellerId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SellerId.class.getSimpleName()));
        }
        if (!SellerId.isValidId(sellerId)) {
            throw new IllegalValueException(SellerId.MESSAGE_CONSTRAINTS);
        }
        return new SellerId(sellerId);
    }

    /** Converts the Json propertyType string into the model's {@code PropertyType} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted PropertyType.
     */
    private PropertyType toModelPropertyType() throws IllegalValueException {
        if (propertyType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PropertyType.class.getSimpleName()));
        }
        if (!PropertyType.isValidPropertyType(propertyType)) {
            throw new IllegalValueException(PropertyType.MESSAGE_CONSTRAINTS);
        }
        return new PropertyType(propertyType);
    }

    /** Converts the Json askingPrice string into the model's {@code Price} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Price.
     */
    private Price toModelAskingPrice() throws IllegalValueException {
        if (askingPrice == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Price.class.getSimpleName()));
        }
        try {
            if (!Price.isValidPrice(Double.parseDouble(askingPrice))) {
                throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }

        return new Price(Double.parseDouble(askingPrice));
    }

    /** Converts the Json isRental string into the model's {@code IsRental} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted IsRental.
     */
    private IsRental toModelIsRental() throws IllegalValueException {
        if (isRental == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    IsRental.class.getSimpleName()));
        }
        if (!IsRental.isValidIsRental(isRental)) {
            throw new IllegalValueException(IsRental.MESSAGE_CONSTRAINTS);
        }
        return new IsRental(isRental);
    }

    /** Converts the Json isClosedDeal string into the model's boolean object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted isClosedDeal.
     */
    private IsClosedDeal toModelIsClosedDeal() throws IllegalValueException {
        if (isClosedDeal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    IsClosedDeal.class.getSimpleName()));
        }
        if (!IsClosedDeal.isValidIsClosedDeal(isClosedDeal)) {
            throw new IllegalValueException(IsClosedDeal.MESSAGE_CONSTRAINTS);
        }
        return new IsClosedDeal(isClosedDeal);
    }

    /**
     * Converts this Jackson-friendly adapted property object into the model's {@code Property} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public Property toModelType() throws IllegalValueException {

        final PropertyId modelPropertyId = toModelPropertyId();
        final PropertyName modelPropertyName = toModelPropertyName();
        final Address modelAddress = toModelAddress();
        final SellerId modelSellerId = toModelSellerId();
        final PropertyType modelPropertyType = toModelPropertyType();
        final Price modelAskingPrice = toModelAskingPrice();
        final IsRental modelIsRental = toModelIsRental();
        final IsClosedDeal modelIsClosedDeal = toModelIsClosedDeal();
        return new Property(modelPropertyId, modelPropertyName, modelSellerId, modelAddress,
                modelAskingPrice, modelPropertyType, modelIsRental, modelIsClosedDeal);
    }

}
