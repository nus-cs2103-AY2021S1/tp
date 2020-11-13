package seedu.jarvis.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.jarvis.commons.exceptions.IllegalValueException;
import seedu.jarvis.model.consultation.Consultation;


public class JsonAdaptedConsultation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Consultation's %s field is missing!";
    public static final String INVALID_DATE_TIME_FORMAT = "Date time format is wrong! Please follow format: "
            + "YYYY-MM-DDTHH:MM";
    public static final String NAME_OF_STUDENT_FIELD = "name of student";

    private final String studentName;
    private final String dateAndTime;

    /**
     * Constructs a Json-friendly Consultation object.
     * @param studentName consultation subject
     * @param dateAndTime consultation date and time
     */
    public JsonAdaptedConsultation(@JsonProperty("studentName") String studentName, @JsonProperty("dateAndTime")
            String dateAndTime) {
        this.studentName = studentName;
        this.dateAndTime = dateAndTime;
    }

    /**
     * Converts a given {@code Consultation} into this class for Jackson use.
     */
    public JsonAdaptedConsultation(Consultation source) {
        studentName = source.getStudentName();
        dateAndTime = source.getDateAndTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted Consultation object into the model's {@code Consultation} object.
     * @return Consultation
     * @throws IllegalValueException if there were any data constraints violated in the adapted Consultation.
     */
    public Consultation toModelType() throws IllegalValueException {
        if (studentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, NAME_OF_STUDENT_FIELD));
        }

        if (dateAndTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        try {
            return new Consultation(studentName, LocalDateTime.parse(dateAndTime));
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(INVALID_DATE_TIME_FORMAT);
        }
    }
}
