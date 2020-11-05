package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class ParticipationBelowSpecifiedScorePredicateTest {

    @Test
    public void equals() {
        int firstPredicateScore = 1;
        int secondPredicateScore = 50;

        ParticipationBelowSpecifiedScorePredicate firstPredicate =
                new ParticipationBelowSpecifiedScorePredicate(firstPredicateScore);
        ParticipationBelowSpecifiedScorePredicate secondPredicate =
                new ParticipationBelowSpecifiedScorePredicate(secondPredicateScore);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ParticipationBelowSpecifiedScorePredicate firstPredicateCopy =
                new ParticipationBelowSpecifiedScorePredicate(firstPredicateScore);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentParticipationBelow_returnsTrue() {
        // One keyword
        ParticipationBelowSpecifiedScorePredicate predicate = new ParticipationBelowSpecifiedScorePredicate(50);
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob")
                .withParticipation("49").build()));
        predicate = new ParticipationBelowSpecifiedScorePredicate(50);
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").withParticipation("0").build()));
    }

    @Test
    public void test_studentParticipationNotBelow_returnsFalse() {
        // Zero
        ParticipationBelowSpecifiedScorePredicate predicate = new ParticipationBelowSpecifiedScorePredicate(50);
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").withParticipation("51").build()));

        // Not below
        predicate = new ParticipationBelowSpecifiedScorePredicate(50);
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").withParticipation("100").build()));
    }
}
