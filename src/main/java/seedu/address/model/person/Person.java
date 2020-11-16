package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import seedu.address.model.attendance.Attendance;
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
    private final Telegram telegram;
    private final MatricNumber matricNumber;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final SortedSet<Attendance> attendances = new TreeSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name,
                  Phone phone,
                  Email email,
                  Telegram telegram,
                  MatricNumber matricNumber,
                  Set<Tag> tags,
                  SortedSet<Attendance> attendances) {
        requireAllNonNull(name, phone, email, tags, attendances);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.matricNumber = matricNumber;
        this.tags.addAll(tags);
        this.attendances.addAll(attendances);
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

    public Telegram getTelegram() {
        return telegram;
    }

    public MatricNumber getMatricNumber() {
        return matricNumber;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable attendance sorted set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public SortedSet<Attendance> getAttendances() {
        return Collections.unmodifiableSortedSet(attendances);
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
                && (otherPerson.getPhone().equals(getPhone())
                || otherPerson.getEmail().equals(getEmail())
                || otherPerson.getMatricNumber().equals(getMatricNumber())
                || otherPerson.getTelegram().equals(getTelegram()));
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
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTelegram().equals(getTelegram())
                && otherPerson.getMatricNumber().equals(getMatricNumber())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getAttendances().equals(getAttendances());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, telegram, matricNumber, tags, attendances);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Telegram: ")
                .append(getTelegram())
                .append(" Matric Number: ")
                .append(getMatricNumber())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
