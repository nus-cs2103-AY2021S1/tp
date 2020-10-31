package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.property.PropertyBuilder;

public class SellerIdContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        SellerIdContainsKeywordsPredicate firstPredicate =
                new SellerIdContainsKeywordsPredicate(firstPredicateKeywordList);
        SellerIdContainsKeywordsPredicate secondPredicate =
                new SellerIdContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SellerIdContainsKeywordsPredicate firstPredicateCopy =
                new SellerIdContainsKeywordsPredicate(firstPredicateKeywordList);
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
        SellerIdContainsKeywordsPredicate predicate =
                new SellerIdContainsKeywordsPredicate(Collections.singletonList("S1"));
        assertTrue(predicate.test(new PropertyBuilder().withSellerId("S1").build()));

        // Only one matching keyword
        predicate = new SellerIdContainsKeywordsPredicate(Arrays.asList("S1", "S2"));
        assertTrue(predicate.test(new PropertyBuilder().withSellerId("S1").build()));

        // Mixed-case keywords
        predicate = new SellerIdContainsKeywordsPredicate(Arrays.asList("s1", "s2"));
        assertTrue(predicate.test(new PropertyBuilder().withSellerId("S1").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SellerIdContainsKeywordsPredicate predicate =
                new SellerIdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PropertyBuilder().withSellerId("S1").build()));

        // Non-matching keyword
        predicate = new SellerIdContainsKeywordsPredicate(Arrays.asList("S3"));
        assertFalse(predicate.test(new PropertyBuilder().withSellerId("S1").build()));

        // Keywords match property type and name, but does not match id
        predicate = new SellerIdContainsKeywordsPredicate(
                Arrays.asList("HDB", "House", "S2", "S3"));
        assertFalse(predicate.test(new PropertyBuilder().withSellerId("S1")
                .withPropertyType("HDB")
                .withPropertyName("My House")
                .build()));
    }
}
