package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.TagName;

/**
 * Represents a Person in the Projact.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final PersonName name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final TelegramAddress telegramAddress;
    private final Set<TagName> tagNames = new HashSet<>();

    /**
     * Every field must be present and not null.
     */

    public Person(PersonName name, Phone phone, Email email, TelegramAddress telegramAddress, Set<TagName> tagNames) {
        requireAllNonNull(name, phone, email, telegramAddress, tagNames);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegramAddress = telegramAddress;
        this.tagNames.addAll(tagNames);
    }

    public PersonName getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public TelegramAddress getTelegramAddress() {
        return telegramAddress;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<TagName> getTagNames() {
        return Collections.unmodifiableSet(tagNames);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
                && otherPerson.getTelegramAddress().equals(getTelegramAddress())
                && otherPerson.getTagNames().equals(getTagNames());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, telegramAddress, tagNames);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Telegram Address: ")
                .append(getTelegramAddress());
        if (!getTagNames().isEmpty()) {
            builder.append(" Tag Names: ");
            getTagNames().forEach(builder::append);
        }
        return builder.toString();
    }

}
