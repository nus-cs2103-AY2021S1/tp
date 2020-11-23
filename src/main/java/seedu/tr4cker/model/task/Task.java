package seedu.tr4cker.model.task;

import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.exceptions.TaskConversionException;

/**
 * Represents a Task in TR4CKER.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    // Identity fields
    private final Name name;
    private final Deadline deadline;
    private final CompletionStatus completionStatus;

    // Data fields
    private final TaskDescription taskDescription;
    private final Set<ModuleCode> moduleCode;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, Deadline deadline, CompletionStatus completionStatus,
                TaskDescription taskDescription, Set<ModuleCode> moduleCode, Set<Tag> tags) {
        requireAllNonNull(name, deadline, completionStatus, taskDescription, tags);
        this.name = name;
        this.deadline = deadline;
        this.completionStatus = completionStatus;
        this.taskDescription = taskDescription;
        this.moduleCode = moduleCode;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public TaskDescription getTaskDescription() {
        return taskDescription;
    }

    public Set<ModuleCode> getModuleCode() {
        return Collections.unmodifiableSet(moduleCode);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Adds tag(s) to existing tags.
     *
     * @param tags Tags to be added.
     * @return A set of duplicated tags.
     */
    public Set<Tag> addTags(Set<Tag> tags) {
        Set<Tag> duplicateTags = new HashSet<>();
        for (Tag tag : tags) {
            if (this.tags.contains(tag)) {
                duplicateTags.add(tag);
            }
        }
        this.tags.addAll(tags);
        return duplicateTags;
    }

    /**
     * Deletes tag(s) from existing tags (if there exists).
     *
     * @param tags Tags to be deleted.
     * @return A set of non existing tags.
     */
    public Set<Tag> deleteTags(Set<Tag> tags) {
        Set<Tag> nonExistingTags = new HashSet<>();
        for (Tag tag : tags) {
            if (!this.tags.contains(tag)) {
                nonExistingTags.add(tag);
            }
            this.tags.remove(tag);
        }
        return nonExistingTags;
    }

    /**
     * Returns true if both tasks of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName())
                && (otherTask.getDeadline().equals(getDeadline()));
    }

    /**
     * Returns true if both tasks have the same fields.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isEdited(Task editedTask) {
        if (editedTask == this || editedTask == null) {
            return true;
        }

        return !(editedTask.getName().equals(getName())
                && editedTask.getTaskDescription().equals(getTaskDescription())
                && editedTask.getDeadline().equals(getDeadline())
                && editedTask.getModuleCode().equals(getModuleCode()));
    }

    public boolean isCompleted() {
        return completionStatus.value == 100;
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
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
        return otherTask.getName().equals(getName())
                && otherTask.getDeadline().equals(getDeadline())
                && otherTask.getCompletionStatus().equals(getCompletionStatus())
                && otherTask.getTaskDescription().equals(getTaskDescription())
                && otherTask.getModuleCode().equals(getModuleCode())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline, completionStatus, taskDescription, moduleCode, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Deadline: ")
                .append(getDeadline())
                .append(" CompletionStatus: ")
                .append(getCompletionStatus())
                .append(" Description: ")
                .append(getTaskDescription())
                .append(" ModuleCode: ");
        getModuleCode().forEach(builder::append);
        builder.append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Returns an Event with data from this Task.
     */
    public Event toEvent() {
        EventName eventName = new EventName(name.taskName);
        EventDate eventDate;
        try {
            eventDate = new EventDate(deadline.toDate(), true);
        } catch (IllegalArgumentException iae) {
            throw new TaskConversionException();
        }
        return new Event(eventName, eventDate);
    }

}
