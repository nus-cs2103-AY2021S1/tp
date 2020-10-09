package seedu.address.model.attendance;

import seedu.address.model.student.Name;

public class NamedAttendance {
    private final Name name;
    private final Attendance attendance;

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
