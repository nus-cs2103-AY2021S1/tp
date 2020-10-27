package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.property.PropertyBuilder;

public class PropertyTypeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PropertyTypeContainsKeywordsPredicate firstPredicate =
                new PropertyTypeContainsKeywordsPredicate(firstPredicateKeywordList);
        PropertyTypeContainsKeywordsPredicate secondPredicate =
                new PropertyTypeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PropertyTypeContainsKeywordsPredicate firstPredicateCopy =
                new PropertyTypeContainsKeywordsPredicate(firstPredicateKeywordList);
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
        PropertyTypeContainsKeywordsPredicate predicate =
                new PropertyTypeContainsKeywordsPredicate(Collections.singletonList("HDB"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyType("HDB Condo").build()));

        // Multiple keywords
        predicate = new PropertyTypeContainsKeywordsPredicate(Arrays.asList("HDB", "Condo"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyType("HDB Condo").build()));

        // Only one matching keyword
        predicate = new PropertyTypeContainsKeywordsPredicate(Arrays.asList("Condo", "Landed"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyType("HDB Condo").build()));

        // Mixed-case keywords
        predicate = new PropertyTypeContainsKeywordsPredicate(Arrays.asList("hdB", "cOnDO"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyType("HDB Condo").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PropertyTypeContainsKeywordsPredicate predicate =
                new PropertyTypeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PropertyBuilder().withPropertyType("HDB").build()));

        // Non-matching keyword
        predicate = new PropertyTypeContainsKeywordsPredicate(Arrays.asList("Landed"));
        assertFalse(predicate.test(new PropertyBuilder().withPropertyType("HDB Condo").build()));

        // Keywords match name and address, but does not match type
        predicate = new PropertyTypeContainsKeywordsPredicate(
                Arrays.asList("House", "Main", "Street"));
        assertFalse(predicate.test(new PropertyBuilder().withPropertyType("HDB")
                .withAddress("Main Street")
                .withPropertyName("My House")
                .build()));
    }
}
