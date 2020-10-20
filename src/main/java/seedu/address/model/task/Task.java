package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.GitUserName;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Participation;

/**
 * Represents a Task of a project.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    public static final String MESSAGE_CONSTRAINTS =
        "Attributes should only contain alphanumeric characters and spaces, and it should not be blank";
    /*
     * The first character of an attribute must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String taskName;
    private final String description;
    private final LocalDate publishDate;
    private final Deadline deadline;
    private final double progress;
    private final boolean isDone;
    private Set<Participation> assignees;

    /**
     * name, progress, and isDone should be present and not null. description and deadline can be null.
     */
    public Task(String taskName, String description, Deadline deadline, double progress, boolean isDone) {
        requireAllNonNull(taskName, progress, isDone);
        this.taskName = taskName;
        this.description = description;
        publishDate = LocalDate.now();
        this.deadline = deadline;
        this.progress = progress;
        this.isDone = isDone;
        this.assignees = new HashSet<>();
    }

    /**
     * Returns true if a given string is a valid attribute.
     */
    public static boolean isValidAttribute(String test) {
        return test.matches(VALIDATION_REGEX);
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

    public Deadline getDeadline() {
        return deadline;
    }

    public double getProgress() {
        return progress;
    }

    public Set<Participation> getAssignees() {
        return assignees;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean hasAssignee(Participation assignee) {
        return assignees.contains(assignee);
    }

    /**
     * Checks if the task has an assignee whose name matches the given name.
     *
     * @param assigneeGitUserName the assignee's name to look for
     * @return true if this task has an assignee whose name matches the given name,
     * and false otherwise
     */
    public boolean hasAssigneeWhoseGitNameIs(GitUserName assigneeGitUserName) {
        return assignees.stream()
            .anyMatch(assignee -> assignee.getAssigneeGitName().equals(assigneeGitUserName));
    }

    /**
     * Checks if the task is due on the given deadline.
     * @param deadline  the given deadline to check
     * @return  true if the task is due on the given deadline, and false otherwise
     */
    public boolean isDueOn(Deadline deadline) {
        assert (deadline != null);
        if (this.deadline == null) {
            return false;
        } else {
            return this.deadline.equals(deadline);
        }
    }
    public boolean addAssignee(Participation assignee) {
        return assignees.add(assignee);
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
