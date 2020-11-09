package seedu.address.model.task;

/**
 * Represents the Priority of the task.
 */
public enum Priority {
    HIGH,
    NORMAL,
    LOW;

    public static final String MESSAGE_CONSTRAINTS =
            "There are only 3 priority level that can be chosen: "
            + "HIGH, NORMAL, and LOW.";

    /**
     * Returns true if a given string is a valid priority.
     *
     * @param test given string
     * @return true if priority is valid
     */
    public static boolean isValidPriority(String test) {
        switch(test) {
        case ("HIGH"):
        case ("NORMAL"):
        case ("LOW"):
            return true;
        default:
            return false;
        }
    }
}
