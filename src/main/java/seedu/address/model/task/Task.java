package seedu.address.model.task;

/**
 * Represents a Task in the todo list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    private final TaskName name;
    private final Type type;
    private final Priority priority;
    private final Date date;
    private final Status status;

    /**
     * Represents the task object constructor.
     * Every field must be present and not null.
     *
     * @param name name of the task.
     */
    public Task(TaskName name, Type type, Priority priority, Date date, Status status) {
        this.name = name;
        this.type = type;
        this.priority = priority;
        this.date = date;
        this.status = status;
    }

    /**
     * Returns the name of the task.
     * @return name of the task
     */
    public TaskName getName() {
        return this.name;
    }

    /**
     * Returs the type of the task.
     *
     * @return type of the task
     */
    public Type getType() {
        return type;
    }

    /**
     * Returns the priority of the task.
     *
     * @return priority of the task
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * Returns the date of the task.
     *
     * @return date of the task
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the status of the task.
     *
     * @return status of the task
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Returns true if both task of the same name have the same type and date.
     * This defines a weaker notion of equality between two tasks.
     *
     * @param otherTask other task to be compared
     * @return true if both task has the same name, type, and date
     */
    public boolean isSameTask(Task otherTask) {
        if (this == otherTask) {
            return true;
        }

        return getType().equals(otherTask.getType())
            && getName().equals((otherTask.getName()))
            && getDate().equals(otherTask.getDate());
    }

    /**
     * Checks if two tasks are equal.
     * This defines a stronger equality between two tasks.
     *
     * @param other other task to be compared
     * @return true if both task have the same name, type, priority, date, and status
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(getName())
            && otherTask.getType().equals(getType())
            && otherTask.getPriority().equals(getPriority())
            && otherTask.getDate().equals(getDate())
            && otherTask.getStatus().equals(getStatus());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" *Name: ")
                .append(getName())
                .append("\n")
                .append(" *Type: ")
                .append(getType())
                .append("\n")
                .append(" *Priority: ")
                .append(getPriority())
                .append("\n")
                .append(" *Date: ")
                .append(getDate())
                .append("\n")
                .append("Status: ")
                .append(getStatus())
                .append("\n");
        return builder.toString();
    }

}
