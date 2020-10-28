package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class NameMatchesKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameMatchesKeywordsPredicate firstPredicate = new NameMatchesKeywordsPredicate(firstPredicateKeywordList);
        NameMatchesKeywordsPredicate secondPredicate = new NameMatchesKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        NameMatchesKeywordsPredicate firstPredicateCopy = new NameMatchesKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different predicate -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_zeroKeywords_throwsException() {
        assertThrows(AssertionError.class, () -> new NameMatchesKeywordsPredicate(Collections.emptyList()));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameMatchesKeywordsPredicate predicate = new NameMatchesKeywordsPredicate(Collections.singletonList("Apple"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana").build()));

        // Multiple keywords
        predicate = new NameMatchesKeywordsPredicate(Arrays.asList("Apple", "Banana"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana").build()));

        // Only one matching keyword
        predicate = new NameMatchesKeywordsPredicate(Arrays.asList("Banana", "Carrot"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Carrot").build()));

        // Mixed-case keywords
        predicate = new NameMatchesKeywordsPredicate(Arrays.asList("ApPlE", "bAnAnA"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple Banana").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        NameMatchesKeywordsPredicate predicate = new NameMatchesKeywordsPredicate(Collections.singletonList("Carrot"));
        assertFalse(predicate.test(new ItemBuilder().withName("Apple Banana").build()));
    }
}
