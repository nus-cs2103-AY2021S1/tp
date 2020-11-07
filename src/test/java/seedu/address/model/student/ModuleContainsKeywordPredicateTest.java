package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.ModuleContainsKeywordsPredicate;
import seedu.address.testutil.ModuleBuilder;

public class ModuleContainsKeywordPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModuleContainsKeywordsPredicate firstPredicate =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleContainsKeywordsPredicate secondPredicate =
                new ModuleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleContainsKeywordsPredicate firstPredicateCopy =
                new ModuleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleIdContainsKeywords_returnsTrue() {
        // One keyword
        ModuleContainsKeywordsPredicate predicate =
                new ModuleContainsKeywordsPredicate(Collections.singletonList("CS2103T"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleId("CS2103T").build()));

        // Multiple keywords
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("CS21", "03T"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleId("CS2103T").build()));

        // Only one matching keyword
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("XX", "3T"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleId("CS2103T").build()));

        // Mixed-case keywords
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("cs2103t", "cs2101"));
        assertTrue(predicate.test(new ModuleBuilder().withModuleId("CS2103T CS2101").build()));
    }

    @Test
    public void test_moduleIdDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ModuleContainsKeywordsPredicate predicate = new ModuleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ModuleBuilder().withModuleId("CS2103T").build()));

        // Non-matching keyword
        predicate = new ModuleContainsKeywordsPredicate(Arrays.asList("GEH"));
        assertFalse(predicate.test(new ModuleBuilder().withModuleId("CS2103T").build()));
    }
}
