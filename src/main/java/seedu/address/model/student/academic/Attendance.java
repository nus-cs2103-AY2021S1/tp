package seedu.address.model.student.academic;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.commons.util.DateUtil.getInputFormat;
import static seedu.address.commons.util.DateUtil.print;

import java.time.LocalDate;

public class Attendance {

    public static final String DATE_CONSTRAINTS =
            "Attendance dates should be valid and in the form dd/mm/yy, and should not be blank";
    public static final String STATUS_CONSTRAINTS =
            "Attendance status should be either 'present' or 'absent'.";

    private static final String PRESENT_STATUS = "present";
    private static final String ABSENT_STATUS = "absent";

    private LocalDate lessonDate;
    private boolean isPresent;
    private Feedback feedback;

    /**
     * Constructs a {@code Attendance} object.
     * @param date date of lesson
     * @param isPresent whether student was present for lesson
     * @param feedback feedback for lesson
     */
    public Attendance(LocalDate date, String isPresent, Feedback feedback) {
        requireAllNonNull(date, isPresent, feedback);
        checkArgument(isValidAttendanceStatus(isPresent), STATUS_CONSTRAINTS);

        this.lessonDate = date;
        this.isPresent = parseAttendanceStatus(isPresent);
        this.feedback = feedback;
    }

    public static boolean isValidAttendanceStatus(String status) {
        return status.equals(PRESENT_STATUS) || status.equals(ABSENT_STATUS);
    }

    public static boolean parseAttendanceStatus(String status) {
        return status.equals(PRESENT_STATUS); // if false, then will be equal to ABSENT_STATUS
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public String getUserInputDate() {
        return getInputFormat(lessonDate);
    }

    public boolean getAttendanceStatus() {
        return isPresent;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) %s",
                print(lessonDate), (getAttendanceStatus() ? "\u2713" : "\u2718"), getFeedback());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Attendance)) {
            return false;
        }

        Attendance other = (Attendance) obj;
        return other.getLessonDate().equals(getLessonDate())
                && other.getAttendanceStatus() == getAttendanceStatus()
                && other.getFeedback().equals(getFeedback());
    }
}
