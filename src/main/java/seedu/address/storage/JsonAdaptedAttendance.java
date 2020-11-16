package seedu.address.storage;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.model.attendance.Attendance;

/**
 * Jackson-friendly version of {@link Attendance}.
 */
class JsonAdaptedAttendance {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private final String dateString;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given {@code dateString}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(String dateString) {
        assert(dateString != null && !dateString.equals(""));
        this.dateString = dateString;
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        dateString = source.toString();
    }

    @JsonValue
    public String getDateString() {
        assert(dateString != null && !dateString.equals(""));
        return dateString;
    }

    /**
     * Converts this Jackson-friendly adapted attendance object into the model's {@code Attendance} object.
     *
     */
    public Attendance toModelType() {
        assert(dateString != null && !dateString.equals(""));
        return Attendance.fromDateString(dateString);
    }

}
