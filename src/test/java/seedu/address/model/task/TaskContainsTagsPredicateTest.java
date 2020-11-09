package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.todolist.TaskBuilder;

public class TaskContainsTagsPredicateTest {

    @Test
    public void equals() {

        Set<Tag> firstPredicateTagSet = SampleDataUtil.getTagSet("first");
        Set<Tag> secondPredicateTagSet = SampleDataUtil.getTagSet("first", "second");

        TaskContainsTagsPredicate firstPredicate = new TaskContainsTagsPredicate(firstPredicateTagSet);
        TaskContainsTagsPredicate secondPredicate = new TaskContainsTagsPredicate(secondPredicateTagSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskContainsTagsPredicate firstPredicateCopy = new TaskContainsTagsPredicate(firstPredicateTagSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(6));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different values -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskContainsTags_returnsTrue() {
        // One tag
        TaskContainsTagsPredicate predicate =
                new TaskContainsTagsPredicate(SampleDataUtil.getTagSet("first"));
        assertTrue(predicate.test(new TaskBuilder().withTags("first").build()));

        // Multiple tags
        predicate = new TaskContainsTagsPredicate(SampleDataUtil.getTagSet("first", "second"));
        assertTrue(predicate.test(new TaskBuilder().withTags("first", "second").build()));

        // Only one matching tag
        predicate = new TaskContainsTagsPredicate(SampleDataUtil.getTagSet("first", "second"));
        assertTrue(predicate.test(new TaskBuilder().withTags("first", "third").build()));

        // Mixed-case tags
        predicate = new TaskContainsTagsPredicate(SampleDataUtil.getTagSet("fiRsT", "seCONd"));
        assertTrue(predicate.test(new TaskBuilder().withTags("first", "second").build()));
    }

    @Test
    public void test_taskDoesNotContainTags_returnsFalse() {
        TaskContainsTagsPredicate predicate =
                new TaskContainsTagsPredicate(SampleDataUtil.getTagSet("second"));

        // Task has no tags
        assertFalse(predicate.test(new TaskBuilder().withTags().build()));

        // Non-matching tag
        assertFalse(predicate.test(new TaskBuilder().withTags("first").build()));
    }
}
