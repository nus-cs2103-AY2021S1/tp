package seedu.tr4cker.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;
import seedu.tr4cker.model.util.SampleDataUtil;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_NAME = "Task1 Name";
    public static final String DEFAULT_DEADLINE = "01-Dec-2021 2359";
    public static final int DEFAULT_COMPLETION_STATUS = 0;
    public static final String DEFAULT_DESCRIPTION = "Task 1 description";

    private Name name;
    private Deadline deadline;
    private CompletionStatus completionStatus;
    private TaskDescription taskDescription;
    private Set<ModuleCode> moduleCode;
    private Set<Tag> tags;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        name = new Name(DEFAULT_NAME);
        deadline = new Deadline(DEFAULT_DEADLINE, false);
        completionStatus = new CompletionStatus(DEFAULT_COMPLETION_STATUS);
        taskDescription = new TaskDescription(DEFAULT_DESCRIPTION);
        moduleCode = new HashSet<>();
        tags = new HashSet<>();
        tags.add(new Tag("homework"));
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        name = taskToCopy.getName();
        deadline = taskToCopy.getDeadline();
        completionStatus = taskToCopy.getCompletionStatus();
        taskDescription = taskToCopy.getTaskDescription();
        moduleCode = new HashSet<>(taskToCopy.getModuleCode());
        tags = new HashSet<>(taskToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Task} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code moduleCode} to a new {@code Set<ModuleCode>}.
     * Parses the {@code codeName} into a {@code ModuleCode} adds it to {@code moduleCode} of
     * the {@code Task} we are building.
     */
    public TaskBuilder withModule(String codeName) {
        this.moduleCode = SampleDataUtil.getModuleCodeSet(codeName);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code description} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDescription(String description) {
        this.taskDescription = new TaskDescription(description);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline, false);
        return this;
    }

    /**
     * Sets the {@code CompletionStatus} of the {@code Task} that we are building.
     * @param completionStatus
     */
    public TaskBuilder withCompletionStatus(int completionStatus) {
        this.completionStatus = new CompletionStatus(completionStatus);
        return this;
    }

    public Task build() {
        return new Task(name, deadline, completionStatus, taskDescription, moduleCode, tags);
    }

}
