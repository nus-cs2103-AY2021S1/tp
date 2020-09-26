package seedu.address.model.attendance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.model.student.Student;

/**
 * Represents an Attendance for the student list.
 */
public class Attendance {
    private static final String ATTENDANCE_STRING_FORMAT = "%s|%s\n";

    private HashMap<String, AttendanceType> attendanceList;

    private Attendance() {
        attendanceList = new HashMap<>();
    }

    public Attendance(HashMap<String, AttendanceType> attendanceList) {
        this.attendanceList = attendanceList;
    }

    /**
     * Initialises and returns an {@code Attendance} list of {@code students}.
     * Each student has an attendance type of {@code NO_RECORD}.
     */
    public static Attendance of(List<Student> students) {
        Attendance attendance = new Attendance();
        for (Student student : students) {
            attendance.markStudentAttendance(student.getNusnetId().value, AttendanceType.NO_RECORD);
        }
        return attendance;
    }

    /**
     * Marks the attendance of a student represented by their {@code nusnetId} with {@code attendanceType}.
     */
    public void markStudentAttendance(String nusnetId, AttendanceType attendanceType) {
        attendanceList.put(nusnetId, attendanceType);
    }

    /**
     * Marks the attendance of all students represented by the list of {@code nusnetIds} with {@code attendanceType}.
     */
    public void markAllAttendance(List<String> nusnetIds, AttendanceType attendanceType) {
        for (String nusnetId : nusnetIds) {
            attendanceList.put(nusnetId, attendanceType);
        }
    }

    @Override
    public String toString() {
        String result = "";

        for (Map.Entry<String, AttendanceType> set : attendanceList.entrySet()) {
            result += String.format(ATTENDANCE_STRING_FORMAT, set.getKey(), set.getValue().name());
        }

        return result;
    }
}
