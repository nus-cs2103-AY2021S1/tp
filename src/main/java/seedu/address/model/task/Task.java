package seedu.address.model.task;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

public abstract class Task {
    /** Maximum length for description supported by Lifebook */
    private static final int MAX_DESCRIPTION_LENGTH = 30;

    /** Message for denoting max description length */
    public static final String MESSAGE_CONSTRAINTS = "Description must have less than "
            + MAX_DESCRIPTION_LENGTH + " characters.";


    /** A brief description of the task. */
    protected String description;

    /** Tracks the completion of the task */
    protected boolean isDone;

    /** Optional link for documents and online meetings */
    protected Link link;

    /** Optional tag of the task**/
    protected final Set<Tag> tags = new HashSet<>();

    protected Recurrence recurrence;

    /**
     * Constructs a task that has not been completed with a description.
     *
     * @param description a brief description of the task
     * @param tags        a set of tags attached to the task.
     */
    public Task(String description, Set<Tag> tags) {
        assert description != null;
        assert tags != null;
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.isDone = false;
        this.tags.addAll(tags);
    }

    /**
     * Constructs a task, which may or may not have been completed, with a description.
     *
     * @param isDone indicates if the task has been completed.
     * @param description a brief description of the task.
     * @param tags        a set of tags attached to the task.
     */
    public Task(boolean isDone, String description, Set<Tag> tags) {
        assert description != null;
        assert tags != null;
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.isDone = isDone;
        this.tags.addAll(tags);
    }

    /**
     * Constructs a task, which have not been completed, with a description.
     *
     * @param description a brief description of the task.
     */
    public Task(String description) {
        assert description != null;
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns the status icon of the task.
     * Returns tick symbol when task is indicated as done.
     * Returns X symbol when task is not indicated as done.
     *
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Returns the description of the task as a string.
     *
     * @return the description of the task.
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * Indicates that the task has been completed.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public String getTagsToString() {
        StringBuilder builder = new StringBuilder();
        getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Returns a boolean value indicating if the task is equal to
     * another object by determining if descriptions and isDone parameters
     * are equal.
     *
     * @param o an object that is compared to the task to determine if both are equal
     * @return true or false if the object is equal or not equal to the task respectively.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Task) {
            Task task = (Task) o;
            boolean isEqualTask = this.description.equals(task.description) && this.isDone == task.isDone;
            return isEqualTask;
        } else {
            return false;
        }
    }

    public boolean isSameTask(Task task) {
        return this.equals(task);
    }

    /** Tracks if there is a link present in this task */
    public boolean hasLink() {
        if (this.link != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of task, which includes the status icon
     * and description.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + getDescription() + " " + getTagsToString();
    }

    /**
     * Returns the string representation of the task in a format to be inputted into a text file for data storage.
     *
     * @return the string representation of the task to be saved in a text file.
     */
    public String saveFormat() {
        if (isDone) {
            return "T | 1 | " + this.getDescription() + " | " + getTagsToString();
        } else {
            return "T | 0 | " + this.getDescription() + " | " + getTagsToString();
        }
    }

    /**
     * Indicates if task is recurring.
     * @return true if it is recurring, and false otherwise.
     */
    public boolean isRecurring() {
        System.out.println(this.getRecurrence() != null);
        return this.getRecurrence() != null;
    }

    /**
     * Indicates if description is valid.
     * @return true if it is valid, and false otherwise.
     */
    public static boolean isValidDescription(String description) {
        return description.length() <= MAX_DESCRIPTION_LENGTH;
    }

    public abstract LocalDateTime getDeadline();
    public abstract LocalDateTime getStart();
    public abstract LocalDateTime getEnd();
    public abstract String getDateTime();
    public abstract Optional<Link> getLink();
    public abstract LocalDateTime getLocalDateTime();
    public abstract boolean isTodo();
    public abstract boolean isEvent();
    public abstract String getType();
    public abstract Recurrence getRecurrence();
}
