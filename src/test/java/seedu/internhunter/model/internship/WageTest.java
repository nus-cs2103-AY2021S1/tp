package seedu.internhunter.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.model.internship.Wage.DEFAULT_WAGE;
import static seedu.internhunter.model.internship.Wage.WAGE_SYMBOL;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_WAGE_DECIMAL;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_WAGE_NEGATIVE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.INVALID_WAGE_ZERO;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_2000;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_3000;

import org.junit.jupiter.api.Test;

public class WageTest {

    private static final Wage VALID_WAGE_ONE = new Wage(VALID_WAGE_2000);
    private static final Wage VALID_WAGE_TWO = new Wage(VALID_WAGE_3000);
    private static final Wage VALID_WAGE_DEFAULT = new Wage(DEFAULT_WAGE);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Wage(null));
    }

    @Test
    public void constructor_invalidWage_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, Wage.MESSAGE_CONSTRAINTS, () -> new Wage(INVALID_WAGE_ZERO));
        assertThrows(IllegalArgumentException.class, Wage.MESSAGE_CONSTRAINTS, () -> new Wage(INVALID_WAGE_NEGATIVE));
        assertThrows(IllegalArgumentException.class, Wage.MESSAGE_CONSTRAINTS, () -> new Wage(INVALID_WAGE_DECIMAL));
    }

    @Test
    public void isValidWage_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Wage.isValidWage(null));
    }

    @Test
    public void isValidWage_validInput_success() {
        assertTrue(Wage.isValidWage("1")); // Min value of 1
        assertTrue(Wage.isValidWage("12345")); // Normal wage
        assertTrue(Wage.isValidWage("3000")); // Normal wage
        assertTrue(Wage.isValidWage("123456789123456789123456789123456789")); // Very large number
    }

    @Test
    public void isValidWage_invalidDigits_success() {
        assertFalse(Wage.isValidWage("0")); // Zero
        assertFalse(Wage.isValidWage("0123")); // Zero
        assertFalse(Wage.isValidWage("+123")); // Starting with positive
        assertFalse(Wage.isValidWage("-4")); // Negative number
        assertFalse(Wage.isValidWage("400.00")); // Decimals
        assertFalse(Wage.isValidWage("0.400")); // Decimals
    }

    @Test
    public void isValidWage_invalidInput_success() {
        assertFalse(Wage.isValidWage("")); // empty string
        assertFalse(Wage.isValidWage("    ")); // spaces only
        assertFalse(Wage.isValidWage("   213")); // leading spaces
        assertFalse(Wage.isValidWage("213  ")); // trailing spaces
        assertFalse(Wage.isValidWage("1221abc2131")); // alphabets within digits
        assertFalse(Wage.isValidWage("90 41")); // spaces within digits
    }

    @Test
    public void isValidOutputWage_validInput_success() {
        assertTrue(Wage.isValidOutputWage("1")); // Min value of 1
        assertTrue(Wage.isValidOutputWage("3000")); // Normal wage
        assertTrue(Wage.isValidOutputWage("123456789123456789123456789123456789")); // Very large number

        assertTrue(Wage.isValidOutputWage(DEFAULT_WAGE)); // Dash should be accepted
    }

    @Test
    public void getValue_equalityTest_success() {
        assertEquals(VALID_WAGE_2000, VALID_WAGE_ONE.getValue());
    }

    @Test
    public void toString_validFormat_success() {
        assertEquals(WAGE_SYMBOL + VALID_WAGE_2000, VALID_WAGE_ONE.toString());
        assertEquals(DEFAULT_WAGE, VALID_WAGE_DEFAULT.toString());
    }

    @Test
    public void equals_equalityTest_success() {
        // same object -> return true
        assertTrue(VALID_WAGE_ONE.equals(VALID_WAGE_ONE));

        // same value -> return true
        Wage wageCopy = new Wage(VALID_WAGE_2000);
        assertTrue(VALID_WAGE_ONE.equals(wageCopy));
    }

    @Test
    public void equals_nonEqualityTest_success() {
        assertFalse(VALID_WAGE_ONE.equals(VALID_WAGE_TWO));

        // null -> return false
        assertFalse(VALID_WAGE_ONE.equals(null));

        // different types -> return false
        assertFalse(VALID_WAGE_ONE.equals(0.5f));
    }

    @Test
    public void hashCode_equalityTest_success() {
        assertEquals(VALID_WAGE_ONE.hashCode(), VALID_WAGE_ONE.hashCode());
    }

}
