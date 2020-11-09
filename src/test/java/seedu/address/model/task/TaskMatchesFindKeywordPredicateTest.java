package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.TodoBuilder;

public class TaskMatchesFindKeywordPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateNameKeywordList = Collections.singletonList("first");
        String firstPredicateTagKeyword = "first";
        List<String> secondPredicateNameKeywordList = Arrays.asList("first", "second");
        String secondPredicateTagKeyword = "second";

        TaskMatchesFindKeywordPredicate firstPredicate = new TaskMatchesFindKeywordPredicate(
            firstPredicateNameKeywordList, firstPredicateTagKeyword);
        TaskMatchesFindKeywordPredicate secondPredicate = new TaskMatchesFindKeywordPredicate(
            secondPredicateNameKeywordList, firstPredicateTagKeyword);
        TaskMatchesFindKeywordPredicate thirdPredicate = new TaskMatchesFindKeywordPredicate(
            firstPredicateNameKeywordList, secondPredicateTagKeyword);


        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskMatchesFindKeywordPredicate firstPredicateCopy = new TaskMatchesFindKeywordPredicate(
            firstPredicateNameKeywordList, firstPredicateTagKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different desc keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different tag keyword -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_eventMatchesKeyword_returnsTrue() {
        // One desc keyword
        TaskMatchesFindKeywordPredicate predicate =
            new TaskMatchesFindKeywordPredicate(Collections.singletonList("meeting"));
        assertTrue(predicate.test(new EventBuilder().withDescription("Attend meeting").build()));

        // Multiple name keywords matching tag
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("Attend", "meeting"), "important");
        assertTrue(predicate.test(new EventBuilder()
            .withDescription("Attend meeting").withTags("urgent", "important").build()));

        // Only one matching name keyword matching tag
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("Homework", "meeting"), "important");
        assertTrue(predicate.test(new EventBuilder().withDescription("Attend meeting").withTags("important").build()));

        // Mixed-case name keywords mixed-case matching tag
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("atteND", "meEtinG"), "imPORtanT");
        assertTrue(predicate.test(new EventBuilder().withDescription("Attend meeting").withTags("important").build()));
    }

    @Test
    public void test_todoMatchesKeyword_returnsTrue() {
        // One desc keyword
        TaskMatchesFindKeywordPredicate predicate =
            new TaskMatchesFindKeywordPredicate(Collections.singletonList("quiz"));
        assertTrue(predicate.test(new TodoBuilder().withDescription("Do quiz").build()));

        // Multiple name keywords matching tag
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("Do", "quiz"), "cs2100");
        assertTrue(predicate.test(new TodoBuilder().withDescription("Do quiz").withTags("urgent", "cs2100").build()));

        // Only one matching name keyword matching tag
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("Do", "quiz"), "cs2100");
        assertTrue(predicate.test(new TodoBuilder().withDescription("Do quiz").withTags("cs2100").build()));

        // Mixed-case name keywords mixed-case matching tag
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("DO", "qUiZ"), "cS2100");
        assertTrue(predicate.test(new TodoBuilder().withDescription("Do quiz").withTags("cs2100").build()));
    }

    @Test
    public void test_eventDoesNotMatchKeywords_returnsFalse() {
        TaskMatchesFindKeywordPredicate predicate;

        // Non-matching desc keyword
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("party"));
        assertFalse(predicate.test(new EventBuilder().withDescription("Attend meeting").build()));

        // Non-matching tag keyword
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("meeting"), "unimportant");
        assertFalse(predicate.test(new EventBuilder().withDescription("Attend meeting").withTags("important").build()));
    }

    @Test
    public void test_todoDoesNotMatchKeywords_returnsFalse() {
        TaskMatchesFindKeywordPredicate predicate;

        // Non-matching desc keyword
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("party"));
        assertFalse(predicate.test(new TodoBuilder().withDescription("Do quiz").build()));

        // Non-matching tag keyword
        predicate = new TaskMatchesFindKeywordPredicate(Arrays.asList("quiz"), "unimportant");
        assertFalse(predicate.test(new TodoBuilder().withDescription("Do quiz").withTags("important").build()));
    }
}
