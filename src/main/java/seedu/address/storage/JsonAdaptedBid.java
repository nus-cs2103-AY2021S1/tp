package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.bid.Bid;

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
        propertyId = source.getPropertyId();
        bidderId = source.getBidderId();
        bidAmount = source.getBidAmount();
    }

    /**
     * Converts input into a model type for Bid
     * @return Bid object
     * @throws IllegalValueException in case null value is given
     */
    public Bid toModelType() throws IllegalValueException {

        if (propertyId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "P12"));
        }
        if (bidderId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "B04"));
        }
        if (bidAmount == 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "450443"));
        }
        return new Bid(propertyId, bidderId, bidAmount);
    }
}
