package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.id.Id;
import seedu.address.model.tag.Tag;

/**
 * Represents the bidders who are bidding for properties.
 */
public class Bidder extends ClientPerson {

    /**
     * Constructs the bidder with the name, phone, tags, and id.
     *
     * @param name name of the bidder.
     * @param phone phone number.
     * @param tags tags.
     * @param id identifier.
     */
    public Bidder(Name name, Phone phone, Set<Tag> tags, Id id) {
        super(name, phone, tags, id);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    @Override
    public boolean isSamePerson(Person otherPerson) {
        return otherPerson instanceof Bidder && super.isSamePerson(otherPerson);
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
                && otherBidder.getTags().equals(getTags());
    }

}
