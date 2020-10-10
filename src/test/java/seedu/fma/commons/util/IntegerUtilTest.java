package seedu.fma.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.fma.commons.exceptions.IllegalValueException;

public class IntegerUtilTest {
    @Test
    public void isPositiveInteger() {
        // Test negative integer
        assertFalse(IntegerUtil.isPositiveInteger(-1));

        // Test positive integer
        assertTrue(IntegerUtil.isPositiveInteger(20));
    }

    //---------------- Tests for requiredPositiveInteger --------------------------------------

    private void requiredPositiveInteger_throwsIllegalArgumentException(Integer number) {
        assertThrows(IllegalValueException.class, () -> IntegerUtil.requirePositiveInteger(-10));
    }
}
