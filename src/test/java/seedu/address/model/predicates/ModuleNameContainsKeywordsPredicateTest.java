package seedu.address.model.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.predicates.ModuleNameContainsKeywordsPredicate;
import seedu.address.testutil.builders.ModuleBuilder;

class ModuleNameContainsKeywordsPredicateTest {

    @Test
    void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ModuleNameContainsKeywordsPredicate firstPredicate =
                new ModuleNameContainsKeywordsPredicate(firstPredicateKeywordList);
        ModuleNameContainsKeywordsPredicate secondPredicate =
                new ModuleNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        ModuleNameContainsKeywordsPredicate firstPredicateCopy =
                new ModuleNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different person -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_moduleNameContainsKeywords_returnsTrue() {
        // One keyword
        ModuleNameContainsKeywordsPredicate predicate =
                new ModuleNameContainsKeywordsPredicate(Collections.singletonList("Software"));
        assertTrue(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));

        // One matching partial keyword
        predicate = new ModuleNameContainsKeywordsPredicate(Collections.singletonList("Soft"));
        assertTrue(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));

        // full matching keywords
        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineering"));
        assertTrue(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));

        // Multiple matching partial keywords
        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("ware", "Eng"));
        assertTrue(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));

        // Mixed-case keyword
        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("SoFTwaRe", "ENginEeRInG"));
        assertTrue(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));

        // Mixed-case partial keyword
        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("SoFTw", "EnGInE"));
        assertTrue(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));
    }

    @Test
    public void test_codeDoesNotContainKeywords_returnsFalse() {
        // Multiple keywords
        ModuleNameContainsKeywordsPredicate predicate =
                new ModuleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineering", "Programming"));
        assertFalse(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));

        // Non-matching keyword
        predicate = new ModuleNameContainsKeywordsPredicate(Collections.singletonList("Softsware"));
        assertFalse(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));

        // Keyword longer than the code
        predicate = new ModuleNameContainsKeywordsPredicate(Collections.singletonList("Softwares"));
        assertFalse(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));

        predicate = new ModuleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineerings"));
        assertFalse(predicate.test(new ModuleBuilder().withName("Software Engineering").build()));

    }
}
