package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleBuilder;

class CodeOrNameMatchesKeywordPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        CodeOrNameMatchesKeywordPredicate firstPredicate =
                new CodeOrNameMatchesKeywordPredicate(firstPredicateKeywordList);
        CodeOrNameMatchesKeywordPredicate secondPredicate =
                new CodeOrNameMatchesKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        CodeOrNameMatchesKeywordPredicate firstPredicateCopy =
                new CodeOrNameMatchesKeywordPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different module -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_codeContainsKeywords_returnsTrue() {
        // One keyword
        CodeOrNameMatchesKeywordPredicate predicate =
                new CodeOrNameMatchesKeywordPredicate(Collections.singletonList("CS2103"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));

        // Multiple keywords
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("CS2103", "CS2102"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));

        // Mixed-case keywords
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("cS2103", "Es2660"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        CodeOrNameMatchesKeywordPredicate predicate =
                new CodeOrNameMatchesKeywordPredicate(Collections.singletonList("Software"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));

        // Multiple keywords
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("Software", "Engineering"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));
        //Only one matching keyword
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("Software", "Programming"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));


        // Mixed-case keywords
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("SofTwAre", "eNginEERinG"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));
    }

    @Test
    public void test_codeAndNameContainsFullKeywords_returnsTrue() {
        // Two matching keywords
        CodeOrNameMatchesKeywordPredicate predicate =
                new CodeOrNameMatchesKeywordPredicate(Arrays.asList("CS2103", "Software"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));

        // Exact keywords
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("CS2103", "Software", "Engineering"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));

        // Multiple keywords
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("CS2103", "Software",
                "Engineering", "Methodology"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));
    }

    @Test
    public void test_codeAndNameContainsPartialKeywords_returnsTrue() {
        // Keyword matches code partially
        CodeOrNameMatchesKeywordPredicate predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("CS21"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));
        // Keyword matches name partially
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("Soft"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));
        // Keyword with mixed-case matches name partially
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("sOFtW"));
        assertTrue(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));
    }

    @Test
    public void test_codeAndNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        CodeOrNameMatchesKeywordPredicate predicate = new CodeOrNameMatchesKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));

        // Non-matching keyword
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("CS2100"));
        assertFalse(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));

        // Keyword match partially
        predicate = new CodeOrNameMatchesKeywordPredicate(Arrays.asList("CS21031"));
        assertFalse(predicate.test(new ModuleBuilder().withCode("CS2103").withName("Software Engineering").build()));
    }
}
