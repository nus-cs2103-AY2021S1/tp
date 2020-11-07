package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExerciseBuilder;

public class ExerciseNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ExerciseNameContainsKeywordsPredicate firstPredicate = new ExerciseNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        ExerciseNameContainsKeywordsPredicate secondPredicate = new ExerciseNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExerciseNameContainsKeywordsPredicate firstPredicateCopy = new ExerciseNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different exercise -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_exerciseNameContainsKeywords_returnsTrue() {
        // One keyword
        ExerciseNameContainsKeywordsPredicate predicate = new ExerciseNameContainsKeywordsPredicate(
                Collections.singletonList("Squats"));
        assertTrue(predicate.test(new ExerciseBuilder().withName("Squats Bench").build()));

        // Multiple keywords
        predicate = new ExerciseNameContainsKeywordsPredicate(Arrays.asList("Squats", "Bench"));
        assertTrue(predicate.test(new ExerciseBuilder().withName("Squats Bench").build()));

        // Mixed-case keywords
        predicate = new ExerciseNameContainsKeywordsPredicate(Arrays.asList("sQuaTs", "BenCh"));
        assertTrue(predicate.test(new ExerciseBuilder().withName("Squats Bench").build()));
    }

    @Test
    public void test_exerciseNameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        ExerciseNameContainsKeywordsPredicate predicate = new ExerciseNameContainsKeywordsPredicate(Arrays
                .asList("Squats"));
        assertFalse(predicate.test(new ExerciseBuilder().withName("Bench Shoulders").build()));
    }
}
