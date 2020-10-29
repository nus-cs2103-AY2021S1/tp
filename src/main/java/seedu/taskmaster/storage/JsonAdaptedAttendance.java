package seedu.taskmaster.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.taskmaster.commons.exceptions.IllegalValueException;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.record.ClassParticipation;
import seedu.taskmaster.model.record.StudentRecord;
import seedu.taskmaster.model.record.StudentRecordListManager;
import seedu.taskmaster.model.student.Name;
import seedu.taskmaster.model.student.NusnetId;

/**
 * Jackson-friendly version of {@link StudentRecordListManager}.
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
                                 @JsonProperty("telegram") String attendanceType) {
        this.nusnetId = nusnetId;
        this.attendanceType = attendanceType;
    }

    /**
     * Converts a given {@code StudentRecord} into this class for Jackson use.
     */
    public JsonAdaptedAttendance(StudentRecord source) {
        nusnetId = source.getNusnetId().value;
        attendanceType = source.getAttendanceType().name();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code StudentRecordListManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public StudentRecord toModelType() throws IllegalValueException {
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

        final Name placeholderName = Name.getStudentNotFoundName();

        final NusnetId modelNusnetId = new NusnetId(nusnetId);

        final AttendanceType modelAttendanceType = AttendanceType.valueOf(attendanceType);

        // TODO: implement
        final ClassParticipation classParticipation = new ClassParticipation();

        return new StudentRecord(placeholderName, modelNusnetId, modelAttendanceType, classParticipation);
    }

}

