package seedu.address.model.person.seller;

import java.util.Set;

import seedu.address.model.id.Id;
import seedu.address.model.person.ClientPerson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents the sellers who are selling the properties.
 */
public class Seller extends ClientPerson {

    /**
     * Constructs the seller with the name, phone, tags, and id.
     *
     * @param name name of the seller.
     * @param phone phone number.
     * @param tags tags.
     * @param id identifier.
     */
    public Seller(Name name, Phone phone, Set<Tag> tags, Id id) {
        super(name, phone, tags, id);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    @Override
    public boolean isSamePerson(Person otherPerson) {
        return otherPerson instanceof Seller && super.isSamePerson(otherPerson);
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
                && otherSeller.getTags().equals(getTags());
    }

}
