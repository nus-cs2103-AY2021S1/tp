package seedu.address.model.person;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import seedu.address.model.tag.Tag;

/**
 * Represents a Bug in the description book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Bug {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final State state;

    // Data fields
    private final Description description;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Bug(Name name, Phone phone, State state, Description description, Set<Tag> tags) {
        requireAllNonNull(name, phone, state, description, tags);
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.description = description;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public State getState() {
        return state;
    }

    public Description getDescription() {
        return description;
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
    public boolean isSamePerson(Bug otherBug) {
        if (otherBug == this) {
            return true;
        }

        return otherBug != null
                && otherBug.getName().equals(getName())
                && (otherBug.getPhone().equals(getPhone()) || otherBug.getState().equals(getState()));
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

        if (!(other instanceof Bug)) {
            return false;
        }

        Bug otherBug = (Bug) other;
        return otherBug.getName().equals(getName())
                && otherBug.getPhone().equals(getPhone())
                && otherBug.getState().equals(getState())
                && otherBug.getDescription().equals(getDescription())
                && otherBug.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, state, description, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" State: ")
                .append(getState())
                .append(" Description: ")
                .append(getDescription())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
