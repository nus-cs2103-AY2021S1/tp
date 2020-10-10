package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.bid.Bid;
import seedu.address.model.person.Name;

public class JsonAdaptedBid {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "bid's %s field is missing!";

    private final String propertyId;
    private final String bidderId;
    private final double bidAmount;




    @JsonCreator
    public JsonAdaptedBid(@JsonProperty("propertyId") String propertyId, @JsonProperty("bidderId") String bidderId, @JsonProperty("bidAmount") double bidAmount) {
        this.propertyId = propertyId;
        this.bidderId = bidderId;
        this.bidAmount = bidAmount;
    }



    public JsonAdaptedBid(Bid source) {
        propertyId = source.getPropertyId();
        bidderId = source.getBidderId();
        bidAmount = source.getBidAmount();
    }

    public Bid toModelType() throws IllegalValueException {

        if (propertyId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "P12"));
        }
        if (bidderId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "B04"));
        }
        if (bidAmount  == 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "450443"));
        }
        return new Bid(propertyId, bidderId, bidAmount);
    }
}
