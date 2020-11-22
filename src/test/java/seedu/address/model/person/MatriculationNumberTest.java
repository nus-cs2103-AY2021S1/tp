package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MatriculationNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatriculationNumber(null));
    }

    @Test
    public void constructor_invalidMatriculationNumber_throwsIllegalArgumentException() {
        String invalidMatriculationNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new MatriculationNumber(invalidMatriculationNumber));
    }

    @Test
    public void isValidMatriculationNumber() {
        // null matriculation number
        assertThrows(NullPointerException.class, () -> MatriculationNumber.isValidMatriculationNumber(null));

        // blank matriculation number
        assertFalse(MatriculationNumber.isValidMatriculationNumber("")); // empty string
        assertFalse(MatriculationNumber.isValidMatriculationNumber(" ")); // spaces only

        // incomplete matriculation number
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A0123")); // incomplete
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A0123456")); // incomplete
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A012345B")); // incomplete

        // invalid matriculation number
        assertFalse(MatriculationNumber.isValidMatriculationNumber("a0123456B")); // start with 'a'
        assertFalse(MatriculationNumber.isValidMatriculationNumber("B0123456B")); // start with 'B'
        assertFalse(MatriculationNumber.isValidMatriculationNumber("0123456BA")); // start with a number
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A0123456b")); // end with 'b'
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A01234567")); // end with a number
        // contain alphabetic character in the middle
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A012E456B"));
        // contain special symbol in the middle
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A_______B"));
        assertFalse(MatriculationNumber.isValidMatriculationNumber(" A0123456B")); // leading space
        assertFalse(MatriculationNumber.isValidMatriculationNumber("A0123456B ")); // trailing space

        // valid matriculation number
        assertTrue(MatriculationNumber.isValidMatriculationNumber("A0111111A"));
        assertTrue(MatriculationNumber.isValidMatriculationNumber("A0222222B"));
        assertTrue(MatriculationNumber.isValidMatriculationNumber("A0333333C"));
        assertTrue(MatriculationNumber.isValidMatriculationNumber("A0444444Z"));
    }
}
