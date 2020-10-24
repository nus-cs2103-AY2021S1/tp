package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeadlineBuilder;

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
        assertTrue(predicate.test(new DeadlineBuilder().withTitle("Play Games").build()));

        // Multiple keywords
        predicate.setKeyword(PREFIX_DESCRIPTION, "League of Legends");
        assertTrue(predicate.test(new DeadlineBuilder().withTitle("Play").withDescription("League of Legends").build()));

        // Multiple keywords with partial match
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TYPE, "tod");
        assertTrue(predicate.test(new DeadlineBuilder().withType("todo").build()));

        // status keywords match status exactly
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_STATUS, "incomplete");
        assertTrue(predicate.test(new DeadlineBuilder().withStatus(State.INCOMPLETE).build()));

        predicate.setKeyword(PREFIX_STATUS, "complete");
        assertTrue(predicate.test(new DeadlineBuilder().withStatus(State.COMPLETE).build()));

        // Mixed-case keywords
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "mEeT aLiCe");
        assertTrue(predicate.test(new DeadlineBuilder().withTitle("Meet Alice").build()));
    }

    @Test
    public void test_findMatchedKeywords_returnsFalse() {
        // Zero keywords
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        assertFalse(predicate.test(new DeadlineBuilder().withTitle("Alice").build()));

        // Non-matching keyword
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_DATE_TIME, "01-01-2020 19:00");
        assertFalse(predicate.test(new DeadlineBuilder().withDateTime("01-02-2019 18:00").build()));

        // title Keywords does not match title
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "01-01-2020 12:00");
        assertFalse(predicate.test(new DeadlineBuilder().withTitle("Alice").withDateTime("01-01-2020 12:00")
                .withDescription("alice,email.com").withType("event").build()));


        // type Keywords does not match type
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TYPE, "deadline");
        assertFalse(predicate.test(new DeadlineBuilder().withType("event").withDateTime("01-01-2020 12:00")
                .withDescription("alice,email.com").withType("event").build()));


        // desc Keywords does not match description
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_DESCRIPTION, "play outside");
        assertFalse(predicate.test(new DeadlineBuilder().withType("event").withDateTime("01-01-2020 12:00")
                .withDescription("alice,email.com").withType("event").build()));


        // status Keywords does not match status
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_STATUS, "complet");
        assertFalse(predicate.test(new DeadlineBuilder().withType("event").withDateTime("01-01-2020 12:00")
                .withDescription("alice,email.com").withType("event").withStatus(State.COMPLETE).build()));

        // test with multiple repeated attributes
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "assignment");
        predicate.setKeyword(PREFIX_TITLE, "borrow");
        assertTrue(predicate.test(new DeadlineBuilder().withTitle("Submit assignment").build()));
        assertTrue(predicate.test(new DeadlineBuilder().withTitle("borrow").build()));
        assertFalse(predicate.test(new DeadlineBuilder().withTitle("random thing").build()));

        // test with multiple distinct attributes
        predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "assignment");
        predicate.setKeyword(PREFIX_TYPE, "event");
        assertTrue(predicate.test(new DeadlineBuilder().withTitle("submit assignment").withType("event").build()));
        assertFalse(predicate.test(new DeadlineBuilder().withTitle("submit assignment").withType("todo").build()));
        assertFalse(predicate.test(new DeadlineBuilder().withTitle("random").withType("event").build()));
    }
}
