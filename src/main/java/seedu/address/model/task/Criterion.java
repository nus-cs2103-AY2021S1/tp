package seedu.address.model.task;

/**
 * Represents the Criterion that is related to tasks.
 */
public enum Criterion {
    DESCRIPTION,
    DATE,
    PRIORITY;

    public static final String MESSAGE_CONSTRAINTS =
            "There are only 3 criterion that can be chosen: DESCTIPTION(DESC), "
            + "DEADLINE(DATE), and PRIORITY(PRIO).";
}
