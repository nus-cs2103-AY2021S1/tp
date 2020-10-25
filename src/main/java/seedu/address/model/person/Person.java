package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Phone emergency;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final ArchiveStatus archiveStatus;
    /**
     * Every field must be present and not null.
     */

    public Person(Name name, Phone phone, Phone emergency, Address address, ArchiveStatus archiveStatus,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, emergency, address, tags, archiveStatus);

        this.name = name;
        this.phone = phone;
        this.emergency = emergency;
        this.address = address;
        this.archiveStatus = archiveStatus;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Phone getEmergency() {
        return emergency;
    }

    public Address getAddress() {
        return address;
    }

    public ArchiveStatus getArchiveStatus() {
        return archiveStatus;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && (otherPerson.getPhone().equals(getPhone()));
    }

    /**
     * Sets the person's archive status to true. It's equivalent to having archived the person.
     *
     * @return A Person whose archive status is true.
     */
    public Person archive() {
        return new Person(this.name, this.phone, this.emergency, this.address, new ArchiveStatus(true),
                this.tags);
    }

    /**
     * Sets the person's archive status to false. It's equivalent to unarchive the person.
     *
     * @return A Person whose archive status is false.
     */
    public Person unarchive() {
        return new Person(this.name, this.phone, this.emergency, this.address, new ArchiveStatus(false),
                this.tags);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;

        if (other == null || Boolean.valueOf(archiveStatus.toString())
                || Boolean.valueOf(otherPerson.archiveStatus.toString())) {
            return false;
        }

        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmergency().equals((getEmergency()))
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getArchiveStatus().equals(getArchiveStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, emergency, address, archiveStatus, tags);

    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Emergency Contact: ")
                .append(getEmergency())
                .append(" Address: ")
                .append(getAddress())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
