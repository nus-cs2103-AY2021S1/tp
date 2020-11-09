package seedu.address.model.person.seller;

import static seedu.address.model.id.SellerId.DEFAULT_SELLER_ID;

import seedu.address.model.id.SellerId;
import seedu.address.model.person.ClientPerson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents the sellers who are selling the properties.
 */
public class Seller extends ClientPerson {

    public static final Tag SELLER_TAG = new Tag("seller");

    /**
     * Constructs the seller with the name, phone, tags, and id.
     *  @param name name of the seller.
     * @param phone phone number.
     * @param sellerId identifier.
     */
    public Seller(Name name, Phone phone, SellerId sellerId) {
        super(name, phone, SELLER_TAG, sellerId);
    }

    public Seller setDefaultSellerId() {
        return new Seller(name, phone, DEFAULT_SELLER_ID);
    }

    public void setId(SellerId updatedId) {
        super.setId(updatedId);
        this.clientId = updatedId;
    }


    public boolean isSameSeller(Seller otherSeller) {
        return this.isSameClient(otherSeller);
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

        if (!(other instanceof Seller)) {
            return false;
        }

        Seller otherSeller = (Seller) other;
        return otherSeller.getName().equals(getName())
                && otherSeller.getPhone().equals(getPhone())
                && otherSeller.getTag().equals(getTag());
    }

}
