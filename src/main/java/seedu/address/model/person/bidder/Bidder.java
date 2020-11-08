package seedu.address.model.person.bidder;

import seedu.address.model.id.BidderId;
import seedu.address.model.person.ClientPerson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents the bidders who are bidding for properties.
 */
public class Bidder extends ClientPerson {

    public static final Tag BIDDER_TAG = new Tag("bidder");
    private boolean isWinningBidder; // meant for extension

    /**
     * Constructs the bidder with the name, phone, tags, and id.
     *
     * @param name     name of the bidder.
     * @param phone    phone number.
     * @param bidderId identifier.
     */
    public Bidder(Name name, Phone phone, BidderId bidderId) {
        super(name, phone, BIDDER_TAG, bidderId);
    }

    public void setId(BidderId updatedId) {
        super.setId(updatedId);
        this.clientId = updatedId;
    }

    public boolean isSameBidder(Bidder otherBidder) {
        return this.isSameClient(otherBidder);
    }

    /**
     * Returns true if both sellers have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Bidder)) {
            return false;
        }

        Bidder otherBidder = (Bidder) other;
        return otherBidder.getName().equals(getName())
                && otherBidder.getPhone().equals(getPhone())
                && otherBidder.getId().equals(getId())
                && otherBidder.getTag().equals(getTag());
    }

}
