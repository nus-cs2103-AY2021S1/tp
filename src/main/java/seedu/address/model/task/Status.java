package seedu.address.model.task;

/**
 * Represents the status of the task.
 */
public enum Status {
    COMPLETED,
    NOT_COMPLETED;

    public static final String MESSAGE_CONSTRAINTS = "There are only 2 possible statuses for a task: "
                    + "COMPLETED and INCOMPLETE.";


    /**
     * Returns true if a given string is a valid status.
     *
     * @param test given string
     * @return true if status is valid
     */
    public static boolean isValidStatus(String test) {
        switch(test) {
        case ("COMPLETED"):
        case ("NOT_COMPLETED"):
            return true;
        default:
            return false;
        }
    }
}
