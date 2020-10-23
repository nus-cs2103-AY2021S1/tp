package seedu.address.model.task.deadline;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Represents a Deadline in the PlaNus task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Deadline extends Task {

    // Identity fields
    private final DateTime dateTime;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Status status;

    /**
     * Every field must be present and not null.
     */
    public Deadline(Title title, DateTime dateTime, Description description, Set<Tag> tags) {
        super(title, description, tags);
        requireAllNonNull(dateTime);
        this.dateTime = dateTime;
        this.status = Status.createIncompleteStatus();
    }

    /**
     * Every field must be present and not null.
     */
    public Deadline(Title title, DateTime dateTime, Description description, Set<Tag> tags, Status status) {
        super(title, description, tags);
        requireAllNonNull(dateTime);
        this.dateTime = dateTime;
        this.status = status;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns true if both deadlines of the same title, date and time.
     */
    public boolean isSameDeadline(Deadline otherDeadline) {
        if (otherDeadline == this) {
            return true;
        }

        return otherDeadline != null
                && otherDeadline.getTitle().equals(getTitle())
                && otherDeadline.getDateTime().equals(getDateTime());
    }

    public Deadline markAsDone() {
        return new Deadline(super.getTitle(), dateTime, super.getDescription(), tags, Status.createCompleteStatus());
    }

    /**
     * Returns true if both deadlines have the same identity and data fields.
     * This defines a stronger notion of equality between two deadlines.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return otherDeadline.getTitle().equals(getTitle())
                && otherDeadline.getDateTime().equals(getDateTime())
                && otherDeadline.getDescription().equals(getDescription())
                && otherDeadline.getTags().equals(getTags())
                && otherDeadline.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, dateTime, description, tags, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Done by: ")
                .append(getDateTime())
                .append(" Description: ")
                .append(getDescription())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Status: ").append(getStatus());
        return builder.toString();
    }

}
