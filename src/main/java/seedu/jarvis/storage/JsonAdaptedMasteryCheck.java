package seedu.jarvis.storage;

import static seedu.jarvis.storage.JsonAdaptedConsultation.NAME_OF_STUDENT_FIELD;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.jarvis.commons.exceptions.IllegalValueException;
import seedu.jarvis.model.masterycheck.MasteryCheck;



public class JsonAdaptedMasteryCheck {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Mastery Check's %s field is missing!";
    public static final String INVALID_DATE_TIME_FORMAT = "Date time format is wrong! Please follow format: "
            + "YYYY-MM-DDTHH:MM";
    public static final String INVALID_HASPASSED_FORMAT = "Pass/Fail status of the Mastery Check is corrupted!";

    public static final String HASPASSED_FIELD = "passed";

    private final String studentName;
    private final String dateAndTime;
    private final String passed;

    /**
     * Constructs a Json-friendly Mastery Check object.
     * @param studentName mastery check subject
     * @param dateAndTime mastery check date and time
     * @param passed mastery check passed
     */
    public JsonAdaptedMasteryCheck(@JsonProperty("studentName") String studentName, @JsonProperty("dateAndTime")
            String dateAndTime, @JsonProperty("passed") String passed) {
        this.studentName = studentName;
        this.dateAndTime = dateAndTime;
        this.passed = passed;
    }


    /**
     * Converts a given {@code MasteryCheck} into this class for Jackson use.
     */
    public JsonAdaptedMasteryCheck(MasteryCheck source) {
        studentName = source.getStudentName();
        dateAndTime = source.getDateAndTime().toString();
        passed = source.hasPassed() ? "T" : "F";
    }

    /**
     * Converts this Jackson-friendly adapted Mastery Check object into the model's {@code MasteryCheck} object.
     * @return MasteryCheck
     * @throws IllegalValueException if there were any data constraints violated in the adapted Mastery Check.
     */
    public MasteryCheck toModelType() throws IllegalValueException {
        if (studentName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, NAME_OF_STUDENT_FIELD));
        }

        if (dateAndTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }

        if (passed == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, HASPASSED_FIELD));
        }

        try {
            if (passed.equals("T")) {
                return MasteryCheck.createFullMarkMC(studentName, LocalDateTime.parse(dateAndTime));
            } else if (passed.equals("F")) {
                return MasteryCheck.createZeroMarkMC(studentName, LocalDateTime.parse(dateAndTime));
                // TODO: this needs to work! otherwise parsing / toString is at fault
            } else {
                throw new IllegalValueException(INVALID_HASPASSED_FORMAT);
            }
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(INVALID_DATE_TIME_FORMAT);
        }
    }
}
