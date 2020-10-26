package seedu.address.model.student.academic;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Attendance {

    private static final DateTimeFormatter INPUT_DEF = DateTimeFormatter.ofPattern("d/M/yy");
    private static final DateTimeFormatter INPUT_ALT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final String ATTENDED_STATUS = "attended";
    private static final String UNATTENDED_STATUS = "unattended";
    public static final String DATE_CONSTRAINTS =
            "Attendance dates should be valid and in the form dd/mm/yy, and should not be blank";
    public static final String STATUS_CONSTRAINTS =
            "Attendance status should be either 'attended' or 'unattended'.";

    public LocalDate lessonDate;
    public boolean hasAttended;
    public Feedback feedback;

    public Attendance(String date, String hasAttended, Feedback feedback) {
        requireAllNonNull(date, hasAttended, feedback);
        checkArgument(isValidDate(date), DATE_CONSTRAINTS);
        checkArgument(isValidAttendanceStatus(hasAttended), STATUS_CONSTRAINTS);

        this.lessonDate = parseDate(date);
        this.hasAttended = parseAttendanceStatus(hasAttended);
        this.feedback = feedback;
    }

    public static LocalDate parseDate(String input) {
        LocalDate date;
        try {
            date = LocalDate.parse(input, INPUT_DEF);
        } catch (DateTimeParseException ignored) {
            date = LocalDate.parse(input, INPUT_ALT);
        }
        return date;
    }

    /**
     * Returns true if a given string is a valid date for {@code Attendance}.
     */
    public static boolean isValidDate(String date) {
        String validationRegex = "(\\d{1,2})(\\/)(\\d{1,2})(\\/)(\\d{2}|\\d{4})";
        if (!date.matches(validationRegex)) {
            return false;
        }

        LocalDate testDate = null;

        for (DateTimeFormatter format : new DateTimeFormatter[] {INPUT_DEF, INPUT_ALT}) {
            try {
                testDate = LocalDate.parse(date, format);
                break;
            } catch (DateTimeParseException ignored) {
                // does not match the DateTimeFormat, try the next
            }
        }
        return testDate != null;
    }

    public static boolean isValidAttendanceStatus(String status) {
        return status.equals(ATTENDED_STATUS) || status.equals(UNATTENDED_STATUS);
    }

    public static boolean parseAttendanceStatus(String status) {
        return status.equals(ATTENDED_STATUS); // if false, then will be equal to UNATTENDED_STATUS
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public String getUserInputDate() {
        return lessonDate.format(OUTPUT);
    }

    public boolean getAttendanceStatus() {
        return hasAttended;
    }

    public Feedback getFeedback() {
        return feedback;
    }
}
