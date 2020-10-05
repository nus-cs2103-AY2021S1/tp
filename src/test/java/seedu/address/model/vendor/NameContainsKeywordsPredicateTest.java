package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.VendorBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        seedu.address.model.vendor.NameContainsKeywordsPredicate firstPredicate = new seedu.address.model
                .vendor.NameContainsKeywordsPredicate(firstPredicateKeywordList);
        seedu.address.model.vendor.NameContainsKeywordsPredicate secondPredicate = new seedu.address.model
                .vendor.NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        seedu.address.model.vendor.NameContainsKeywordsPredicate firstPredicateCopy = new seedu.address.model
                .vendor.NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different vendor -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        seedu.address.model.vendor.NameContainsKeywordsPredicate predicate = new seedu.address.model
                .vendor.NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new VendorBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new seedu.address.model.vendor.NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new VendorBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new seedu.address.model.vendor.NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new VendorBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new seedu.address.model.vendor.NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new VendorBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        seedu.address.model.vendor.NameContainsKeywordsPredicate predicate = new seedu.address.model
                .vendor.NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new VendorBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new seedu.address.model.vendor.NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new VendorBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new VendorBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
