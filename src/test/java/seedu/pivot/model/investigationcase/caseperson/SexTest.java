package seedu.pivot.model.investigationcase.caseperson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SexTest {
    private static final String MALE_CAPS = "M";
    private static final String MALE_SMALL = "m";
    private static final String FEMALE_CAPS = "F";
    private static final String FEMALE_SMALL = "f";
    private static final String INVALID_SEX = "SOMETHING";
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    @Test
    public void createSex_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Sex.createSex(null));
    }

    @Test
    public void createSex_invalidSex_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Sex.createSex(INVALID_SEX));
        assertThrows(IllegalArgumentException.class, () -> Sex.createSex(EMPTY));
        assertThrows(IllegalArgumentException.class, () -> Sex.createSex(BLANK));
    }

    @Test
    public void isValidSex_validSex_true() {
        assertTrue(Sex.isValidSex(MALE_CAPS));
        assertTrue(Sex.isValidSex(MALE_SMALL));
        assertTrue(Sex.isValidSex(FEMALE_CAPS));
        assertTrue(Sex.isValidSex(FEMALE_SMALL));
    }

    @Test
    public void isValidSex_invalidSex_false() {
        assertFalse(Sex.isValidSex(INVALID_SEX));
        assertFalse(Sex.isValidSex(EMPTY));
        assertFalse(Sex.isValidSex(BLANK));
    }
}
