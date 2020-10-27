package seedu.address.model.task;

import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;

/**
 * Stores the details to edit the deadline task with. Each non-empty field value will replace the
 * corresponding field value of the deadline task.
 */
public class EditDeadlineDescriptor extends EditTaskDescriptor {
    private Title title;
    private Description description;
    private Priority priority;
    private TaskDate taskDeadline;
    private Set<Tag> tagList;

    public EditDeadlineDescriptor() {}

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(title, description, priority, taskDeadline, tagList);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskDate getTaskDeadline() {
        return taskDeadline;
    }

    public Set<Tag> getTagList() {
        return tagList;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setTaskDeadline(TaskDate taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public void setTagList(Set<Tag> tagList) {
        this.tagList = tagList;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDeadlineDescriptor)) {
            return false;
        }

        // state check
        EditDeadlineDescriptor e = (EditDeadlineDescriptor) other;

        return getTitle().equals(e.getTitle())
                && getDescription().equals(e.getDescription())
                && getPriority().equals(e.getPriority())
                && getTaskDeadline().equals(e.getTaskDeadline())
                && getTagList().equals(e.getTagList());
    }
}
