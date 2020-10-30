package seedu.resireg.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalSemesters.AY2020_SEM_1;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.model.semester.academicyear.AcademicYear;
import seedu.resireg.model.semester.semesternumber.SemesterNumber;

class JsonAdaptedSemesterTest {
    private static final int INVALID_YEAR = 0;
    private static final int INVALID_SEMESTER_NUMBER = 0;

    private static final int VALID_ACADEMIC_YEAR = 2020;
    private static final int VALID_SEMESTER_NUMBER = 2;

    @Test
    void toModelType_validSemesterDetails_returnsSemester() throws Exception {
        JsonAdaptedSemester semester = new JsonAdaptedSemester(AY2020_SEM_1);
        assertEquals(AY2020_SEM_1, semester.toModelType());
    }

    @Test
    void toModelType_invalidAcademicYear_throwsIllegalValueException() {
        JsonAdaptedSemester semester = new JsonAdaptedSemester(INVALID_YEAR, VALID_SEMESTER_NUMBER);
        String expectedMessage = AcademicYear.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, semester::toModelType);
    }

    @Test
    void toModelType_invalidSemesterNumber_throwsIllegalValueException() {
        JsonAdaptedSemester semester = new JsonAdaptedSemester(VALID_ACADEMIC_YEAR, INVALID_SEMESTER_NUMBER);
        String expectedMessage = SemesterNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, semester::toModelType);
    }
}
