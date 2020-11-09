package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Represents a TagTask in the address book.
 * Guarantees: immutable; task description is valid as declared in {@link #isValidTaskDescription(String)}
 */
public class TagTask {

    public static final String MESSAGE_CONSTRAINTS =
            "Task description should start with an alphanumeric character, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9].*$";

    public final String taskDescription;
    private boolean isDone;

    /**
     * Constructs a {@code TagTask}.
     *
     * @param taskDescription A valid description of the task.
     * @param isDone A boolean indicating whether the task is completed.
     */
    public TagTask(String taskDescription, boolean isDone) {
        requireNonNull(taskDescription);
        taskDescription = taskDescription.trim();
        checkArgument(isValidTaskDescription(taskDescription), MESSAGE_CONSTRAINTS);
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns true if a given string is a valid task description.
     */
    public static boolean isValidTaskDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both TagTask has the same task description.
     * This defines a weaker notion of equality between two TagTasks.
     */
    public boolean isSameTagTask(TagTask otherTagTask) {
        if (otherTagTask == this) {
            return true;
        }

        return otherTagTask != null
                && otherTagTask.taskDescription.equals(this.taskDescription);
    }

    /**
     * Modify the isDone status to true to signify the completion of task.
     * @throws CommandException if the Task has already been completed
     */
    public void markDone() throws CommandException {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new CommandException(
                    String.format("Task: %1$s is already completed!", this.toString()));
        }
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        String tick = "\u2713";
        String cross = "\u2718";
        return '[' + (this.isDone ? tick : cross)
                + " | " + this.taskDescription + ']';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TagTask)) {
            return false;
        }
        TagTask other = (TagTask) obj;
        return this.isDone == other.isDone
                && this.taskDescription.equals(other.taskDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.isDone, this.taskDescription);
    }
}
