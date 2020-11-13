package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.participation.Participation;
import seedu.address.model.person.GitUserName;
import seedu.address.model.project.Deadline;

/**
 * Represents a Task of a project.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task implements Comparable<Task> {

    public static final String TASK_NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String DESCRIPTION_VALIDATION_REGEX = "[^\\s].*";

    public static final String NAME_MESSAGE_CONSTRAINTS =
            "Task name should only contain alphanumeric characters and spaces, and it should not be blank.";
    public static final String DESCRIPTION_MESSAGE_CONSTRAINTS =
            "Task description can be any values, and it should not be blank.";
    public static final String PUBLISH_DATE_MESSAGE_CONSTRAINTS =
            "Publish date should only be in the format of dd-MM-yyyy.";
    public static final String PROGRESS_MESSAGE_CONSTRAINTS =
            "Progress values should only contain values between 0 and 100 inclusive, and it should not be blank.";
    public static final String IS_DONE_MESSAGE_CONSTRAINTS =
            "Done status should only be \"true\" or \"false\".";

    private final String taskName;
    private final String description;
    private final Deadline deadline;
    private final Double progress;
    private final Boolean isDone;
    private final Set<String> assignees;
    private LocalDate publishDate;

    /**
     * Name and progress should be present and not null. description and deadline can be null.
     */
    public Task(String taskName, String description, Deadline deadline, double progress) {
        requireAllNonNull(taskName, progress);
        this.taskName = taskName;
        if (description == null) {
            this.description = "";
        } else {
            this.description = description;
        }
        publishDate = LocalDate.now();
        if (deadline == null) {
            this.deadline = null;
        } else {
            this.deadline = deadline;
        }
        this.progress = progress;
        if (progress == 100.00) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
        this.assignees = new HashSet<>();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(TASK_NAME_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidTaskDescription(String test) {
        if (test.isBlank()) {
            return true;
        } else {
            return test.matches(DESCRIPTION_VALIDATION_REGEX);
        }
    }

    /**
     * Returns true if a given string is a valid publish date.
     */
    public static boolean isValidPublishDate(String test) {
        return Date.isValidDate(test);
    }

    /**
     * Returns true if a given string is a valid progress value.
     */
    public static boolean isValidProgress(String test) {
        if (test == null) {
            return false;
        }
        if (test.isEmpty()) {
            return false;
        }
        try {
            Double t = Double.valueOf(test);
            return 0 <= t && t <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid isDone value.
     */
    public static boolean isValidIsDone(String test) {
        if (test.equals("false")) {
            return true;
        } else {
            return Boolean.parseBoolean(test);
        }
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

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Optional<Deadline> getDeadline() {
        return Optional.ofNullable(deadline);
    }

    public Double getProgress() {
        return progress;
    }

    public Set<String> getAssignees() {
        return assignees;
    }

    public Boolean isDone() {
        return isDone;
    }

    public boolean hasAssignee(Participation assignee) {
        return assignees.contains(assignee.getPerson().toString());
    }

    public boolean hasAssigneeOfName(String assignee) {
        return assignees.contains(assignee);
    }

    /**
     * Removes the assignee with the given name.
     *
     * @param assignee username of the assignee to be removed from the task
     */
    public void removeAssigneeOfName(String assignee) {
        if (hasAssigneeOfName(assignee)) {
            assignees.remove(assignee);
        }
    }

    public boolean hasAnyAssignee() {
        return !assignees.isEmpty();
    }

    /**
     * Returns true if the task's due date is between the given start date and end date.
     *
     * @param start the start date of the time range
     * @param end   the end date of the time range
     */
    public boolean isDueBetween(Date start, Date end) {
        assert (start != null && end != null);
        if (this.deadline == null) {
            return false;
        }
        return this.deadline.isWithinTimeRange(start, end);
    }

    /**
     * Checks if the task has an assignee whose name matches the given name.
     *
     * @param assigneeGitUserName the assignee's name to look for.
     * @return true if this task has an assignee whose name matches the given name, and false otherwise.
     */
    public boolean hasAssigneeWhoseGitNameIs(GitUserName assigneeGitUserName) {
        return assignees.stream()
                .anyMatch(assignee -> assignee.equals(assigneeGitUserName.toString()));
    }

    /**
     * Checks if the task is due on the given deadline.
     *
     * @param deadline the given deadline to check.
     * @return true if the task is due on the given deadline, and false otherwise.
     */
    public boolean isDueOn(Deadline deadline) {
        assert (deadline != null);
        if (this.deadline == null) {
            return false;
        } else {
            return this.deadline.equals(deadline);
        }
    }

    public boolean addAssignee(String assignee) {
        return assignees.add(assignee);
    }

    /**
     * Returns true if name and deadline fields are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return getTaskName().equals(task.getTaskName())
                && getDeadline().equals(task.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskName(), getDescription(), getDeadline(), getProgress(), isDone(), getAssignees());
    }

    @Override
    public String toString() {
        return "[" + taskName + "]";
    }

    /**
     * By default, two tasks are compared using their deadlines.
     * Tasks with no deadline are consider "larger",
     * so that they will be listed at the end when sorted by default.
     *
     * @param task the task to compare with
     */
    @Override
    public int compareTo(Task task) {
        if (this.deadline == null && task.deadline == null) {
            return 0;
        } else if (this.deadline == null) {
            return 1;
        } else if (task.deadline == null) {
            return -1;
        } else {
            return this.deadline.compareTo(task.deadline);
        }
    }
}
