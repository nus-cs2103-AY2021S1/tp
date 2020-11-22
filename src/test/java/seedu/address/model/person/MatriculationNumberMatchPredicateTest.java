package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_NUMBER_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;


public class MatriculationNumberMatchPredicateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MatriculationNumberMatchPredicate(null));
    }

    @Test
    public void equals() {
        MatriculationNumber firstMatriculationNumber = new MatriculationNumber(VALID_MATRICULATION_NUMBER_AMY);
        MatriculationNumber secondMatriculationNumber = new MatriculationNumber(VALID_MATRICULATION_NUMBER_BOB);

        MatriculationNumberMatchPredicate firstPredicate =
                new MatriculationNumberMatchPredicate(firstMatriculationNumber);
        MatriculationNumberMatchPredicate secondPredicate =
                new MatriculationNumberMatchPredicate(secondMatriculationNumber);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MatriculationNumberMatchPredicate firstPredicateCopy =
                new MatriculationNumberMatchPredicate(firstMatriculationNumber);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different matriculation number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test() {
        // same matriculation number -> returns true
        MatriculationNumberMatchPredicate predicate =
                new MatriculationNumberMatchPredicate(new MatriculationNumber(VALID_MATRICULATION_NUMBER_AMY));
        assertTrue(predicate
                .test(new PersonBuilder().withMatriculationNumber(VALID_MATRICULATION_NUMBER_AMY).build()));

        // different matriculation number -> returns false
        predicate = new MatriculationNumberMatchPredicate(new MatriculationNumber(VALID_MATRICULATION_NUMBER_BOB));
        assertFalse(predicate
                .test(new PersonBuilder().withMatriculationNumber(VALID_MATRICULATION_NUMBER_AMY).build()));
    }
}
