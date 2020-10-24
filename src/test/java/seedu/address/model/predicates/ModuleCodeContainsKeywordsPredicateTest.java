package seedu.address.model.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.predicates.ModuleCodeContainsKeywordsPredicate;
import seedu.address.testutil.builders.ModuleBuilder;

class ModuleCodeContainsKeywordsPredicateTest {

    @Test
    void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        ModuleCodeContainsKeywordsPredicate firstPredicate =
                new ModuleCodeContainsKeywordsPredicate(firstPredicateKeyword);
        ModuleCodeContainsKeywordsPredicate secondPredicate =
                new ModuleCodeContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ModuleCodeContainsKeywordsPredicate firstPredicateCopy =
                new ModuleCodeContainsKeywordsPredicate(firstPredicateKeyword);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_moduleCodeContainsKeywords_returnsTrue() {
        // One keyword
        ModuleCodeContainsKeywordsPredicate predicate = new ModuleCodeContainsKeywordsPredicate("CS2103");
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").build()));

        // One matching partial keyword
        predicate = new ModuleCodeContainsKeywordsPredicate("CS2");
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").build()));

        // Mixed-case keyword
        predicate = new ModuleCodeContainsKeywordsPredicate("Cs2103");
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").build()));

        // Mixed-case partial keyword
        predicate = new ModuleCodeContainsKeywordsPredicate("cS2");
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").build()));
    }

    @Test
    public void test_codeDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        ModuleCodeContainsKeywordsPredicate predicate = new ModuleCodeContainsKeywordsPredicate("CS2104");
        assertFalse(predicate.test(new ModuleBuilder().withCode("CS2103").build()));

        // Keyword longer than the code
        predicate = new ModuleCodeContainsKeywordsPredicate("CS2103S");
        assertFalse(predicate.test(new ModuleBuilder().withCode("CS2103").build()));
    }
}
