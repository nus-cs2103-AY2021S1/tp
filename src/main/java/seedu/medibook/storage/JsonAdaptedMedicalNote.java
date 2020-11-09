package seedu.medibook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.logic.parser.ParserUtil;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;
import seedu.medibook.model.medicalnote.Content;
import seedu.medibook.model.medicalnote.MedicalNote;

/**
 * Jackson-friendly version of {@link MedicalNote}.
 */
class JsonAdaptedMedicalNote {

    public static final String ERROR_MESSAGE_NULL_FIELD = "MedicalNote's fields cannot be null!";

    public final String date;
    @JsonProperty("doctor name")
    public final String doctorName;
    @JsonProperty("doctor mcr")
    public final String doctorMcr;
    public final String content;

    /**
     * Constructs a {@code JsonAdaptedMedicalNote} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedMedicalNote(@JsonProperty("date") String date, @JsonProperty("doctor name") String doctorName,
                                  @JsonProperty("doctor mcr") String doctorMcr,
                                  @JsonProperty("content") String content) {
        this.date = date;
        this.doctorName = doctorName;
        this.doctorMcr = doctorMcr;
        this.content = content;
    }

    /**
     * Converts a given {@code MedicalNote} into this class for Jackson use.
     */
    public JsonAdaptedMedicalNote(MedicalNote medicalNote) {
        this.date = medicalNote.getInputDateString();
        this.doctorName = medicalNote.getDoctorNameString();
        this.doctorMcr = medicalNote.getDoctorMcrString();
        this.content = medicalNote.getContentString();
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public MedicalNote toModelType() throws IllegalValueException {
        try {
            final Date modelDate = ParserUtil.parseDate(date);
            final Name modelName = ParserUtil.parseName(doctorName);
            final Mcr modelMcr = ParserUtil.parseMcr(doctorMcr);
            final Doctor modelDoctor = new Doctor(modelName, modelMcr);
            final Content modelContent = ParserUtil.parseContent(content);
            return new MedicalNote(modelDate, modelDoctor, modelContent);
        } catch (ParseException | IllegalArgumentException e) {
            throw new IllegalValueException(e.getMessage());
        } catch (NullPointerException npe) {
            throw new IllegalValueException(ERROR_MESSAGE_NULL_FIELD);
        }
    }

}
