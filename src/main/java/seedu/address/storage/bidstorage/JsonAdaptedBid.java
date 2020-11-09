package seedu.address.storage.bidstorage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.bid.Bid;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.price.Price;

public class JsonAdaptedBid {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "bid's %s field is missing!";

    private final String propertyId;
    private final String bidderId;
    private final double bidAmount;


    /**
     * constructor to make a JsonAdaptedBid
     * @param propertyId String id of the property
     * @param bidderId String id of the bidder
     * @param bidAmount Amount bidder wants to buy the property for
     */
    @JsonCreator
    public JsonAdaptedBid(@JsonProperty("propertyId") String propertyId,
                          @JsonProperty("bidderId") String bidderId, @JsonProperty("bidAmount") double bidAmount) {
        this.propertyId = propertyId;
        this.bidderId = bidderId;
        this.bidAmount = bidAmount;
    }


    /**
     * Constructor that wraps a bid object into a JsonAdaptedBid
     * @param source bid object to convert
     */
    public JsonAdaptedBid(Bid source) {
        propertyId = source.getPropertyId().toString();
        bidderId = source.getBidderId().toString();
        bidAmount = source.getBidAmount().price;
    }

    /**
     * Converts input into a model type for Bid
     * @return Bid object
     * @throws IllegalValueException in case null value is given
     */
    public Bid toModelType() throws IllegalValueException {

        if (propertyId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "propertyId"));
        }
        if (!PropertyId.isValidId(propertyId)) {
            throw new IllegalValueException(PropertyId.MESSAGE_CONSTRAINTS);
        }
        PropertyId modelPropertyId = new PropertyId(propertyId);

        if (bidderId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "bidderId"));
        }
        if (!BidderId.isValidId(bidderId)) {
            throw new IllegalValueException(BidderId.MESSAGE_CONSTRAINTS);
        }
        BidderId modelBidderId = new BidderId(bidderId);

        if (bidAmount <= 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "bidAmount"));
        }
        Price modelBidAmount = new Price(bidAmount);
        return new Bid(modelPropertyId, modelBidderId, modelBidAmount);
    }
}
