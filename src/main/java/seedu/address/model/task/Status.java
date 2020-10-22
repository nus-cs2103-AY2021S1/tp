package seedu.address.model.task;

/**
 * Represents the status of the task.
 */
public enum Status {
    COMPLETED,
    NOT_COMPLETED;
    public static Status getStatus(String status) {
        String inputAllUpperCase = status.toUpperCase();
        if (inputAllUpperCase == "COMPLETED") {
            return COMPLETED;
        } else {
            return NOT_COMPLETED;
        }
    }
}
