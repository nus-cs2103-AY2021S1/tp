package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorialgroup.TutorialContainsKeywordsPredicate;
import seedu.address.testutil.TutorialGroupBuilder;

public class TutorialContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TutorialContainsKeywordsPredicate firstPredicate =
            new TutorialContainsKeywordsPredicate(firstPredicateKeywordList);
        TutorialContainsKeywordsPredicate secondPredicate =
            new TutorialContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorialContainsKeywordsPredicate firstPredicateCopy =
            new TutorialContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tutorialGroupIdContainsKeywords_returnsTrue() {
        // One keyword
        TutorialContainsKeywordsPredicate predicate =
            new TutorialContainsKeywordsPredicate(Collections.singletonList("T04"));
        assertTrue(predicate.test(new TutorialGroupBuilder().withTutorialGroupId("T04").build()));

        // Multiple keywords
        predicate = new TutorialContainsKeywordsPredicate(Arrays.asList("T04", "T05"));
        assertTrue(predicate.test(new TutorialGroupBuilder().withTutorialGroupId("T04").build()));

        // Only one matching keyword
        predicate = new TutorialContainsKeywordsPredicate(Arrays.asList("04", "05"));
        assertTrue(predicate.test(new TutorialGroupBuilder().withTutorialGroupId("T04").build()));

        // Mixed-case keywords
        predicate = new TutorialContainsKeywordsPredicate(Arrays.asList("t04", "t05"));
        assertTrue(predicate.test(new TutorialGroupBuilder().withTutorialGroupId("T04").build()));
    }

    @Test
    public void test_tutorialGroupIdDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TutorialContainsKeywordsPredicate predicate = new TutorialContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TutorialGroupBuilder().withTutorialGroupId("T04").build()));

        // Non-matching keyword
        predicate = new TutorialContainsKeywordsPredicate(Arrays.asList("T10"));
        assertFalse(predicate.test(new TutorialGroupBuilder().withTutorialGroupId("T04").build()));
    }
}
