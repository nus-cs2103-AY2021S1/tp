package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a contact in the contact list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Contact {

    // Identity fields
    private final ContactName name;
    private final Email email;
    private final Telegram telegramUsername;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Contact(ContactName name, Email email, Telegram telegramUsername, Set<Tag> tags) {
        requireAllNonNull(name, email, telegramUsername, tags);
        this.name = name;
        this.email = email;
        this.telegramUsername = telegramUsername;
        this.tags.addAll(tags);
    }

    public ContactName getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Telegram getTelegramUsername() {
        return this.telegramUsername;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both contacts of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameContact(Contact otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTelegramUsername().equals(getTelegramUsername());
    }

    /**
     * Returns true if both contacts have the same identity and data fields.
     * This defines a stronger notion of equality between two contacts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Contact)) {
            return false;
        }

        Contact otherPerson = (Contact) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTelegramUsername().equals(getTelegramUsername())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, telegramUsername, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Email: ")
                .append(getEmail())
                .append(" Telegram: ")
                .append(getTelegramUsername())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
