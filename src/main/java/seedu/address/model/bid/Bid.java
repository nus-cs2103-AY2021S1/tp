package seedu.address.model.bid;

import java.util.Objects;

public class Bid {


    private String propertyId;
    private String bidderId;
    private double bidAmount;

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
        return false;// will check against propertyList to see if propertyId exists
    }

    public boolean isValidBidderId() {
        return false;// will check against bidderList to see if propertyId exists
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(propertyId, bidderId, bidAmount);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Added bid to property: ")
                .append(getPropertyId())
                .append(" of ")
                .append(getBidAmount())
                .append(" by ")
                .append(getBidderId());

        return builder.toString();
    }
}
