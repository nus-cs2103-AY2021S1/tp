package seedu.taskmaster.model.record;

public enum AttendanceType {
    PRESENT,
    ABSENT,
    NO_RECORD;

    public static final String MESSAGE_CONSTRAINTS = "StudentRecord type can only be 'present', 'absent' or 'empty.";

    /**
     * Returns true if a given string is a valid attendance type.
     */
    public static boolean isValidAttendanceType(String test) {
        try {
            AttendanceType.valueOf(test);
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }
}
