package seedu.address.logic.commands.todolistcommands;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskName;

/**
 * Stores the details to edit the task with. Each non-empty field value will replace the
 * corresponding field value of the task.
 */
public class EditTaskDescriptor {
    private TaskName name;
    private Set<Tag> tags;
    private Priority priority;
    private Date date;

    // boolean to indicate if a field should be deleted
    // cannot be null to avoid NullPointerException
    private boolean isPriorityDeleted;
    private boolean isDateDeleted;

    /**
     * Constructs an EditTaskDescriptor.
     */
    public EditTaskDescriptor() {
        isPriorityDeleted = false;
        isDateDeleted = false;
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditTaskDescriptor(EditTaskDescriptor toCopy) {
        setName(toCopy.name);
        setTags(toCopy.tags);
        setPriority(toCopy.priority);
        setDate(toCopy.date);
        setIsPriorityDeleted(toCopy.isPriorityDeleted);
        setIsDateDeleted(toCopy.isDateDeleted);
    }

    /**
     * Returns true if at least one field is edited or deleted.
     */
    public boolean isAnyFieldEdited() {
        boolean isAnyNonNull = CollectionUtil.isAnyNonNull(name, tags, priority, date);
        boolean isAnyDeleted = isPriorityDeleted || isDateDeleted;
        return isAnyNonNull || isAnyDeleted;
    }

    public void setName(TaskName name) {
        this.name = name;
    }

    public Optional<TaskName> getName() {
        return Optional.ofNullable(name);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = (tags != null) ? new HashSet<>(tags) : null;
    }

    /**
     * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     * Returns {@code Optional#empty()} if {@code tags} is null.
     */
    public Optional<Set<Tag>> getTags() {
        return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Optional<Priority> getPriority() {
        return Optional.ofNullable(priority);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Optional<Date> getDate() {
        return Optional.ofNullable(date);
    }

    public boolean getIsPriorityDeleted() {
        return this.isPriorityDeleted;
    }

    public void setIsPriorityDeleted(boolean bool) {
        this.isPriorityDeleted = bool;
    }

    public boolean getIsDateDeleted() {
        return this.isDateDeleted;
    }

    public void setIsDateDeleted(boolean bool) {
        this.isDateDeleted = bool;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskDescriptor)) {
            return false;
        }

        // state check
        EditTaskDescriptor e = (EditTaskDescriptor) other;

        return getName().equals(e.getName())
            && getTags().equals(e.getTags())
            && getPriority().equals(e.getPriority())
            && getDate().equals(e.getDate());
    }
}
