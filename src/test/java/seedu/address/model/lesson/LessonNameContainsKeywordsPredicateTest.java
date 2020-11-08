package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LessonBuilder;

public class LessonNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        LessonNameContainsKeywordsPredicate firstPredicate = new LessonNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        LessonNameContainsKeywordsPredicate secondPredicate = new LessonNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        LessonNameContainsKeywordsPredicate firstPredicateCopy = new LessonNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different lesson -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_lessonNameContainsKeywords_returnsTrue() {
        // One keyword
        LessonNameContainsKeywordsPredicate predicate = new LessonNameContainsKeywordsPredicate(
                Collections.singletonList("GES1028"));
        assertTrue(predicate.test(new LessonBuilder().withName("GES1028 CS2106").build()));

        // Multiple keywords
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("GES1028", "CS2106"));
        assertTrue(predicate.test(new LessonBuilder().withName("GES1028 CS2106").build()));

        // Only one matching keyword
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("CS2106", "GET1011"));
        assertTrue(predicate.test(new LessonBuilder().withName("GES1028 GET1011").build()));

        // Mixed-case keywords
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("gEs1028", "cs2106"));
        assertTrue(predicate.test(new LessonBuilder().withName("GES1028 CS2106").build()));
    }

    @Test
    public void test_lessonNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LessonNameContainsKeywordsPredicate predicate = new LessonNameContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new LessonBuilder().withName("GES1028").build()));

        // Non-matching keyword
        predicate = new LessonNameContainsKeywordsPredicate(Arrays.asList("GET1011"));
        assertFalse(predicate.test(new LessonBuilder().withName("GES1028 CS2106").build()));
    }
}
