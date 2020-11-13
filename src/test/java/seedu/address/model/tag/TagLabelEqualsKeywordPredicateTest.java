package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.label.Label;
import seedu.address.testutil.TagBuilder;

class TagLabelEqualsKeywordPredicateTest {
    @Test
    public void equals() {
        Label firstPredicateLabel = new Label("first");
        Label secondPredicateLabel = new Label("second");

        TagLabelEqualsKeywordPredicate firstPredicate = new TagLabelEqualsKeywordPredicate(
                firstPredicateLabel);
        TagLabelEqualsKeywordPredicate secondPredicate = new TagLabelEqualsKeywordPredicate(
                secondPredicateLabel);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagLabelEqualsKeywordPredicate firstPredicateCopy = new TagLabelEqualsKeywordPredicate(
                firstPredicateLabel);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different label -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_labelEqualsKeyword_returnsTrue() {
        TagLabelEqualsKeywordPredicate predicate = new TagLabelEqualsKeywordPredicate(
                new Label("first"));
        assertTrue(predicate.test(new TagBuilder().withLabels("first").build()));
    }

    @Test
    public void test_tagLabelDoesNotEqualsKeyword_returnsFalse() {
        TagLabelEqualsKeywordPredicate predicate = new TagLabelEqualsKeywordPredicate(
                new Label("first"));
        assertFalse(predicate.test(new TagBuilder().withLabels("second").build()));
    }
}
