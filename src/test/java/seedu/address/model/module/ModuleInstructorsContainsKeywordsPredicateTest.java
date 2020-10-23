package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.predicates.ModuleInstructorsContainsKeywordsPredicate;
import seedu.address.testutil.ModuleBuilder;

class ModuleInstructorsContainsKeywordsPredicateTest {
    @Test
    void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModuleInstructorsContainsKeywordsPredicate firstPredicate =
                new ModuleInstructorsContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleInstructorsContainsKeywordsPredicate secondPredicate =
                new ModuleInstructorsContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ModuleInstructorsContainsKeywordsPredicate firstPredicateCopy =
                new ModuleInstructorsContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_moduleInstructorsContainsKeywords_returnsTrue() {
        // One matching keyword
        ModuleInstructorsContainsKeywordsPredicate predicate =
                new ModuleInstructorsContainsKeywordsPredicate(Collections.singletonList("BENSON"));
        assertTrue(predicate.test(new ModuleBuilder().withInstructors(ALICE, BENSON, CARL).build()));

        // Multiple matching keywords
        predicate = new ModuleInstructorsContainsKeywordsPredicate(Arrays.asList("BENSON", "CARL"));
        assertTrue(predicate.test(new ModuleBuilder().withInstructors(ALICE, BENSON, CARL).build()));

        // Multiple keywords, only one matching
        predicate = new ModuleInstructorsContainsKeywordsPredicate(Arrays.asList("HITLER", "STALIN", "BENSON"));
        assertTrue(predicate.test(new ModuleBuilder().withInstructors(ALICE, BENSON, CARL).build()));

        // Mixed-case keyword
        predicate = new ModuleInstructorsContainsKeywordsPredicate(Collections.singletonList("BeNSoN"));
        assertTrue(predicate.test(new ModuleBuilder().withInstructors(ALICE, BENSON, CARL).build()));
    }

    @Test
    public void test_moduleInstructorsDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ModuleInstructorsContainsKeywordsPredicate predicate =
                new ModuleInstructorsContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ModuleBuilder().withInstructors(ALICE, BENSON, CARL).build()));

        // Non-matching keywords
        predicate = new ModuleInstructorsContainsKeywordsPredicate(Arrays.asList("HITLER", "STALIN"));
        assertFalse(predicate.test(new ModuleBuilder().withInstructors(ALICE, BENSON, CARL).build()));

        // Partial keyword
        predicate = new ModuleInstructorsContainsKeywordsPredicate(Collections.singletonList("BEN"));
        assertFalse(predicate.test(new ModuleBuilder().withInstructors(ALICE, BENSON, CARL).build()));

        // Keyword longer than the code
        predicate = new ModuleInstructorsContainsKeywordsPredicate(Collections.singletonList("ALICES"));
        assertFalse(predicate.test(new ModuleBuilder().withInstructors(ALICE, BENSON, CARL).build()));
    }
}
