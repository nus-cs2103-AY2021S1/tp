package seedu.address.model.task;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;

import java.util.Set;

/**
 * Stores the details to edit the event task with. Each non-empty field value will replace the
 * corresponding field value of the event task.
 */
public class EditEventDescriptor extends EditTaskDescriptor {
    private Title title;
    private Description description;
    private Priority priority;
    private TaskDate eventDate;
    private TaskTime eventTime;
    private Set<Tag> tagList;

    public EditEventDescriptor() {}

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(title, description, priority, eventDate, eventTime, tagList);
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

    public TaskDate getEventDate() {
        return eventDate;
    }

    public TaskTime getEventTime() {
        return eventTime;
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

    public void setEventDate(TaskDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventTime(TaskTime eventTime) {
        this.eventTime = eventTime;
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
        if (!(other instanceof EditEventDescriptor)) {
            return false;
        }

        // state check
        EditEventDescriptor e = (EditEventDescriptor) other;

        return getTitle().equals(e.getTitle())
                && getDescription().equals(e.getDescription())
                && getPriority().equals(e.getPriority())
                && getEventDate().equals(e.getEventDate())
                && getEventTime().equals(e.getEventTime())
                && getTagList().equals(e.getTagList());
    }
}
