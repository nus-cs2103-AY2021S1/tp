package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.student.NusnetId;


/**
 * An Immutable AttendanceList that is serializable to JSON format.
 */
@JsonRootName(value = "taskmaster")
class JsonSerializableAttendanceList {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Attendance list contains duplicate NusnetId(s).";

    private final List<JsonAdaptedAttendance> attendances = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskmaster} with the given students.
     */
    @JsonCreator
    public JsonSerializableAttendanceList(@JsonProperty("attendances") List<JsonAdaptedAttendance> attendances) {
        this.attendances.addAll(attendances);
    }

    /**
     * Converts a given {@code ReadOnlyTaskmaster} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskmaster}.
     */
    public JsonSerializableAttendanceList(AttendanceList source) {
        attendances.addAll(source.asUnmodifiableObservableList().stream()
                .map(JsonAdaptedAttendance::new).collect(Collectors.toList()));
    }

    /**
     * Converts this student list into the model's {@code Taskmaster} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AttendanceList toModelType(AttendanceList existingList) throws IllegalValueException {
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
        existingList.setAttendances(newAttendances);

        return existingList;
    }

}
