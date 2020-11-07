package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CriterionTest {

    @Test
    public void isValidCriterion() {
        // null Criterion
        assertThrows(NullPointerException.class, () -> Criterion.isValidCriterion(null));

        // invalid Criterion
        assertFalse(Criterion.isValidCriterion("")); // empty string
        assertFalse(Criterion.isValidCriterion(" ")); // spaces only
        assertFalse(Criterion.isValidCriterion("$")); // only non-alphanumeric characters
        assertFalse(Criterion.isValidCriterion("#12halo")); // contains non-alphanumeric characters
        assertFalse(Criterion.isValidCriterion("1234")); // contains all number
        assertFalse(Criterion.isValidCriterion("N A M E")); // contains whitespaces in between
        assertFalse(Criterion.isValidCriterion("NAM E")); // contains one whitespace
        assertFalse(Criterion.isValidCriterion(" NAME")); // contains whitespace in the front
        assertFalse(Criterion.isValidCriterion("NAME ")); // contains trailing whitespace

        // valid Criterion, all uppercase
        assertTrue(Criterion.isValidCriterion("NAME"));
        assertTrue(Criterion.isValidCriterion("DATE"));
        assertTrue(Criterion.isValidCriterion("PRIORITY"));
    }
}
