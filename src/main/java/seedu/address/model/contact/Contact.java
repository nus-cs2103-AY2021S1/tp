package seedu.address.model.contact;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a contact in the contact list.
 * Guarantees: details are present and not null, except for Telegram which might be absent,
 * field values are validated, immutable.
 */
public class Contact {

    // Identity fields
    private final ContactName name;
    private final Email email;
    private final Telegram telegram;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final boolean isImportant;

    /**
     * Creates and initialises a Contact object without a Telegram field.
     *
     * @param name ContactName field of the Contact object.
     * @param email Email field of the Contact object.
     * @param tags Set of tags of the Contact object.
     * @param isImportant boolean describing the isImportant field of the Contact object.
     */
    public Contact(ContactName name, Email email, Set<Tag> tags, boolean isImportant) {
        requireAllNonNull(name, email, tags, isImportant);
        this.name = name;
        this.email = email;
        this.telegram = null;
        this.tags.addAll(tags);
        this.isImportant = isImportant;
    }

    /**
     * Creates and initialises a new Contact object.
     * Every field must be present and not null.
     *
     * @param name ContactName field of the Contact object.
     * @param email Email field of the Contact object.
     * @param telegram Telegram field of the Contact object.
     * @param tags Set of tags of the Contact object.
     * @param isImportant boolean describing the isImportant field of the Contact object.
     */
    public Contact(ContactName name, Email email, Telegram telegram, Set<Tag> tags, boolean isImportant) {
        requireAllNonNull(name, email, telegram, tags, isImportant);
        this.name = name;
        this.email = email;
        this.telegram = telegram;
        this.tags.addAll(tags);
        this.isImportant = isImportant;
    }

    public ContactName getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Optional<Telegram> getTelegram() {
        return Optional.ofNullable(this.telegram);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Determines if both contacts have the same name and email.
     * This defines a weaker notion of equality between two contacts.
     *
     * @param otherContact Contact to be compared.
     * @return True if both contacts have the same name and email, false otherwise.
     */
    public boolean isSameContact(Contact otherContact) {
        if (otherContact == this) {
            return true;
        }

        return otherContact != null
                && otherContact.getName().equals(getName())
                && otherContact.getEmail().equals(getEmail());
    }

    /**
     * Marks the contact as important.
     *
     * @return Contact object with the updated field.
     */
    public Contact markAsImportant() {
        return new Contact(this.name, this.email, this.telegram, this.tags, true);
    }

    /**
     * Marks the contact as unimportant.
     *
     * @return Contact object with the updated field.
     */
    public Contact markAsNotImportant() {
        return new Contact(this.name, this.email, this.telegram, this.tags, false);
    }

    /**
     * Determines if this contact is important.
     *
     * @return True if this contact is important, false otherwise.
     */
    public boolean isImportant() {
        return this.isImportant;
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

        Contact otherContact = (Contact) other;
        return otherContact.getName().equals(getName())
                && otherContact.getEmail().equals(getEmail())
                && otherContact.getTelegram().equals(getTelegram())
                && otherContact.getTags().equals(getTags())
                && ((Boolean) otherContact.isImportant()).equals(isImportant());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName() + "\n")
                .append(" Email: ")
                .append(getEmail() + "\n")
                .append(" Telegram: ")
                .append(getTelegram().isPresent() ? getTelegram().get() + "\n" : "- \n")
                .append(" Important: ")
                .append(isImportant ? "Yes\n" : "No\n")
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
