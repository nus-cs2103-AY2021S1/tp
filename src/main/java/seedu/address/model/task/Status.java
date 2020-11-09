package seedu.address.model.task;

/**
 * Represents the status of the task.
 */
public enum Status {
    COMPLETED,
    NOT_COMPLETED;

    public static final String MESSAGE_CONSTRAINTS = "There are only 2 possible statuses for a task: "
                    + "COMPLETED and INCOMPLETE.";

}
