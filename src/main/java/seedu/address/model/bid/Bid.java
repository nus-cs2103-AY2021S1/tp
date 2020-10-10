package seedu.address.model.bid;

import seedu.address.model.person.Person;

import java.util.Objects;

public class Bid {


    private String propertyId;
    private String bidderId;
    private double bidAmount;

    /**
     * Constructor for Bid Object
     * @param propertyId string id of the property to bid for
     * @param bidderId string id of the bidder wanting the property
     * @param bidAmount double value of the amount the bidder wants the property for
     */
    public Bid(String propertyId, String bidderId, double bidAmount) {
        this.propertyId = propertyId;
        this.bidderId = bidderId;
        this.bidAmount = bidAmount;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getBidderId() {
        return bidderId;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public boolean isValidPropertyId() {
        return false; //will check against propertyList to see if propertyId exists
    }

    public boolean isValidBidderId() {
        return false; //will check against bidderList to see if propertyId exists
    }

    public boolean isSameBid(Bid otherBid) {
        if (otherBid == this) {
            return true;
        }

        return otherBid != null
                && otherBid.getPropertyId().equals(getPropertyId())
                && (otherBid.getBidderId().equals(getBidderId()))
                && ((otherBid.getBidAmount() == getBidAmount()));// added this in
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(propertyId, bidderId, bidAmount);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Added bid of $")
                .append(getBidAmount())
                .append(" by ")
                .append(getBidderId())
                .append(" to property: ")
                .append(getPropertyId());

        return builder.toString();
    }
}
