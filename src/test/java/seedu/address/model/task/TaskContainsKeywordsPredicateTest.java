package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskContainsKeywordsPredicate firstPredicate = new TaskContainsKeywordsPredicate();
        firstPredicateKeywordList.forEach(word -> firstPredicate.setKeyword(PREFIX_TITLE, word));
        TaskContainsKeywordsPredicate secondPredicate = new TaskContainsKeywordsPredicate();
        secondPredicateKeywordList.forEach(word -> secondPredicate.setKeyword(PREFIX_TITLE, word));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsKeywordsPredicate firstPredicateCopy = new TaskContainsKeywordsPredicate();
        firstPredicateKeywordList.forEach(word -> firstPredicateCopy.setKeyword(PREFIX_TITLE, word));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_findMatchedKeywords_returnsTrue() {
        // One keyword
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "Play");
        assertTrue(predicate.test(new TaskBuilder().withTitle("Play Games").build()));

        // Multiple keywords
        predicate.setKeyword(PREFIX_DESCRIPTION, "League of Legends");
        assertTrue(predicate.test(new TaskBuilder().withTitle("Play").withDescription("League of Legends").build()));

        // Fuzzy matched
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_DESCRIPTION, "Leguo of Legends");
        assertTrue(predicate.test(new TaskBuilder().withDescription("League of Legends").build()));

        // Mixed-case keywords
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "mEeT aLiCe");
        assertTrue(predicate.test(new TaskBuilder().withTitle("Meet Alice").build()));
    }

    @Test
    public void test_findMatchedKeywords_returnsFalse() {
        // Zero keywords
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").build()));

        // Non-matching keyword
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_DATE_TIME, "01-01-2020 19:00");
        assertFalse(predicate.test(new TaskBuilder().withDateTime("01-02-2019 18:00").build()));

        // title Keywords match dateTime, description and type, but does not match title
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "01-01-2020 12:00");
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withDateTime("01-01-2020 12:00")
                .withDescription("alice,email.com").withType("event").build()));

    }
}
