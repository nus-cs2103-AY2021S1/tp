package seedu.address.model.student.academic;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents all academic details of a Student in Reeve.
 * Consists of exams, homework and attendance.
 */
public class Academic {

    private final List<Attendance> attendance;

    /**
     * Builds an Academic object.
     * @param attendance list of attendance for student.
     */
    public Academic(List<Attendance> attendance) {
        requireAllNonNull(attendance);
        this.attendance = attendance;
    }

    public List<Attendance> getAttendance() {
        return attendance;
    }

    /**
     * Get Attendance of student formatted for GUI use.
     * @return formatted exams.
     */
    public String getFormattedAttendance() {
        String result = "";
        int index = 1;
        for (Attendance singleAttendance : attendance) {
            result = result + index + ". " + singleAttendance + "\n";
            index++;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Academic)) {
            return false;
        }

        Academic other = (Academic) obj;
        return other.getAttendance().equals(getAttendance());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (!attendance.isEmpty()) {
            builder.append("\nAttendance:\n");
            String detailList = attendance.stream()
                    .map(detail -> String.format("- %s", detail))
                    .collect(Collectors.joining("\n"));
            builder.append(detailList);
        }
        return builder.toString();
    }

}
