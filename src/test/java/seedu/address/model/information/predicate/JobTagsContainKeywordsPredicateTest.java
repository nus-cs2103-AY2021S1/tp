package seedu.address.model.information.predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.JobBuilder;

public class JobTagsContainKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("cleaner");
        List<String> secondPredicateKeywordList = Arrays.asList("cleaner", "whiteCollar");

        JobTagsContainKeywordsPredicate firstPredicate =
                new JobTagsContainKeywordsPredicate(firstPredicateKeywordList);
        JobTagsContainKeywordsPredicate secondPredicate =
                new JobTagsContainKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        JobTagsContainKeywordsPredicate firstPredicateCopy =
                new JobTagsContainKeywordsPredicate(firstPredicateKeywordList);
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
        JobTagsContainKeywordsPredicate predicate = new JobTagsContainKeywordsPredicate(
                Collections.singletonList("cleaner"));
        assertTrue(predicate.test(new JobBuilder().withTags("whiteCollar", "cleaner").build()));

        // Multiple keyword
        predicate = new JobTagsContainKeywordsPredicate(Arrays.asList("cleaner", "whiteCollar"));
        assertTrue(predicate.test(new JobBuilder().withTags("whiteCollar", "cleaner", "CEO").build()));

        // Exact matching
        predicate = new JobTagsContainKeywordsPredicate(Arrays.asList("whiteCollar", "cleaner"));
        assertTrue(predicate.test(new JobBuilder().withTags("whiteCollar", "cleaner").build()));

        // Zero keywords
        predicate = new JobTagsContainKeywordsPredicate(Collections.emptyList());
        assertTrue(predicate.test(new JobBuilder().withTags("whiteCollar", "cleaner").build()));

        // Mixed-case keywords
        predicate = new JobTagsContainKeywordsPredicate(Arrays.asList("whItEcoLlaR", "cLeAnEr"));
        assertTrue(predicate.test(new JobBuilder().withTags("whiteCollar", "cleaner").build()));
    }

    @Test
    public void test_tagsDoNotContainKeywords_returnsFalse() {

        // Only one matching keyword, the rest do not match
        JobTagsContainKeywordsPredicate predicate = new JobTagsContainKeywordsPredicate(
                Arrays.asList("cleaner", "lowWage"));
        assertFalse(predicate.test(new JobBuilder().withTags("whiteCollar", "cleaner").build()));

        // Non-matching keyword
        predicate = new JobTagsContainKeywordsPredicate(Arrays.asList("CEO"));
        assertFalse(predicate.test(new JobBuilder().withTags("whiteCollar", "cleaner").build()));

        // Keywords match phone and email, but does not match tags
        predicate = new JobTagsContainKeywordsPredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street", "CEO"));
        assertFalse(predicate.test(new JobBuilder().withJobTitle("Alice").withPhone("12345")
                .withEmail("alice@email.com").withTags("whiteCollar", "cleaner").build()));
    }
}
