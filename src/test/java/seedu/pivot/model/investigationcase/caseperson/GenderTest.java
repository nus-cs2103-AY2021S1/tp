package seedu.pivot.model.investigationcase.caseperson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {
    private static final String MALE_CAPS = "M";
    private static final String MALE_SMALL = "m";
    private static final String FEMALE_CAPS = "F";
    private static final String FEMALE_SMALL = "f";
    private static final String INVALID_GENDER = "SOMETHING";
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    @Test
    public void createGender_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Gender.createGender(null));
    }

    @Test
    public void createGender_invalidGender_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Gender.createGender(INVALID_GENDER));
        assertThrows(IllegalArgumentException.class, () -> Gender.createGender(EMPTY));
        assertThrows(IllegalArgumentException.class, () -> Gender.createGender(BLANK));
    }

    @Test
    public void isValidGender_validGender_true() {
        assertTrue(Gender.isValidGender(MALE_CAPS));
        assertTrue(Gender.isValidGender(MALE_SMALL));
        assertTrue(Gender.isValidGender(FEMALE_CAPS));
        assertTrue(Gender.isValidGender(FEMALE_SMALL));
    }

    @Test
    public void isValidGender_invalidGender_false() {
        assertFalse(Gender.isValidGender(INVALID_GENDER));
        assertFalse(Gender.isValidGender(EMPTY));
        assertFalse(Gender.isValidGender(BLANK));
    }
}
