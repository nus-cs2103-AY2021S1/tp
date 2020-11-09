package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Attendance;

/**
 * Jackson-friendly version of week number.
 */
class JsonAdaptedWeekNumber {

    private final String weekNumber;

    /**
     * Constructs a {@code JsonAdaptedWeekNumber} with the given {@code weekNumber}.
     */
    @JsonCreator
    public JsonAdaptedWeekNumber(@JsonProperty("weekNumber") String weekNumber) {
        this.weekNumber = weekNumber;
    }

    /**
     * Converts this Jackson-friendly adapted week number object into a string.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted week number.
     */
    public String toModelType() throws IllegalValueException {
        if (!Attendance.isValidWeekNumber(weekNumber)) {
            throw new IllegalValueException(String.format((Attendance.WEEK_NUMBER_CONSTRAINTS),
                    Attendance.MIN_WEEK, Attendance.MAX_WEEK));
        }
        return weekNumber;
    }

}
