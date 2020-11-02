package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Attendance;

/**
 * Jackson-friendly version of attendance records.
 */
class JsonAdaptedAttendance {

    private final String weekNum;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given {@code isPresent}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty String weekNum) {
        this.weekNum = weekNum;
    }

    @JsonValue
    public String getWeekNum() {
        return weekNum;
    }

    /**
     * Converts this Jackson-friendly adapted attendance record into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Attendance toModelType() throws IllegalValueException {
        if (!Attendance.isValidAttendance(weekNum)) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        Attendance attendance = new Attendance();
        attendance.addAttendance(weekNum);
        return attendance;
    }

}
