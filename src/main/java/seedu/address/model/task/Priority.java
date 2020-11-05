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
     * [method might be used in the future to improve SLAP in ParserUtil if needed]
     * Returns true if a given string is a valid priority.
     *
     * @param test given string
     * @return true if priority is valid
     */
    public static boolean isValidPriority(String test) {
        String inputAllUpperCase = test.toUpperCase();
        switch(inputAllUpperCase) {
        case ("HIGH"):
        case ("NORMAL"):
        case ("LOW"):
            return true;
        default:
            return false;
        }
    }
}
