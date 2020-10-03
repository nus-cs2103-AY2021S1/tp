package seedu.address.model.person;

public enum Status {
    ACTIVE, COLD, CLOSED;

    public static final String MESSAGE_CONSTRAINTS = "Status can only be either ACTIVE, COLD or CLOSED";

    public static Status createStatus(String status) {
        return Status.valueOf(status);
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        for (Status s : Status.values()) {
            if (s.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
