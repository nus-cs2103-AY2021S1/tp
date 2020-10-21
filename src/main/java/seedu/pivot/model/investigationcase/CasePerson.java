package seedu.pivot.model.investigationcase;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Encapsulates a Person related to an Investigation Case.
 */
public abstract class CasePerson {
    // Identity fields
    private final Name name;

    /**
     * Every field must be present and not null.
     */
    public CasePerson(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        return builder.toString();
    }
}
