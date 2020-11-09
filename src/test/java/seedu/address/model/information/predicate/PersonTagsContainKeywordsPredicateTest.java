package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTagsContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("cleaner");
        List<String> secondPredicateKeywordList = Arrays.asList("cleaner", "whiteCollar");

        PersonTagsContainKeywordsPredicate firstPredicate =
                new PersonTagsContainKeywordsPredicate(firstPredicateKeywordList);
        PersonTagsContainKeywordsPredicate secondPredicate =
                new PersonTagsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonTagsContainKeywordsPredicate firstPredicateCopy =
                new PersonTagsContainKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagsContainKeywords_returnsTrue() {
        // One keyword
        PersonTagsContainKeywordsPredicate predicate =
                new PersonTagsContainKeywordsPredicate(Collections.singletonList("cleaner"));
        assertTrue(predicate.test(new PersonBuilder().withTags("whiteCollar", "cleaner").build()));

        // Multiple keyword
        predicate = new PersonTagsContainKeywordsPredicate(Arrays.asList("cleaner", "whiteCollar"));
        assertTrue(predicate.test(new PersonBuilder().withTags("whiteCollar", "cleaner", "CEO").build()));

        // Exact matching
        predicate = new PersonTagsContainKeywordsPredicate(Arrays.asList("whiteCollar", "cleaner"));
        assertTrue(predicate.test(new PersonBuilder().withTags("whiteCollar", "cleaner").build()));

        // Zero keywords
        predicate = new PersonTagsContainKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new PersonBuilder().withTags("whiteCollar", "cleaner").build()));

        // Mixed-case keywords
        predicate = new PersonTagsContainKeywordsPredicate(Arrays.asList("whItEcoLlaR", "cLeAnEr"));
        assertTrue(predicate.test(new PersonBuilder().withTags("whiteCollar", "cleaner").build()));
    }

    @Test
    public void test_tagsDoNotContainKeywords_returnsFalse() {

        // Only one matching keyword, the rest do not match
        PersonTagsContainKeywordsPredicate predicate = new PersonTagsContainKeywordsPredicate(
                Arrays.asList("cleaner", "lowWage"));
        assertFalse(predicate.test(new PersonBuilder().withTags("whiteCollar", "cleaner").build()));

        // Non-matching keyword
        predicate = new PersonTagsContainKeywordsPredicate(Arrays.asList("CEO"));
        assertFalse(predicate.test(new PersonBuilder().withTags("whiteCollar", "cleaner").build()));

        // Keywords match phone, email and name, but does not match tags
        predicate = new PersonTagsContainKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "CEO"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTags("whiteCollar", "cleaner").build()));
    }
}
