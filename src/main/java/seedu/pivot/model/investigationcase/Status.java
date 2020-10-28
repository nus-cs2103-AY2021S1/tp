package seedu.pivot.model.investigationcase;

import static java.util.Objects.requireNonNull;

public enum Status {
    ACTIVE, COLD, CLOSED;

    public static final String MESSAGE_CONSTRAINTS = "Status can only be either active, cold or closed";

    /**
     * Creates a Status Enum Object using the given status.
     * @param status
     * @return Status
     */
    public static Status createStatus(String status) {
        requireNonNull(status);
        return Status.valueOf(status.trim().toUpperCase());
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        requireNonNull(test);
        for (Status status : Status.values()) {
            if (status.name().equals(test.trim().toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
