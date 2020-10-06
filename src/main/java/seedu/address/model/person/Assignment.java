package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Assignment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assignment {

    // Identity fields
    private final Name name;
    private final Deadline deadline;

    // Data fields
    private final ModuleCode moduleCode;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Assignment(Name name, Deadline deadline, ModuleCode moduleCode, Set<Tag> tags) {
        requireAllNonNull(name, deadline, moduleCode, tags);
        this.name = name;
        this.deadline = deadline;
        this.moduleCode = moduleCode;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both assignments of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two assignments.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }

        return otherAssignment != null
                && otherAssignment.getName().equals(getName())
                && (otherAssignment.getDeadline().equals(getDeadline()));
    }

    /**
     * Returns true if both assignments have the same identity and data fields.
     * This defines a stronger notion of equality between two assignments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return otherAssignment.getName().equals(getName())
                && otherAssignment.getDeadline().equals(getDeadline())
                && otherAssignment.getModuleCode().equals(getModuleCode())
                && otherAssignment.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, moduleCode, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Deadline: ")
                .append(getDeadline())
                .append(" Email: ")
                .append(" Module: ")
                .append(getModuleCode())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
