package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AnimalBuilder;

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
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different animal -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(
                Collections.singletonList("Ahmeng"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Ahmeng Buttercup").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Ahmeng", "Buttercup"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Ahmeng Buttercup").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Buttercup", "Coco"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Buttercup Coco").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("ahMeNg", "buTTercup"));
        assertTrue(predicate.test(new AnimalBuilder().withName("Ahmeng Buttercup").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new AnimalBuilder().withName("Ahmeng").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Coco"));
        assertFalse(predicate.test(new AnimalBuilder().withName("Ahmeng Buttercup").build()));

        // Keywords match ID and species, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("000123", "Reticulated", "Python"));
        assertFalse(predicate.test(new AnimalBuilder().withName("Buttercup").withId("000123")
                .withSpecies("Reticulated Python").build()));
    }
}
