package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TagBuilder;

public class TagNameEqualsKeywordPredicateTest {
    @Test
    public void equals() {
        TagName firstPredicateTagName = new TagName("first");
        TagName secondPredicateTagName = new TagName("second");

        TagNameEqualsKeywordPredicate firstPredicate = new TagNameEqualsKeywordPredicate(
                firstPredicateTagName);
        TagNameEqualsKeywordPredicate secondPredicate = new TagNameEqualsKeywordPredicate(
                secondPredicateTagName);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagNameEqualsKeywordPredicate firstPredicateCopy = new TagNameEqualsKeywordPredicate(
                firstPredicateTagName);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different tag -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameEqualsKeyword_returnsTrue() {
        TagNameEqualsKeywordPredicate predicate = new TagNameEqualsKeywordPredicate(
                new TagName("first"));
        assertTrue(predicate.test(new TagBuilder().withTagName("first").build()));
    }

    @Test
    public void test_tagNameDoesNotContainKeywords_returnsFalse() {
        TagNameEqualsKeywordPredicate predicate = new TagNameEqualsKeywordPredicate(
                new TagName("first"));
        assertFalse(predicate.test(new TagBuilder().withTagName("second").build()));
    }
}
