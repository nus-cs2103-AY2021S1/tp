package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(8));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different value -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleNameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("CS2100"));
        assertTrue(predicate.test(new ModuleBuilder().withName("CS2100").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("CS2100", "CS2103T"));
        assertTrue(predicate.test(new ModuleBuilder().withName("CS2100").build()));
    }

    @Test
    public void test_moduleNameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("CS2100"));
        assertFalse(predicate.test(new ModuleBuilder().withName("CS2103T").build()));

        // Multiple non-matching keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("CS2100", "CS2030"));
        assertFalse(predicate.test(new ModuleBuilder().withName("CS2103T").build()));
    }
}
