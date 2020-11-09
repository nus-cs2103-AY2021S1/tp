package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidUrlLink_throwsIllegalArgumentException() {
        String invalidSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // invalid salary
        assertFalse(Salary.isValidSalary("")); // empty string
        assertFalse(Salary.isValidSalary(" ")); // spaces only
        assertFalse(Salary.isValidSalary("-88")); // negative numbers
        assertFalse(Salary.isValidSalary("google")); // alphabets
        assertFalse(Salary.isValidSalary("9011p041")); // alphabets within digits
        assertFalse(Salary.isValidSalary("8,000")); // numbers with comma
        assertFalse(Salary.isValidSalary("Infinity")); // numbers more than 1 billion
        assertFalse(Salary.isValidSalary("1000000001")); // numbers more than 1 billion
        assertFalse(Salary.isValidSalary("2000.5")); // numbers with decimal

        // valid salary
        assertTrue(Salary.isValidSalary("0")); // 0
        assertTrue(Salary.isValidSalary("8000")); // positive number less than 1 billion
        assertTrue(Salary.isValidSalary("    8000     ")); // valid number with whitespace
    }
}

