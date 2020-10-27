package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.property.PropertyBuilder;

public class PropertyAddressContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PropertyAddressContainsKeywordsPredicate firstPredicate =
                new PropertyAddressContainsKeywordsPredicate(firstPredicateKeywordList);
        PropertyAddressContainsKeywordsPredicate secondPredicate =
                new PropertyAddressContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PropertyAddressContainsKeywordsPredicate firstPredicateCopy =
                new PropertyAddressContainsKeywordsPredicate(firstPredicateKeywordList);
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
        PropertyAddressContainsKeywordsPredicate predicate =
                new PropertyAddressContainsKeywordsPredicate(Collections.singletonList("Anchorvale"));
        assertTrue(predicate.test(new PropertyBuilder().withAddress("Anchorvale Bedok").build()));

        // Multiple keywords
        predicate = new PropertyAddressContainsKeywordsPredicate(Arrays.asList("Anchorvale", "Bedok"));
        assertTrue(predicate.test(new PropertyBuilder().withAddress("Anchorvale Bedok").build()));

        // Only one matching keyword
        predicate = new PropertyAddressContainsKeywordsPredicate(Arrays.asList("Bedok", "Cove"));
        assertTrue(predicate.test(new PropertyBuilder().withAddress("Anchorvale Bedok").build()));

        // Mixed-case keywords
        predicate = new PropertyAddressContainsKeywordsPredicate(Arrays.asList("aNchOrVAle", "BedOK"));
        assertTrue(predicate.test(new PropertyBuilder().withAddress("Anchorvale Bedok").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PropertyAddressContainsKeywordsPredicate predicate =
                new PropertyAddressContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PropertyBuilder().withAddress("Anchorvale").build()));

        // Non-matching keyword
        predicate = new PropertyAddressContainsKeywordsPredicate(Arrays.asList("Cove"));
        assertFalse(predicate.test(new PropertyBuilder().withAddress("Anchorvale Bedok").build()));

        // Keywords match property type and name, but does not match address
        predicate = new PropertyAddressContainsKeywordsPredicate(
                Arrays.asList("HDB", "House", "Main", "Street"));
        assertFalse(predicate.test(new PropertyBuilder().withAddress("Anchorvale")
                .withPropertyType("HDB")
                .withPropertyName("My House")
                .build()));
    }
}
