package seedu.address.model.bid;

import java.util.Objects;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.price.Price;

public class Bid {

    public static final String MESSAGE_CONSTRAINTS_BID_AMOUNT =
            "Bid Amount should only contain numerical values, \nand it should not be blank";

    private PropertyId propertyId;
    private BidderId bidderId;
    private Price bidAmount;

    /**
     * Constructor for Bid Object
     * @param propertyId string id of the property to bid for
     * @param bidderId string id of the bidder wanting the property
     * @param bidAmount Price value of the amount the bidder wants the property for
     */
    public Bid(PropertyId propertyId, BidderId bidderId, Price bidAmount) {
        this.propertyId = propertyId;
        this.bidderId = bidderId;
        this.bidAmount = bidAmount;
    }

    /**
     * retrieves the propertyId
     * @return the property id of a given bid
     */
    public PropertyId getPropertyId() {
        return propertyId;
    }

    /**
     * retrieves the bidderId
     * @return the bidder id of a given bid
     */
    public BidderId getBidderId() {
        return bidderId;
    }

    /**
     * retrieves the bid amount
     * @return the bid amount of a given bid
     */
    public Price getBidAmount() {
        return bidAmount;
    }

    /**
     * compares one bid with another bid to see if they are the same in content
     * @param otherBid bid to compare to
     * @return boolean value if they are the same or not
     */
    public boolean isSameBid(Bid otherBid) {
        if (otherBid == this) {
            return true;
        }

        return otherBid != null
                && otherBid.getPropertyId().equals(getPropertyId())
                && otherBid.getBidderId().equals(getBidderId());
    }

    /**
     * compares one bid with another bid to see if any fields have been edited
     * @param otherBid bid to compare to
     * @return true if there is have been no fields edited
     */
    public boolean isBidBeenEdited(Bid otherBid) {
        if (otherBid == this) {
            return true;
        }

        return otherBid != null
                && otherBid.getPropertyId().equals(getPropertyId())
                && otherBid.getBidderId().equals(getBidderId())
                && otherBid.getBidAmount().equals(getBidAmount());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(propertyId, bidderId, bidAmount);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Bid)) {
            return false;
        }

        Bid otherBid = (Bid) other;
        return otherBid.getPropertyId().equals(this.getPropertyId())
                && otherBid.getBidderId().equals(this.getBidderId())
                && otherBid.getBidAmount().equals(this.getBidAmount());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nBid of ")
                .append(getBidAmount())
                .append("\nby ")
                .append(getBidderId())
                .append("\nto property: ")
                .append(getPropertyId());

        return builder.toString();
    }
}
