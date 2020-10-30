package seedu.resireg.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.semester.academicyear.AcademicYear;
import seedu.resireg.model.semester.semesternumber.SemesterNumber;

/**
 * Jackson-friendly version of {@link Semester}.
 */
public class JsonAdaptedSemester {

    private final int academicYear;
    private final int semesterNumber;

    /**
     * Constructs a {@code JsonAdaptedSemester} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedSemester(@JsonProperty("academicYear") int academicYear,
                               @JsonProperty("semesterNumber") int semesterNumber) {

        this.academicYear = academicYear;
        this.semesterNumber = semesterNumber;
    }

    /**
     * Converts a given {@code Semester} into this class for Jackson use.
     */
    public JsonAdaptedSemester(Semester source) {
        academicYear = source.getAcademicYear();
        semesterNumber = source.getSemesterNumber();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Semester} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted semester.
     */
    public Semester toModelType() throws IllegalValueException {
        if (!AcademicYear.isValidAcademicYear(academicYear)) {
            throw new IllegalValueException(AcademicYear.MESSAGE_CONSTRAINTS);
        }

        if (!SemesterNumber.isValidSemesterNumber(semesterNumber)) {
            throw new IllegalValueException(SemesterNumber.MESSAGE_CONSTRAINTS);
        }

        return new Semester(academicYear, semesterNumber);
    }
}
