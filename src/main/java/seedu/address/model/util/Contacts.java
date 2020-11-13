package seedu.address.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Optional;

/**
 * Represents a Meeting's contacts in the meeting book.
 */
public class Contacts {
    public static final String EMPTY_CONTACTS_FIELD = "-";
    public static final String MESSAGE_CONSTRAINTS =
            "Contacts should only take a name, a comma-separated string of names, or blank.";

    /*
     * Contacts can only take alphabetic characters and spaces separated with commas.
     * First character should not be a blank string.
     *
     * @@author claraadora-reused
     * Reused from https://stackoverflow.com/a/1396228 with minor modifications
     */
    public static final String VALIDATION_REGEX = "([a-zA-Z\\s]+)(,\\s*[a-zA-Z\\s]+)*";

    /*
     * Represents the value of Contacts.
     */
    public final Optional<String> value;

    /**
     * Constructs a {@code Contacts}.
     *
     * @param contacts A valid Optional of contact name strings.
     */
    public Contacts(Optional<String> contacts) {
        if (contacts.isPresent()) {
            checkArgument(isValidContacts(contacts.get()), MESSAGE_CONSTRAINTS);
            value = contacts.get().isEmpty()
                    ? Optional.empty()
                    : contacts;
        } else {
            value = contacts;
        }
    }

    /**
     * Constructs a {@code Contacts}.
     *
     * @param contacts A valid Optional of contact name strings.
     */
    public Contacts(String contacts) {
        requireNonNull(contacts);
        checkArgument(isValidContacts(contacts), MESSAGE_CONSTRAINTS);
        if (contacts.isEmpty()) {
            value = Optional.empty();
        } else {
            value = Optional.of(contacts);
        }
    }

    /**
     * Returns true if a given string is a valid contacts.
     */
    public static boolean isValidContacts(String test) {
        if (test.isEmpty()) {
            return true;
        } else {
            return test.matches(VALIDATION_REGEX);
        }
    }

    @Override
    public String toString() {
        return value.orElse(EMPTY_CONTACTS_FIELD);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Contacts
                && value.equals(((Contacts) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
