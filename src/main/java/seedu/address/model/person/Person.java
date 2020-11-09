package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.EqualsUtil.equalsNullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.clientsource.ClientSource;
import seedu.address.model.note.Note;
import seedu.address.model.policy.Policy;

/**
 * Represents a Person in the client list.
 * Guarantees: name, client sources, priority, archive status are present and not null,
 * field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<ClientSource> clientSources = new HashSet<>();
    private final Note note;
    private final boolean isArchive;
    private final Priority priority;
    private final Policy policy;

    /**
     * Only name, client sources, priority need to be non-null.
     * By default, Person is not in archive.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<ClientSource> clientSources, Note note,
                  Priority priority, Policy policy) {
        requireAllNonNull(name, clientSources, priority);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.clientSources.addAll(clientSources);
        this.note = note;
        this.isArchive = false;
        this.priority = priority;
        this.policy = policy;
    }

    /**
     * Only name, client sources, priority, archive status need to be non-null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<ClientSource> clientSources,
                  Note note, boolean isArchive, Priority priority, Policy policy) {
        requireAllNonNull(name, clientSources, isArchive, priority);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.clientSources.addAll(clientSources);
        this.note = note;
        this.isArchive = isArchive;
        this.priority = priority;
        this.policy = policy;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable clientsource set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ClientSource> getClientSources() {
        return Collections.unmodifiableSet(clientSources);
    }

    public Note getNote() {
        return note;
    }

    public boolean getIsArchive() {
        return isArchive;
    }

    public Priority getPriority() {
        return priority;
    }

    public Policy getPolicy() {
        return policy;
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * If both the phones of self and other person are null, check the email instead. Same for the other way round.
     * If both the phones and emails of self and other person are null, return true.
     * This defines a weaker notion of equality between two persons.
     *
     * Note that archive status is not checked here, i.e. a person cannot belong in archive and also in active list.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        // If other person is null, or if their names are not equal, then not the same person.
        if (otherPerson == null || !otherPerson.getName().equals(getName())) {
            return false;
        }

        // If both phones are null, test the email instead.
        if (getPhone() == null && otherPerson.getPhone() == null) {
            return equalsNullable(otherPerson.getEmail(), getEmail());
        }

        // If both emails are null, test the phone instead.
        if (getEmail() == null && otherPerson.getEmail() == null) {
            return equalsNullable(otherPerson.getPhone(), getPhone());
        }

        // After handling for cases where both emails/phones are null in earlier checks;
        // test if at least one of phone or email is the same.
        return (equalsNullable(otherPerson.getPhone(), getPhone())
                || equalsNullable(otherPerson.getEmail(), getEmail()));
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

        return otherPerson.getName().equals(getName())
                && equalsNullable(otherPerson.getPhone(), getPhone())
                && equalsNullable(otherPerson.getEmail(), getEmail())
                && equalsNullable(otherPerson.getAddress(), getAddress())
                && otherPerson.getClientSources().equals(getClientSources())
                && equalsNullable(otherPerson.getNote(), getNote())
                && (otherPerson.getIsArchive() == getIsArchive())
                && equalsNullable(otherPerson.getPolicy(), getPolicy());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, clientSources, note, isArchive, priority, policy);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getName())
                .append(personFieldToStringBuilder(getPhone()))
                .append(personFieldToStringBuilder(getEmail()))
                .append(personFieldToStringBuilder(getAddress()));

        if (getClientSources().size() > 0) {
            builder.append(" ClientSources: ");
            getClientSources().forEach(builder::append);
        }

        builder.append(personFieldToStringBuilder(getNote()))
                .append(personFieldToStringBuilder(getPolicy()))
                .append(personFieldToStringBuilder(getPriority()));

        return builder.toString();
    }

    /**
     * Returns a formatted string builder containing the field's class name and the field.
     * If the field is not present, return an empty string builder.
     *
     * @param field The field in the Person class.
     * @return String builder with field's class name and the field.
     */
    private StringBuilder personFieldToStringBuilder(Object field) {
        final StringBuilder builder = new StringBuilder();
        if (field == null) {
            // return the empty builder
            return builder;
        }
        builder.append(" ")
                .append(field.getClass().getSimpleName())
                .append(": ")
                .append(field);
        return builder;
    }

}

