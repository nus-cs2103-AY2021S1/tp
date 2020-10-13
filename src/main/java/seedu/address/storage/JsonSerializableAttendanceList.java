package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.student.NusnetId;


/**
 * An Immutable list of Attendances that is serializable to JSON format.
 */
@JsonRootName(value = "attendanceList")
class JsonSerializableAttendanceList {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Attendance list contains duplicate NusnetId(s).";

    private final List<JsonAdaptedAttendance> attendances = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAttendanceList} from the given List.
     */
    @JsonCreator
    public JsonSerializableAttendanceList(@JsonProperty("attendances") List<JsonAdaptedAttendance> attendances) {
        this.attendances.addAll(attendances);
    }

    /**
     * Converts a given {@code ReadOnlyTaskmaster} into this class for Jackson use.
     * This method exists because two different constructors will have the same erasure.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAttendanceList}.
     */
    public static JsonSerializableAttendanceList getSerializableListFromAttendances(List<Attendance> source) {
        return new JsonSerializableAttendanceList(source.stream()
                                                    .map(JsonAdaptedAttendance::new)
                                                    .collect(Collectors.toList()));
    }

    /**
     * Converts this student list into a List of Attendances for use by the model.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public List<Attendance> toModelType() throws IllegalValueException {
        List<Attendance> newAttendances = new ArrayList<>();
        List<NusnetId> nusnetIds = new ArrayList<>();

        for (JsonAdaptedAttendance jsonAdaptedAttendance: attendances) {
            Attendance modelAttendance = jsonAdaptedAttendance.toModelType();

            // check for duplicates
            NusnetId nusnetId = modelAttendance.getNusnetId();
            if (nusnetIds.contains(nusnetId)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            nusnetIds.add(modelAttendance.getNusnetId());

            newAttendances.add(modelAttendance);
        }

        return newAttendances;
    }

}
