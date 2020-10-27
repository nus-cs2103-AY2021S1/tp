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
    protected Id clientId;

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
        this.clientId = id;
    }

    public Id getId() {
        return this.clientId;
    }

    public void setId(Id updatedId) {
        this.clientId = updatedId;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nName:")
                .append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nId: ")
                .append(getId())
                .append("\nTags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
