package seedu.resireg.model.semester;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.semester.academicyear.AcademicYear;

class AcademicYearTest {
    @Test
    public void constructor_invalidAcademicYear_throwsIllegalArgumentException() {
        int invalidAcademicYear = 0;
        assertThrows(IllegalArgumentException.class, () -> new AcademicYear(invalidAcademicYear));
    }


    @Test
    void isValidAcademicYear() {
        // invalid academic years
        assertFalse(AcademicYear.isValidAcademicYear(0)); // zero
        assertFalse(AcademicYear.isValidAcademicYear(-1)); // negative numbers
        assertFalse(AcademicYear.isValidAcademicYear(AcademicYear.YEAR_OF_ESTABLISHMENT - 1)); // < 1980
        assertFalse(AcademicYear.isValidAcademicYear(Integer.MIN_VALUE)); // boundary

        // valid academic years
        assertTrue(AcademicYear.isValidAcademicYear(AcademicYear.YEAR_OF_ESTABLISHMENT));
        assertTrue(AcademicYear.isValidAcademicYear(2020)); // current AY at time of writing test
    }
}
