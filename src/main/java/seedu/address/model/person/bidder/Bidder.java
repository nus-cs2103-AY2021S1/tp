package seedu.address.model.person.bidder;

import java.util.Set;

import seedu.address.model.id.BidderId;
import seedu.address.model.id.Id;
import seedu.address.model.person.ClientPerson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents the bidders who are bidding for properties.
 */
public class Bidder extends ClientPerson {

    public static final BidderId DEFAULT_BIDDER_ID = new BidderId(0);
    public final BidderId bidderId;

    public Bidder(Name name, Phone phone, Set<Tag> tags) {
        super(name, phone, tags, DEFAULT_BIDDER_ID);
        this.bidderId = DEFAULT_BIDDER_ID;
    }
    /**
     * Constructs the bidder with the name, phone, tags, and id.
     * @param name name of the bidder.
     * @param phone phone number.
     * @param tags tags.
     * @param bidderId identifier.
     */
    public Bidder(Name name, Phone phone, Set<Tag> tags, BidderId bidderId) {
        super(name, phone, tags, bidderId);
        this.bidderId = bidderId;
    }

    public Bidder setDefaultBidderId() {
        return new Bidder(name, phone, tags , DEFAULT_BIDDER_ID);
    }

    public Bidder setBidderTag() {
        tags.add(new Tag("bidder"));
        return new Bidder(name, phone, tags, bidderId);
    }

    @Override
    public BidderId getId() {
        return bidderId;
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
