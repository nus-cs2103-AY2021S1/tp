package tp.cap5buddy.todolist;

import java.util.Objects;

public class Task {
    private final Type type;
    private final Description description;
    private final Priority priority;
    private final Date date;
    private final Status status;

    /**
     * Constructs a Task.
     *
     * @param type Type of the task.
     * @param description Description of the task.
     * @param priority Priority of the task.
     * @param date Date or deadline of the task.
     * @param status Status (completed or not completed)of the task.
     */
    public Task(Type type, Description description, Priority priority, Date date, Status status) {
        this.type = type; //t
        this.description = description; //n
        this.priority = priority; //p
        this.date = date; //d
        this.status = status;
    }

    public Type getType() {
        return this.type;
    }

    public Description getDescription() {
        return this.description;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public Date getDate() {
        return this.date;
    }

    public Status getStatus() {
        return this.status;
    }

    public Task setType(Type newType) {
        return new Task(newType, this.description, this.priority, this.date, this.status);
    }

    public Task setDescription(Description editedDescription) {
        return new Task(this.type, editedDescription, this.priority, date, status);
    }

    public Task setPriority(Priority editedPriority) {
        return new Task(this.type, this.description, editedPriority, this.date, this.status);
    }

    public Task setDate(Date editedDate) {
        return new Task(this.type, this.description, this.priority, editedDate, this.status);
    }

    public Task setStatus(Status editedStatus) {
        return new Task(this.type, this.description, this.priority, this.date, editedStatus);
    }

    public Task complete() {
        return setStatus(Status.COMPLETED);
    }

    /**
     * Checks if 2 task have the same type, description, and date/deadline. Weaker than equals() method.
     *
     * @param otherTask
     * @return true if the other task has the same type, description, and date/deadline with this task,
     *         otherwise false.
     */
    public boolean isSameTask(Task otherTask) {
        if (this == otherTask) {
            return true;
        }

        return getType().equals(otherTask.getType())
                && getDescription().equals((otherTask.getDescription()))
                && getDate().equals(otherTask.getDate());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getType().equals(getType())
                && otherTask.getPriority().equals(getPriority())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.type, this.priority, this.description, this.date);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" *Type: ")
                .append(getType())
                .append("\n")
                .append(" *Description: ")
                .append(getDescription())
                .append("\n")
                .append(" *Priority: ")
                .append(getPriority())
                .append("\n")
                .append(" *Date: ")
                .append(getDate())
                .append("\n");
        return builder.toString();
    }
}
