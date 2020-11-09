package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.property.PropertyBuilder;

public class PropertyNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PropertyNameContainsKeywordsPredicate firstPredicate =
                new PropertyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        PropertyNameContainsKeywordsPredicate secondPredicate =
                new PropertyNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PropertyNameContainsKeywordsPredicate firstPredicateCopy =
                new PropertyNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PropertyNameContainsKeywordsPredicate predicate =
                new PropertyNameContainsKeywordsPredicate(Collections.singletonList("Anchorvale"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyName("Anchorvale Bedok").build()));

        // Multiple keywords
        predicate = new PropertyNameContainsKeywordsPredicate(Arrays.asList("Anchorvale", "Bedok"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyName("Anchorvale Bedok").build()));

        // Only one matching keyword
        predicate = new PropertyNameContainsKeywordsPredicate(Arrays.asList("Bedok", "Cove"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyName("Anchorvale Bedok").build()));

        // Mixed-case keywords
        predicate = new PropertyNameContainsKeywordsPredicate(Arrays.asList("aNchOrVAle", "BedOK"));
        assertTrue(predicate.test(new PropertyBuilder().withPropertyName("Anchorvale Bedok").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PropertyNameContainsKeywordsPredicate predicate =
                new PropertyNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PropertyBuilder().withPropertyName("Anchorvale").build()));

        // Non-matching keyword
        predicate = new PropertyNameContainsKeywordsPredicate(Arrays.asList("Cove"));
        assertFalse(predicate.test(new PropertyBuilder().withPropertyName("Anchorvale Bedok").build()));

        // Keywords match property type and name, but does not match name
        predicate = new PropertyNameContainsKeywordsPredicate(
                Arrays.asList("HDB", "House", "Main", "Street"));
        assertFalse(predicate.test(new PropertyBuilder().withPropertyName("Anchorvale")
                .withPropertyType("HDB")
                .withAddress("My House")
                .build()));
    }
}
