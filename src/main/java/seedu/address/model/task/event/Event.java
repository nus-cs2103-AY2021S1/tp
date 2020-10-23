package seedu.address.model.task.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;

/**
 * Represents a Task in the PlaNus task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event extends Task {

    // Identity fields

    // Data fields
    private final StartDateTime startDateTime;
    private final EndDateTime endDateTime;
    private final boolean isLesson;

    /**
     * Every field must be present and not null.
     */
    private Event(Title title, StartDateTime startDateTime, EndDateTime endDateTime, Description description, Set<Tag> tags, boolean isLesson) {
        super(title, description, tags);
        requireAllNonNull(startDateTime, endDateTime);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isLesson = isLesson;
    }

    public static Event createLessonEvent(Title title, StartDateTime startDateTime, EndDateTime endDateTime, Description description, Set<Tag> tags) {
        return new Event(title, startDateTime, endDateTime, description, tags, true);
    }

    public static Event createUserEvent(Title title, StartDateTime startDateTime, EndDateTime endDateTime, Description description, Set<Tag> tags) {
        return new Event(title, startDateTime, endDateTime, description, tags, false);
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && otherEvent.getTags().equals(getTags())
                && otherEvent.isLesson() == isLesson();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, startDateTime, endDateTime, description, tags, isLesson);
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
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
