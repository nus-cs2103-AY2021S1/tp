package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AlphanumericTest {
    private static final String ALPHANUMERIC = "ABC123";
    private static final String ALPHA = "ABC";
    private static final String NUMERIC = "123";
    private static final String NOT_ALPHANUMERIC = "ASdsa14@#$%^";
    private static final String EMPTY = "";
    private static final String BLANK = " ";
    private static final boolean CAN_BLANK = true;
    private static final boolean CANNOT_BLANK = false;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AlphanumericStub(null, CAN_BLANK));
        assertThrows(NullPointerException.class, () -> new AlphanumericStub(null, CANNOT_BLANK));
    }

    @Test
    public void constructor_blankOrEmptyButCannotBlank_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new AlphanumericStub(EMPTY, CANNOT_BLANK));
        assertThrows(IllegalArgumentException.class, () -> new AlphanumericStub(BLANK, CANNOT_BLANK));
    }

    @Test
    public void constructor_notAlphanum_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new AlphanumericStub(NOT_ALPHANUMERIC, CANNOT_BLANK));
        assertThrows(IllegalArgumentException.class, () -> new AlphanumericStub(NOT_ALPHANUMERIC, CAN_BLANK));
    }

    @Test
    public void isValidAlphanum_validAlphanum_true() {
        assertTrue(AlphanumericStub.isValidAlphanum(ALPHANUMERIC, CAN_BLANK));
        assertTrue(AlphanumericStub.isValidAlphanum(ALPHANUMERIC, CANNOT_BLANK));
        assertTrue(AlphanumericStub.isValidAlphanum(ALPHA, CAN_BLANK));
        assertTrue(AlphanumericStub.isValidAlphanum(ALPHA, CANNOT_BLANK));
        assertTrue(AlphanumericStub.isValidAlphanum(NUMERIC, CAN_BLANK));
        assertTrue(AlphanumericStub.isValidAlphanum(NUMERIC, CANNOT_BLANK));
        assertTrue(AlphanumericStub.isValidAlphanum(BLANK, CAN_BLANK));
        assertTrue(AlphanumericStub.isValidAlphanum(EMPTY, CAN_BLANK));
    }

    @Test
    public void isValidAlphanum_invalidAlphanum_false() {
        assertFalse(AlphanumericStub.isValidAlphanum(NOT_ALPHANUMERIC, CAN_BLANK));
        assertFalse(AlphanumericStub.isValidAlphanum(NOT_ALPHANUMERIC, CANNOT_BLANK));
        assertFalse(AlphanumericStub.isValidAlphanum(BLANK, CANNOT_BLANK));
        assertFalse(AlphanumericStub.isValidAlphanum(EMPTY, CANNOT_BLANK));
    }

    @Test
    public void equals() {
        AlphanumericStub alphanumericStub = new AlphanumericStub(ALPHANUMERIC, CAN_BLANK);
        AlphanumericStub alternateStub = new AlphanumericStub("Test", CAN_BLANK);

        // same values -> returns true
        assertTrue(alphanumericStub.equals(new AlphanumericStub(ALPHANUMERIC, CAN_BLANK)));

        // same object -> returns true
        assertTrue(alphanumericStub.equals(alphanumericStub));

        // null -> returns false
        assertFalse(alphanumericStub.equals(null));

        // different type -> returns false
        assertFalse(alphanumericStub.equals(5));

        // different alphanum -> returns false
        assertFalse(alphanumericStub.equals(alternateStub));
    }

    private class AlphanumericStub extends Alphanumeric {
        /**
         * Constructs a {@code AlphanumericStub}.
         *
         * @param alphaNum   A valid alphaNum.
         * @param canBeBlank boolean denoting if it can be blank
         */
        protected AlphanumericStub(String alphaNum, boolean canBeBlank) {
            super(alphaNum, canBeBlank);
        }

        @Override
        public boolean equals(Object other) {
            return other == this // short circuit if same object
                    || (other instanceof AlphanumericStub // instanceof handles nulls
                    && getAlphaNum().equals(((AlphanumericStub) other).getAlphaNum())); // state check
        }
    }
}
