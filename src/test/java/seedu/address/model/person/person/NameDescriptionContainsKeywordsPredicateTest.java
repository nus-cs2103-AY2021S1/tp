package seedu.address.model.person.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameDescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameDescriptionContainsKeywordsPredicate firstPredicate =
                new NameDescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        NameDescriptionContainsKeywordsPredicate secondPredicate =
                new NameDescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameDescriptionContainsKeywordsPredicate firstPredicateCopy =
                new NameDescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameDescriptionContainsKeywords_returnsTrue() {
        // One keyword
        NameDescriptionContainsKeywordsPredicate predicate =
                new NameDescriptionContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords (name)
        predicate = new NameDescriptionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords (description)
        predicate = new NameDescriptionContainsKeywordsPredicate(Arrays.asList("Business", "analyst"));
        assertTrue(predicate.test(new PersonBuilder().withDescription("Business analyst").build()));

        // Only one matching keyword (name)
        predicate = new NameDescriptionContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Only one matching keyword (mix)
        predicate = new NameDescriptionContainsKeywordsPredicate(Arrays.asList("Carol", "user"));
        assertTrue(predicate.test(new PersonBuilder().withName("Bob").withDescription("End user").build()));

        // Mixed-case keywords
        predicate = new NameDescriptionContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDescriptionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameDescriptionContainsKeywordsPredicate predicate =
                new NameDescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword (name)
        predicate = new NameDescriptionContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withDescription().build()));

        // Non-matching keyword (description)
        predicate = new NameDescriptionContainsKeywordsPredicate(Arrays.asList("analyst"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withDescription("End user").build()));

        // Keywords match phone and email, but does not match name
        predicate = new NameDescriptionContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }
}
