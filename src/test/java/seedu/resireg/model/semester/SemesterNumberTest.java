package seedu.resireg.model.semester;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.semester.semesternumber.SemesterNumber;

class SemesterNumberTest {
    @Test
    public void constructor_invalidSemesterNumber_throwsIllegalArgumentException() {
        int invalidSemesterNumber = 0;
        assertThrows(IllegalArgumentException.class, () -> new SemesterNumber(invalidSemesterNumber));
    }

    @Test
    void isValidSemesterNumber() {
        // invalid semester numbers
        assertFalse(SemesterNumber.isValidSemesterNumber(0)); // zero
        assertFalse(SemesterNumber.isValidSemesterNumber(-1)); // negative numbers
        assertFalse(SemesterNumber.isValidSemesterNumber(3)); // > 2
        assertFalse(SemesterNumber.isValidSemesterNumber(Integer.MIN_VALUE)); // boundary
        assertFalse(SemesterNumber.isValidSemesterNumber(Integer.MAX_VALUE)); // boundary

        // valid semester numbers
        assertTrue(SemesterNumber.isValidSemesterNumber(1));
        assertTrue(SemesterNumber.isValidSemesterNumber(2));
    }
}
