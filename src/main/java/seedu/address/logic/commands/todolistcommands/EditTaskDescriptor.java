package seedu.address.logic.commands.todolistcommands;

import java.util.Optional;

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
    private Tag tag;
    private Priority priority;
    private Date date;

    public EditTaskDescriptor() {}

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditTaskDescriptor(EditTaskDescriptor toCopy) {
        setName(toCopy.name);
        setTag(toCopy.tag);
        setPriority(toCopy.priority);
        setDate(toCopy.date);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(name, tag, priority, date);
    }

    public void setName(TaskName name) {
        this.name = name;
    }

    public Optional<TaskName> getName() {
        return Optional.ofNullable(name);
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Optional<Tag> getTag() {
        return Optional.ofNullable(tag);
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
            && getTag().equals(e.getTag())
            && getPriority().equals(e.getPriority())
            && getDate().equals(e.getDate());
    }
}
