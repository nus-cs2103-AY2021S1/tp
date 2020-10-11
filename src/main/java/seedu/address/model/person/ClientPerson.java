package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.id.Id;
import seedu.address.model.tag.Tag;

/**
 * Represents the clients who interacts with the property agent.
 */
public class ClientPerson extends Person {

    // Identity fields
    protected Id sellerId;

    /**
     * Constructor for the client.
     *
     * @param name name of client.
     * @param phone phone number of client.
     * @param tags tags.
     * @param id identifier.
     */
    protected ClientPerson(Name name, Phone phone, Set<Tag> tags, Id id) {
        super(name, phone, tags);
        requireAllNonNull(id);
        this.sellerId = id;
    }

    public Id getId() {
        return this.sellerId;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    @Override
    public boolean isSamePerson(Person otherPerson) {
        return otherPerson instanceof ClientPerson && super.isSamePerson(otherPerson);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Tags: ")
                .append(" Id: ")
                .append(getId());
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
