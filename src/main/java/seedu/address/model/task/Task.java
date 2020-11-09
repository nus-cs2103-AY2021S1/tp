package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

//@@author w-yeehong
/**
 * Represents a Task that can be assigned to a room.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Data fields (i.e. values entered by user).
    private final Description description;
    private final DateTimeDue dueAt;

    /**
     * Every field apart must be present and not null.
     */
    public Task(Description description, DateTimeDue dueAt) {
        requireAllNonNull(description, dueAt);
        this.description = description;
        this.dueAt = dueAt;
    }

    public Description getDescription() {
        return description;
    }

    public DateTimeDue getDueAt() {
        return dueAt;
    }

    /**
     * Returns true if both tasks have the same data and metadata fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getDescription().equals(getDescription())
                && otherTask.getDueAt().equals(getDueAt());
    }

    @Override
    public int hashCode() {
        // Use this method for custom fields hashing instead of implementing your own.
        return Objects.hash(description, dueAt);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("\n")
                .append("Due Date: ")
                .append(getDueAt());
        return builder.toString();
    }
}
