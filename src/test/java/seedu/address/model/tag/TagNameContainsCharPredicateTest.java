package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TagBuilder;

class TagNameContainsCharPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagNameContainsKeywordsPredicate firstPredicate = new TagNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        TagNameContainsKeywordsPredicate secondPredicate = new TagNameContainsKeywordsPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagNameContainsKeywordsPredicate firstPredicateCopy = new TagNameContainsKeywordsPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagNameContainsKeyword_returnsTrue() {
        //Words contain same character whole character Keyword
        TagNameContainsCharPredicate firstPredicate = new TagNameContainsCharPredicate(
                Arrays.asList("test"));
        assertTrue(firstPredicate.test(new TagBuilder().withTagName("test").build()));

        //Keyword contains less character
        assertTrue(firstPredicate.test(new TagBuilder().withTagName("test2").build()));

        //Words start with different prefix
        assertTrue(firstPredicate.test(new TagBuilder().withTagName("abctest123").build()));

        //Words end with different suffix
        assertTrue(firstPredicate.test(new TagBuilder().withTagName("test123Hello").build()));

        //Words contain keyword in the middle
        assertTrue(firstPredicate.test(new TagBuilder().withTagName("HellotestWorld").build()));
    }

    @Test
    public void test_keywordMissing_returnsFalse() {
        TagNameContainsCharPredicate firstPredicate = new TagNameContainsCharPredicate(
                Arrays.asList("arr", "4567"));

        //Words contain one character longer than keyword
        assertFalse(firstPredicate.test(new TagBuilder().withTagName("ar").build()));

        //Different Keyword
        assertFalse(firstPredicate.test(new TagBuilder().withTagName("1234").build()));
    }
}
