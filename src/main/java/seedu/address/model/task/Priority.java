package seedu.address.model.task;

/**
 * Represents the Priority of the task.
 */
public enum Priority {
    HIGHEST,
    HIGH,
    NORMAL,
    LOW;

    public static final String MESSAGE_CONSTRAINTS =
            "There are only 4 priority level that can be chosen: HIGHEST, "
            + "HIGH, NORMAL, and LOW.";

    public static Priority getPriority(String priority) {
        String inputAllUpperCase = priority.toUpperCase();
        switch (inputAllUpperCase) {
        case ("HIGHEST"):
            return HIGHEST;
        case ("HIGH"):
            return HIGH;
        case ("NORMAL"):
            return NORMAL;
        default:
            return LOW;
        }
    }

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
        case ("HIGHEST"):
        case ("HIGH"):
        case ("NORMAL"):
        case ("LOW"):
            return true;
        default:
            return false;
        }
    }
}
