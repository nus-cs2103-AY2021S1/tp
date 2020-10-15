package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.attendance.AttendanceType;
import seedu.address.model.student.NusnetId;

/**
 * Jackson-friendly version of {@link AttendanceList}.
 */
class JsonAdaptedAttendance {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String nusnetId;
    private final String attendanceType;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedAttendance(@JsonProperty("nusnetId") String nusnetId,
                                 @JsonProperty("phone") String attendanceType) {
        this.nusnetId = nusnetId;
        this.attendanceType = attendanceType;
    }

    /**
     * Converts a given {@code Attendance} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(Attendance source) {
        nusnetId = source.getNusnetId().value;
        attendanceType = source.getAttendanceType().name();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code AttendanceList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Attendance toModelType() throws IllegalValueException {
        if (nusnetId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    NusnetId.class.getSimpleName()));
        }

        if (!NusnetId.isValidNusnetId(nusnetId)) {
            throw new IllegalValueException(NusnetId.MESSAGE_CONSTRAINTS);
        }

        if (attendanceType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AttendanceType.class.getSimpleName()));
        }

        if (!AttendanceType.isValidAttendanceType(attendanceType)) {
            throw new IllegalValueException(AttendanceType.MESSAGE_CONSTRAINTS);
        }

        final NusnetId modelNusnetId = new NusnetId(nusnetId);

        final AttendanceType modelAttendanceType = AttendanceType.valueOf(attendanceType);

        return new Attendance(modelNusnetId, modelAttendanceType);
    }

}

