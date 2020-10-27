package seedu.address.model.task.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Deadline;


/**
 * Represents a Task in the PlaNus task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event extends Task {
    // Data fields
    private final StartDateTime startDateTime;
    private final EndDateTime endDateTime;
    private final boolean isLesson;

    /**
     * Every field must be present and not null.
     */
    public Event(Title title, StartDateTime startDateTime, EndDateTime endDateTime, Description description,
                  Tag tag, boolean isLesson) {
        super(title, description, tag);
        requireAllNonNull(startDateTime, endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isLesson = isLesson;
    }

    public static Event createLessonEvent(Title title, StartDateTime startDateTime, EndDateTime endDateTime,
                                          Description description, Tag tag) {
        return new Event(title, startDateTime, endDateTime, description, tag, true);
    }

    public static Event createUserEvent(Title title, StartDateTime startDateTime, EndDateTime endDateTime,
                                        Description description, Tag tag) {
        return new Event(title, startDateTime, endDateTime, description, tag, false);
    }

    public boolean isLesson() {
        return isLesson;
    }

    public StartDateTime getStartDateTime() {
        return startDateTime;
    }

    public EndDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalDateTime getEndDateTimeValue() {
        return endDateTime.getValue();
    }

    public LocalDateTime getStartDateTimeValue() {
        return startDateTime.getValue();
    }

    /**
     * Returns an immutable tag, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return tag;
    }

    @Override
    public int getTimeTaken() {
        return (int) Duration.between(getStartDateTimeValue(), getEndDateTimeValue()).toMinutes();
    }
    @Override
    public LocalDate getDate() {
        return this.startDateTime.getValue().toLocalDate();
    }
    @Override
    public boolean isSameTask(Task otherTask) {
        if (otherTask instanceof Event) {
            return isSameEvent((Event) otherTask);
        } else {
            return false;
        }
    }
    /**
     * Returns true if both events of the same title, start and end datetime.
     * This defines a strong notion of equality between two events to allow recurring events yet preventing duplicates.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }
        return otherEvent != null
                && otherEvent.getTitle().equals(getTitle())
                && otherEvent.getStartDateTime().equals(getStartDateTime())
                && otherEvent.getEndDateTime().equals(getEndDateTime());
    }


    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return otherEvent.getTitle().equals(getTitle())
                && otherEvent.getStartDateTime().equals(getStartDateTime())
                && otherEvent.getEndDateTime().equals(getEndDateTime())
                && otherEvent.getDescription().equals(getDescription())
                && otherEvent.getTag().equals(getTag())
                && otherEvent.isLesson() == isLesson();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, startDateTime, endDateTime, description, tag, isLesson);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (isLesson) {
            builder.append("Lesson: ");
        }
        builder.append(getTitle())
                .append(" From: ")
                .append(getStartDateTime())
                .append(" To: ")
                .append(getEndDateTime())
                .append(" Description: ")
                .append(getDescription())
                .append(" Tag: ");
        return builder.toString();
    }

    /**
     * compares event with another task object, if the otherTask is also an event, compare there endDateTime,
     * if another task is deadline object, check whether if has the deadlineDateTime filled by user, if exits,
     * compare the this event's entDateTime with the deadlineDateTime, if not exits, the event is consider to be
     * before the deadline.
     */
    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof Event) {
            return getEndDateTimeValue().compareTo(((Event) otherTask).getEndDateTimeValue());
        } else {
            Deadline deadline = (Deadline) otherTask;
            if (deadline.isDeadlineDateTimeFilled()) {
                return getEndDateTimeValue().compareTo(deadline.getDeadlineDateTimeValue());
            } else {
                return -1;
            }
        }
    }

}
