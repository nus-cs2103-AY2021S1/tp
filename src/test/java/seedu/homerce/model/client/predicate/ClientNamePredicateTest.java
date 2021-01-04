package seedu.homerce.model.client.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.homerce.testutil.client.ClientBuilder;

public class ClientNamePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ClientNamePredicate firstPredicate = new ClientNamePredicate(firstPredicateKeywordList);
        ClientNamePredicate secondPredicate = new ClientNamePredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ClientNamePredicate firstPredicateCopy = new ClientNamePredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different client -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ClientNamePredicate predicate = new ClientNamePredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ClientBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new ClientNamePredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new ClientBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new ClientNamePredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new ClientBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new ClientNamePredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new ClientBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ClientNamePredicate predicate = new ClientNamePredicate(Collections.emptyList());
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new ClientNamePredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ClientBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and homerce, but does not match name
        predicate = new ClientNamePredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new ClientBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }
}
