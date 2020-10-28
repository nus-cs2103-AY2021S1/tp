package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the PlaNus task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Task implements Comparable<Task> {

    // Identity fields
    protected final Title title;
    protected final Description description;

    // Data fields
    protected final Tag tag;

    /**
     * Every field must be present and not null.
     */
    protected Task(Title title, Description description, Tag tag) {
        requireAllNonNull(title, description, tag);
        this.title = title;
        this.description = description;
        this.tag = (tag);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }
    /**
     * Returns an immutable tag, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return tag;
    }
    /**
     * Returns the time taken for the task to be completed in minutes.
     */
    public abstract int getTimeTaken();
    /**
     * Returns the date in which the task occurs.
     */
    public abstract LocalDate getDate();
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
