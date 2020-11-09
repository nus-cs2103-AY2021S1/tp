package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.property.PropertyBuilder;

public class PropertyIdContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PropertyIdContainsKeywordsPredicate firstPredicate =
                new PropertyIdContainsKeywordsPredicate(firstPredicateKeywordList);
        PropertyIdContainsKeywordsPredicate secondPredicate =
                new PropertyIdContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PropertyIdContainsKeywordsPredicate firstPredicateCopy =
                new PropertyIdContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        PropertyIdContainsKeywordsPredicate predicate =
                new PropertyIdContainsKeywordsPredicate(Collections.singletonList("P1"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyId("P1").build()));

        // Only one matching keyword
        predicate = new PropertyIdContainsKeywordsPredicate(Arrays.asList("P1", "P2"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyId("P1").build()));

        // Mixed-case keywords
        predicate = new PropertyIdContainsKeywordsPredicate(Arrays.asList("p1", "p2"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyId("P1").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PropertyIdContainsKeywordsPredicate predicate =
                new PropertyIdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PropertyBuilder().withPropertyId("P1").build()));

        // Non-matching keyword
        predicate = new PropertyIdContainsKeywordsPredicate(Arrays.asList("P3"));
        assertFalse(predicate.test(new PropertyBuilder().withPropertyId("P1").build()));

        // Keywords match property type and name, but does not match id
        predicate = new PropertyIdContainsKeywordsPredicate(
                Arrays.asList("HDB", "House", "P2", "P3"));
        assertFalse(predicate.test(new PropertyBuilder().withPropertyId("P1")
                .withPropertyType("HDB")
                .withPropertyName("My House")
                .build()));
    }
}
