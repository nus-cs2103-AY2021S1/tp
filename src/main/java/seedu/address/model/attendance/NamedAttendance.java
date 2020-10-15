package seedu.address.model.attendance;

import seedu.address.model.student.Name;

/**
 * Represents an object encapsulating an attendance and the name of the student in the attendance.
 */
public class NamedAttendance {
    private final Name name;
    private final Attendance attendance;

    /**
     * Creates a new NamedAttendance object.
     * @param name
     * @param attendance
     */
    public NamedAttendance(Name name, Attendance attendance) {
        this.name = name;
        this.attendance = attendance;
    }

    public Name getName() {
        return name;
    }

    public Attendance getAttendance() {
        return attendance;
    }
}
