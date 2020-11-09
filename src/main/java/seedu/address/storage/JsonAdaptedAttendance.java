package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Attendance;

/**
 * Jackson-friendly version of attendance records.
 */
class JsonAdaptedAttendance {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Attendance's %s field is missing!";

    private final List<JsonAdaptedWeekNumber> weekNumbers = new ArrayList<>();
    private final String participationScore;

    /**
     * Constructs a {@code JsonAdaptedAttendance} with the given {@code isPresent}.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("weekNumbers") List<JsonAdaptedWeekNumber> weekNums,
                                 @JsonProperty("participationScore") String participationScore) {
        if (weekNums != null) {
            this.weekNumbers.addAll(weekNums);
        }
        this.participationScore = participationScore;
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance (Attendance source) {
        assert source != null;
        boolean[] attendanceRecords = source.getIsPresent();
        List<String> attendanceRecordsString = new ArrayList<>();
        for (int i = 0; i < attendanceRecords.length; i++) {
            if (attendanceRecords[i]) {
                attendanceRecordsString.add(String.valueOf(i + 1));
            }
        }
        weekNumbers.addAll(attendanceRecordsString.stream().map(JsonAdaptedWeekNumber::new)
                .collect(Collectors.toList()));
        participationScore = source.getParticipationScoreAsString();
    }

    /**
     * Converts this Jackson-friendly adapted attendance record into the model's {@code Attendance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendance record
     * or participation score.
     */
    public Attendance toModelType() throws IllegalValueException {
        final Attendance modelAttendance = new Attendance();
        for (JsonAdaptedWeekNumber weekNumber : weekNumbers) {
            modelAttendance.addAttendance(weekNumber.toModelType());
        }

        if (participationScore == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "Participation Score"));
        }
        if (!Attendance.isValidParticipation(participationScore)) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        modelAttendance.setParticipation(participationScore);

        return modelAttendance;
    }

}
