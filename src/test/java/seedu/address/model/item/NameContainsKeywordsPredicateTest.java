package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_NAME);
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate(secondPredicateKeywordList, PREFIX_NAME);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_NAME);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Item -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Chicken"), PREFIX_NAME);
        assertTrue(predicate.test(new ItemBuilder().withName("Chicken Duck").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Chicken", "Duck"), PREFIX_NAME);
        assertTrue(predicate.test(new ItemBuilder().withName("Chicken Duck").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Duck", "Beef"), PREFIX_NAME);
        assertTrue(predicate.test(new ItemBuilder().withName("Chicken Beef").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Chicken", "Duck"), PREFIX_NAME);
        assertTrue(predicate.test(new ItemBuilder().withName("Chicken Duck").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList(), PREFIX_NAME);
        assertFalse(predicate.test(new ItemBuilder().withName("Chicken").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Beef"), PREFIX_NAME);
        assertFalse(predicate.test(new ItemBuilder().withName("Chicken Duck").build()));

        // Keywords match quantity and supplier, but does not match name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("23", "Sheng", "Siong"), PREFIX_NAME);
        assertFalse(predicate.test(new ItemBuilder().withName("Chicken").withSupplier("23")
                .withSupplier("Sheng Shiong").build()));
    }
}
