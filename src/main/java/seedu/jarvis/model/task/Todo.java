package seedu.jarvis.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    private final String taskId;
    private final String description;

    /**
     * Creates a Todo object that only has taskId and description attributes.
     * For creation of Todo object from CLI jarvis.
     * @param description of Todo
     */
    public Todo(String description) {
        this.taskId = generateTaskId();
        requireNonNull(description);
        this.description = description;
    }

    /**
     * Creates a Todo object that only has taskId and description attributes.
     * For creation of Todo object from jarvis.json data file.
     * @param taskId of Todo
     * @param description of Todo
     */
    public Todo(String taskId, String description) {
        this.taskId = taskId;
        Task.compareTaskNum(taskId);
        this.description = description;
    }

    // ------------------- Getters -----------------------------------------------------------------------------

    public String getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    // ---------------------------------------------------------------------------------------------------------

    /**
     * Returns a unique task id.
     */
    public String generateTaskId() {
        int taskNum = Task.getTaskNum();
        Task.taskNumInc();
        return "T" + taskNum;
    }

    /**
     * Returns true if both tasks have the same task id.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof seedu.jarvis.model.task.Todo)) {
            return false;
        }

        Todo otherTask = (Todo) other;
        return this.taskId.equals(otherTask.getTaskId());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("[")
                .append(getTaskId())
                .append("] ")
                .append(getDescription());

        return builder.toString();
    }
}
