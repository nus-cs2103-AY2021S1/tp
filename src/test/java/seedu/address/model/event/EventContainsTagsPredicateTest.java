package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.event.EventBuilder;

public class EventContainsTagsPredicateTest {
    @Test
    public void equals() {

        Set<Tag> firstPredicateTagSet = SampleDataUtil.getTagSet("first");
        Set<Tag> secondPredicateTagSet = SampleDataUtil.getTagSet("first", "second");

        EventContainsTagsPredicate firstPredicate = new EventContainsTagsPredicate(firstPredicateTagSet);
        EventContainsTagsPredicate secondPredicate = new EventContainsTagsPredicate(secondPredicateTagSet);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventContainsTagsPredicate firstPredicateCopy = new EventContainsTagsPredicate(firstPredicateTagSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(6));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different values -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_contactContainsTags_returnsTrue() {
        // One tag
        EventContainsTagsPredicate predicate =
                new EventContainsTagsPredicate(SampleDataUtil.getTagSet("first"));
        assertTrue(predicate.test(new EventBuilder().withTags("first").build()));

        // Multiple tags
        predicate = new EventContainsTagsPredicate(SampleDataUtil.getTagSet("first", "second"));
        assertTrue(predicate.test(new EventBuilder().withTags("first", "second").build()));

        // Only one matching tag
        predicate = new EventContainsTagsPredicate(SampleDataUtil.getTagSet("first", "second"));
        assertTrue(predicate.test(new EventBuilder().withTags("first", "third").build()));

        // Mixed-case tags
        predicate = new EventContainsTagsPredicate(SampleDataUtil.getTagSet("fiRsT", "seCONd"));
        assertTrue(predicate.test(new EventBuilder().withTags("first", "second").build()));
    }

    @Test
    public void test_contactDoesNotContainTags_returnsFalse() {
        EventContainsTagsPredicate predicate =
                new EventContainsTagsPredicate(SampleDataUtil.getTagSet("second"));

        // Contact has no tags
        assertFalse(predicate.test(new EventBuilder().withTags().build()));

        // Non-matching tag
        assertFalse(predicate.test(new EventBuilder().withTags("first").build()));
    }
}
