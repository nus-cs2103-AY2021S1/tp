package seedu.address.model.task;

/**
 * Represents the Criterion that is related to tasks.
 */
public enum Criterion {
    NAME,
    DATE,
    PRIORITY;

    public static final String MESSAGE_CONSTRAINTS =
            "There are only 3 criterion that can be chosen: NAME, "
            + "DEADLINE(DATE), and PRIORITY(PRIO).";

    /**
     * Returns true if a given string is a valid criterion.
     *
     * @param test given string
     * @return true if criterion is valid
     */
    public static boolean isValidCriterion(String test) {
        switch(test) {
        case ("NAME"):
        case ("DATE"):
        case ("PRIORITY"):
            return true;
        default:
            return false;
        }
    }
}
