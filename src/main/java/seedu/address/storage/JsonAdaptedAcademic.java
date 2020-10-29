package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.academic.Academic;
import seedu.address.model.student.academic.Attendance;

public class JsonAdaptedAcademic {

    public static final String MISSING_ADMIN_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final List<JsonAdaptedAttendance> attendanceList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAcademic} with academic details.
     * @param attendanceList
     */
    @JsonCreator
    public JsonAdaptedAcademic(@JsonProperty("attendanceList") List<JsonAdaptedAttendance> attendanceList) {
        if (attendanceList != null) {
            this.attendanceList.addAll(attendanceList);
        }
    }

    /**
     * Converts a given {@code Academic} into this class for Jackson use.
     */
    public JsonAdaptedAcademic(Academic source) {
        attendanceList.addAll(source.getAttendance().stream()
                .map(JsonAdaptedAttendance::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted admin object into the model's {@code Admin} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted admin.
     */
    public Academic toModelType() throws IllegalValueException {

        final List<Attendance> attendances = new ArrayList<>();
        for (JsonAdaptedAttendance attendance : attendanceList) {
            attendances.add(attendance.toModelType());
        }

        final List<Attendance> modelAttendance = new ArrayList<>(attendances);
        return new Academic(modelAttendance);
    }
}
