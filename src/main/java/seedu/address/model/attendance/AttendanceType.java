package seedu.address.model.attendance;

public enum AttendanceType {
    PRESENT,
    ABSENT,
    NO_RECORD;
    
    public static final String MESSAGE_CONSTRAINTS = "Attendance type can only be 'present', 'absent' or 'empty.";
    
    public static boolean isValidAttendanceType(String test) {
        try {
            AttendanceType.valueOf(test);
            return true;
        } catch (IllegalArgumentException iae) {
            return false;
        }
    }
}
