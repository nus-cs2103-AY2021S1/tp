package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.id.Id;
import seedu.address.model.tag.Tag;

/**
 * Represents the clients who interacts with the property agent.
 */
public abstract class ClientPerson extends Person {

    // Identity fields
    protected Id clientId;

    /**
     * Constructor for the client.
     *
     * @param name name of client.
     * @param phone phone number of client.
     * @param tag tag.
     * @param id identifier.
     */
    protected ClientPerson(Name name, Phone phone, Tag tag, Id id) {
        super(name, phone, tag);
        requireAllNonNull(id);
        this.clientId = id;
    }

    public Id getId() {
        return this.clientId;
    }

    public void setId(Id updatedId) {
        this.clientId = updatedId;
    }

    /**
     * checks if the other client is the same in any regard.
     *
     * @param otherClient the other client checked against.
     * @return if the client contains the same information .
     */
    public boolean isSameClient(ClientPerson otherClient) {
        return  otherClient != null
                && (this == otherClient
                || this.getName().equals(otherClient.getName())
                || this.getPhone().equals(otherClient.getPhone()));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\nName: ")
                .append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nId: ")
                .append(getId())
                .append("\nTag: ")
                .append(getTag());
        return builder.toString();
    }
}
