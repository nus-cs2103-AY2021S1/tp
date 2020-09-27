package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a Task of a project.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    public final String taskName;
    private final String description;
    private final LocalDate publishDate;
    private final LocalDateTime deadline;
    private final double progress;
    private final boolean isDone;

    /**
     * name, progress, and isDone should be present and not null. description and deadline can be null.
     */
    public Task(String taskName, String description, LocalDateTime deadline, double progress, boolean isDone) {
        requireAllNonNull(taskName, progress, isDone);
        this.taskName = taskName;
        this.description = description;
        publishDate = LocalDate.now();
        this.deadline = deadline;
        this.progress = progress;
        this.isDone = isDone;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public double getProgress() {
        return progress;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if all fields are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Double.compare(task.getProgress(), getProgress()) == 0
                && getTaskName().equals(task.getTaskName())
                && (getDescription() == task.getDescription() || getDescription().equals(task.getDescription()))
                && getPublishDate().equals(task.getPublishDate())
                && Objects.equals(getDeadline(), task.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskName(), getDescription(), getPublishDate(), getDeadline(), getProgress(), isDone());
    }

    @Override
    public String toString() {
        return "[" + taskName + "]";
    }

    // TODO: may add isValidTask method.
}
