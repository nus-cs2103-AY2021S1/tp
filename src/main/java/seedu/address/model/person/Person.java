package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
    private final Email email;
    private final Description description;

    // Data fields
    private final Address address;
    private final List<Suspect> suspects = new ArrayList<>();
    private final List<Victim> victims = new ArrayList<>();
    private final Set<Tag> tags = new HashSet<>();
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Description description, Phone phone, Email email, Address address, List<Suspect> suspects,
                  List<Victim> victims, Set<Tag> tags) {
        requireAllNonNull(name, description, phone, email, address, suspects, victims, tags);
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.suspects.addAll(suspects);
        this.victims.addAll(victims);
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Description getDescription() {
        return description;
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

    public List<Suspect> getSuspects() {
        return suspects;
    }

    public List<Victim> getVictims() {
        return victims;
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
                && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail()));
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
                && otherPerson.getDescription().equals(getDescription())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getSuspects().equals(getSuspects())
                && otherPerson.getVictims().equals(getVictims())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, description, phone, email, address, suspects, victims, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Description: ")
                .append(getDescription())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Suspects: ")
                .append(getSuspects())
                .append(" Victims: ")
                .append(getVictims())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
