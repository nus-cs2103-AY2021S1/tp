package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Refactor Person Class";
    public static final String DEFAULT_DEADLINE = "29-02-2020 00:00:00";
    public static final String DEFAULT_DESCRIPTION = "refactor person class into something relevant to our project";
    public static final Double DEFAULT_PROGRESS = 0.0;
    public static final Boolean DEFAULT_IS_DONE = false;

    private String taskName;
    private String description;
    private Optional<Deadline> deadline;
    private Double progress;
    private Boolean isDone;
    private Set<String> assignees;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = DEFAULT_TASK_NAME;
        deadline = Optional.of(new Deadline(DEFAULT_DEADLINE));
        description = DEFAULT_DESCRIPTION;
        progress = DEFAULT_PROGRESS;
        isDone = DEFAULT_IS_DONE;
        assignees = new HashSet<>();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        deadline = taskToCopy.getDeadline();
        taskName = taskToCopy.getTaskName();
        description = taskToCopy.getDescription();
        progress = taskToCopy.getProgress();
        isDone = taskToCopy.isDone();
        assignees = taskToCopy.getAssignees();
    }

    /**
     * Sets the {@code taskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskName(String taskName) {
        this.taskName = taskName;
        return this;
    }

    /**
     * Sets the {@code ProjectDescription} of the {@code Project} that we are building.
     */
    public TaskBuilder withTaskDescription(String taskDescription) {
        this.description = taskDescription;
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = Optional.of(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code progress} of the {@code Task} that we are building.
     */
    public TaskBuilder withProgress(String progress) {
        this.progress = Double.parseDouble(progress);
        return this;
    }

    /**
     * Sets the {@code isDone} of the {@code Task} that we are building.
     */
    public TaskBuilder withCompletion(String isDone) {
        this.isDone = Boolean.parseBoolean(isDone);
        return this;
    }

    /**
     * Parses the {@code assignees} into a {@code Set<String>} and set it to the {@code Task}
     * that we are building.
     */
    public TaskBuilder withAssignees(String... assignees) {
        this.assignees = SampleDataUtil.getAssigneeSet(assignees);
        return this;
    }

    /**
     * Creates a Task
     *
     * @return task sample
     */
    public Task build() {
        return new Task(taskName, description, deadline.orElse(null), progress,
                isDone);
    }

}
