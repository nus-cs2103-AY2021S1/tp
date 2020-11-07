package seedu.simplykitchen.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.testutil.FoodBuilder;

public class TagSearchPredicateTest {

    @Test
    public void equals() {
        HashSet<Tag> firstSetofTags = new HashSet<>();
        firstSetofTags.add(new Tag("first"));
        HashSet<Tag> secondSetofTags = new HashSet<>();
        secondSetofTags.add(new Tag("second"));

        TagSearchPredicate firstPredicate =
                new TagSearchPredicate(firstSetofTags);
        new TagSearchPredicate(secondSetofTags);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagSearchPredicate firstPredicateCopy =
                new TagSearchPredicate(firstSetofTags);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different food -> returns false
        assertFalse(firstPredicate.equals(secondSetofTags));
    }

    @Test
    public void test_tagSearch_returnsTrue() {
        // Search for tag
        HashSet<Tag> setOfTags = new HashSet<>();
        setOfTags.add(new Tag("frozen"));
        TagSearchPredicate predicate = new TagSearchPredicate(setOfTags);
        assertTrue(predicate.test(new FoodBuilder().withTags("frozen").build()));
    }

    @Test
    public void test_noFoodItemWithMatchingTag_returnsFalse() {
        // Non-matching tag
        HashSet<Tag> setOfTags = new HashSet<>();
        setOfTags.add(new Tag("frozen"));
        TagSearchPredicate predicate = new TagSearchPredicate(setOfTags);
        assertFalse(predicate.test(new FoodBuilder().withTags("raw").build()));

        // Tag search query matches description, but does not match tags
        assertFalse(predicate.test(new FoodBuilder().withDescription("raw").build()));
    }
}

