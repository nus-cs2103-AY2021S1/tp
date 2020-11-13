package seedu.address.model.deliverable.deliverable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeliverableBuilder;

public class TitleDescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleDescriptionContainsKeywordsPredicate firstPredicate =
                new TitleDescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        TitleDescriptionContainsKeywordsPredicate secondPredicate =
                new TitleDescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleDescriptionContainsKeywordsPredicate firstPredicateCopy =
                new TitleDescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void testDescriptionContainsKeywords_returnsTrue() {
        // One keyword - One match (title)
        TitleDescriptionContainsKeywordsPredicate pred =
                new TitleDescriptionContainsKeywordsPredicate(Collections.singletonList("homepage"));
        assertTrue(pred.test(new DeliverableBuilder().withTitle("homepage features").build()));

        // Multiple keywords - All match (title)
        pred = new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("homepage", "application"));
        assertTrue(pred.test(new DeliverableBuilder().withTitle("homepage application").build()));

        // Multiple keywords - One match (title)
        pred = new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("homepage", "application"));
        assertTrue(pred.test(new DeliverableBuilder().withTitle("application").build()));

        // One keyword - One match (description)
        pred = new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("application"));
        assertTrue(pred.test(new DeliverableBuilder().withDescription("application").build()));

        // Multiple keywords - One match (description)
        pred = new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("homepage", "application"));
        assertTrue(pred.test(new DeliverableBuilder().withDescription("application").build()));

        // Multiple keywords - All match (description)
        pred = new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("homepage", "application"));
        assertTrue(pred.test(new DeliverableBuilder().withDescription("application homepage").build()));

        // Mixed-case keywords
        pred = new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("HomePage"));
        assertTrue(pred.test(new DeliverableBuilder().withTitle("homepage").build()));

        // With punctuation
        pred = new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("HomePage"));
        assertTrue(pred.test(new DeliverableBuilder().withTitle("This is a HomePage.").build()));
    }

    @Test
    public void test_titleDescriptionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleDescriptionContainsKeywordsPredicate pred =
                new TitleDescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(pred.test(new DeliverableBuilder().build()));

        // Non-matching keyword
        pred = new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("javafx"));
        assertFalse(pred.test(new DeliverableBuilder().build()));

        // Keywords match milestone and contacts, but not name or description
        pred = new TitleDescriptionContainsKeywordsPredicate(Arrays.asList("v1.3", "James"));
        assertFalse(pred.test(new DeliverableBuilder().withMilestone("1.3").withContacts("James").build()));
    }
}
