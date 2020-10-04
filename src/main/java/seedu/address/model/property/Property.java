package seedu.address.model.property;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.Id;
import seedu.address.model.id.IdManager;
import seedu.address.model.price.Price;

/**
 * Represents a Property in the property book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Uniquely identified by the property id.
 */
public class Property {

    private static final String PREFIX = "p";
    // TODO: should be managed somewhere else to access last id in storage
    private static final IdManager ID_MANAGER = new IdManager(PREFIX);

    private final Id propertyId;
    private final PropertyName propertyName;
    private final Id sellerId;
    private final Address address;
    private final Price askingPrice;
    private final PropertyType propertyType;
    private final boolean isRental;
    private final boolean isClosedDeal;

    /**
     * Every field must be present and not null.
     */
    public Property(PropertyName propertyName, Id sellerId, Address address, Price askingPrice,
                    PropertyType propertyType, boolean isRental, boolean isClosedDeal) {
        requireAllNonNull(propertyName, sellerId, address, askingPrice, propertyType, isRental, isClosedDeal);
        this.propertyName = propertyName;
        this.sellerId = sellerId;
        this.address = address;
        this.askingPrice = askingPrice;
        this.propertyType = propertyType;
        this.isRental = isRental;
        this.isClosedDeal = isClosedDeal;
        this.propertyId = ID_MANAGER.getNextId();
    }


}
