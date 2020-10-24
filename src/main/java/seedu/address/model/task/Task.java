package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.deadline.Deadline;

/**
 * Represents a Task in the PlaNus task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Task implements Comparable<Task> {

    // Identity fields
    protected final Title title;
    protected final Description description;

    // Data fields
    protected final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    protected Task(Title title, Description description, Set<Tag> tags) {
        requireAllNonNull(title, description, tags);
        this.title = title;
        this.description = description;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return title;
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
     * Returns true if both tasks of the same title, date and time.
     * This defines a strong notion of equality between two tasks to allow recurring tasks yet preventing duplicates.
     */
    public abstract boolean isSameTask(Task otherTask);

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public abstract boolean equals(Object other);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    @Override
    public abstract int compareTo(Task otherTask);

}
