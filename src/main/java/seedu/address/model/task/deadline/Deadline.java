package seedu.address.model.task.deadline;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.event.Event;

/**
 * Represents a Deadline in the PlaNus task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Deadline extends Task {

    // Identity fields
    private final DeadlineDateTime deadlineDateTime;

    // Data fields
    private final Status status;
    private final Duration duration;
    private final DoneDateTime doneDateTime;

    /**
     * Every field must be present and not null.
     */
    public Deadline(Title title, DeadlineDateTime deadlineDateTime, Description description, Tag tag,
                     Status status, Duration duration, DoneDateTime doneDateTime) {
        super(title, description, tag);
        requireAllNonNull(deadlineDateTime);
        this.deadlineDateTime = deadlineDateTime;
        this.status = status;
        this.duration = duration;
        this.doneDateTime = doneDateTime;
    }

    /**
     * Factory method to create a new Deadline object
     */
    public static Deadline createDeadline(Title title, DeadlineDateTime deadlineDateTime,
                                          Description description, Tag tag) {
        return new Deadline(title, deadlineDateTime, description, tag, Status.createIncompleteStatus(),
                Duration.createNullDuration(), DoneDateTime.createNullDoneDateTime());
    }

    public DeadlineDateTime getDeadlineDateTime() {
        return deadlineDateTime;
    }

    public DoneDateTime getDoneDateTime() {
        return doneDateTime;
    }

    public Duration getDuration() {
        return duration;
    }
    @Override
    public int getTimeTaken() {
        return duration.valueInMinutes;
    }
    @Override
    public LocalDate getDate() {
        return this.doneDateTime.getValue().toLocalDate();
    }
    @Override
    public boolean isLesson() {
        return false;
    }

    public boolean isDeadlineDateTimeFilled() {
        return deadlineDateTime.isFilled();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return tag;
    }

    @Override
    public boolean isSameTask(Task otherTask) {
        if (otherTask instanceof Deadline) {
            return isSameDeadline((Deadline) otherTask);
        } else {
            return false;
        }
    }

    public Status getStatus() {
        return status;
    }

    public boolean isDone() {
        return status.isCompleted;
    }

    /**
     * Returns true if both deadlines of the same title, date and time.
     */
    private boolean isSameDeadline(Deadline otherDeadline) {
        if (otherDeadline == this) {
            return true;
        }

        return otherDeadline != null
                && otherDeadline.getTitle().equals(getTitle())
                && otherDeadline.getDeadlineDateTime().equals(getDeadlineDateTime());
    }

    public LocalDateTime getDeadlineDateTimeValue() {
        return this.deadlineDateTime.getValue();
    }
    /**
     * mark the task as done by updating the status, duration and done time.
     */
    public Deadline markAsDone(int durationInMinutes) {
        return new Deadline(title, deadlineDateTime, description, tag, Status.createCompleteStatus(),
                new Duration(durationInMinutes), DoneDateTime.createDoneNow());
    }

    /**
     * Returns true if both deadlines have the same identity and data fields.
     * This defines a stronger notion of equality between two deadlines.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return otherDeadline.getTitle().equals(getTitle())
                && otherDeadline.getDeadlineDateTime().equals(getDeadlineDateTime())
                && otherDeadline.getDescription().equals(getDescription())
                && otherDeadline.getTag().equals(getTag())
                && otherDeadline.getStatus().equals(getStatus());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, deadlineDateTime, description, tag, status);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Done by: ")
                .append(getDeadlineDateTime())
                .append(" Description: ")
                .append(getDescription())
                .append(" Tag: ")
                .append(getTag());
        builder.append(" Status: ").append(getStatus());
        return builder.toString();
    }

    /**
     * compares deadline with another task object
     */
    @Override
    public int compareTo(Task otherTask) {
        if (otherTask instanceof Event) {
            if (deadlineDateTime.isFilled) {
                return getDeadlineDateTimeValue().compareTo(((Event) otherTask).getEndDateTimeValue());
            } else {
                return 1;
            }
        } else { //otherTask instanceof Deadline
            Deadline deadline = (Deadline) otherTask;
            if (deadline.isDeadlineDateTimeFilled() && deadlineDateTime.isFilled) {
                return getDeadlineDateTimeValue().compareTo(deadline.getDeadlineDateTimeValue());
            } else if (deadline.isDeadlineDateTimeFilled() && !deadlineDateTime.isFilled) {
                return 1;
            } else if (!deadline.isDeadlineDateTimeFilled() && deadlineDateTime.isFilled) {
                return -1;
            } else {
                return getTitle().toString().compareTo(deadline.getTitle().toString());
            }
        }
    }

}
