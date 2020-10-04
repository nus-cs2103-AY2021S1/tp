package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLIER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class ItemContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ItemContainsKeywordsPredicate firstPredicate = new ItemContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_NAME);
        ItemContainsKeywordsPredicate secondPredicate = new ItemContainsKeywordsPredicate(secondPredicateKeywordList, PREFIX_NAME);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ItemContainsKeywordsPredicate firstPredicateCopy = new ItemContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_NAME);
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
        ItemContainsKeywordsPredicate predicate =
                new ItemContainsKeywordsPredicate(Collections.singletonList("Chicken"), PREFIX_NAME);
        assertTrue(predicate.test(new ItemBuilder().withName("Chicken Duck").build()));

        // Multiple keywords
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Chicken", "Duck"), PREFIX_NAME);
        assertTrue(predicate.test(new ItemBuilder().withName("Chicken Duck").build()));

        // Only one matching keyword
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Duck", "Beef"), PREFIX_NAME);
        assertTrue(predicate.test(new ItemBuilder().withName("Chicken Beef").build()));

        // Mixed-case keywords
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Chicken", "Duck"), PREFIX_NAME);
        assertTrue(predicate.test(new ItemBuilder().withName("Chicken Duck").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ItemContainsKeywordsPredicate predicate = new ItemContainsKeywordsPredicate(Collections.emptyList(), PREFIX_NAME);
        assertFalse(predicate.test(new ItemBuilder().withName("Chicken").build()));

        // Non-matching keyword
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Beef"), PREFIX_NAME);
        assertFalse(predicate.test(new ItemBuilder().withName("Chicken Duck").build()));

        // Keywords match quantity and supplier, but does not match name
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("23", "Sheng", "Siong"), PREFIX_NAME);
        assertFalse(predicate.test(new ItemBuilder().withName("Chicken").withSupplier("23")
                .withSupplier("Sheng Shiong").build()));
    }

    @Test
    public void test_supplierContainsKeywords_returnsTrue() {
        // One keyword
        ItemContainsKeywordsPredicate predicate =
                new ItemContainsKeywordsPredicate(Collections.singletonList("NTUC"), PREFIX_SUPPLIER);
        assertTrue(predicate.test(new ItemBuilder().withSupplier("NTUC").build()));

        // Multiple keywords
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Sheng", "Siong"), PREFIX_SUPPLIER);
        assertTrue(predicate.test(new ItemBuilder().withSupplier("Sheng Siong").build()));

        // Only one matching keyword
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Sheng", "NTUC"), PREFIX_SUPPLIER);
        assertTrue(predicate.test(new ItemBuilder().withSupplier("Sheng Siong").build()));

        // Mixed-case keywords
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Cold", "Storage"), PREFIX_SUPPLIER);
        assertTrue(predicate.test(new ItemBuilder().withSupplier("Cold Storage").build()));
    }

    @Test
    public void test_supplierDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ItemContainsKeywordsPredicate predicate = new ItemContainsKeywordsPredicate(Collections.emptyList(), PREFIX_SUPPLIER);
        assertFalse(predicate.test(new ItemBuilder().withSupplier("NTUC").build()));

        // Non-matching keyword
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("NTUC"), PREFIX_SUPPLIER);
        assertFalse(predicate.test(new ItemBuilder().withSupplier("Cold Storage").build()));

        // Keywords match name and tag, but does not match supplier
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Chicken", "meat"), PREFIX_SUPPLIER);
        assertFalse(predicate.test(new ItemBuilder().withName("Chicken").withTags("meat")
                .build()));
    }

    @Test
    public void test_tagsContainsKeywords_returnsTrue() {
        // One keyword
        ItemContainsKeywordsPredicate predicate =
                new ItemContainsKeywordsPredicate(Collections.singletonList("meat"), PREFIX_TAG);
        assertTrue(predicate.test(new ItemBuilder().withTags("meat","premium").build()));

        // Mixed-case keywords
        predicate = new ItemContainsKeywordsPredicate(Collections.singletonList("Meat"), PREFIX_TAG);
        assertTrue(predicate.test(new ItemBuilder().withTags("meat", "premium").build()));
    }

    @Test
    public void test_tagsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ItemContainsKeywordsPredicate predicate = new ItemContainsKeywordsPredicate(Collections.emptyList(), PREFIX_TAG);
        assertFalse(predicate.test(new ItemBuilder().withTags("meat").build()));

        // Non-matching keyword
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("fish"), PREFIX_SUPPLIER);
        assertFalse(predicate.test(new ItemBuilder().withTags("meat").build()));

        // Keywords match name and supplier, but does not match tag
        predicate = new ItemContainsKeywordsPredicate(Arrays.asList("Chicken", "NTUC"), PREFIX_TAG);
        assertFalse(predicate.test(new ItemBuilder().withName("Chicken").withSupplier("NTUC")
                .build()));
    }




}
