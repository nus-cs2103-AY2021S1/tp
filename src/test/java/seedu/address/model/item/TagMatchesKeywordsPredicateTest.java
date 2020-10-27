package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTags.getTypicalTagSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class TagMatchesKeywordsPredicateTest {

    @Test
    public void nullConstructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagMatchesKeywordsPredicate(null));
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TagMatchesKeywordsPredicate firstPredicate = new TagMatchesKeywordsPredicate(firstPredicateKeywordList);
        TagMatchesKeywordsPredicate secondPredicate = new TagMatchesKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TagMatchesKeywordsPredicate firstPredicateCopy = new TagMatchesKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different predicate -> returns false
        assertNotEquals(secondPredicate, firstPredicate);
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        TagMatchesKeywordsPredicate predicate = new TagMatchesKeywordsPredicate(Collections.singletonList("abc"));
        assertTrue(predicate.test(new ItemBuilder().withTags(getTypicalTagSet()).build()));

        // Multiple keywords
        predicate = new TagMatchesKeywordsPredicate(Arrays.asList("abc", "tuturu"));
        assertTrue(predicate.test(new ItemBuilder().withTags(getTypicalTagSet()).build()));

        // Only one matching keyword
        predicate = new TagMatchesKeywordsPredicate(Arrays.asList("abc", "yolo"));
        assertTrue(predicate.test(new ItemBuilder().withTags(getTypicalTagSet()).build()));

        // Mixed-case keywords
        predicate = new TagMatchesKeywordsPredicate(Arrays.asList("aBc", "tutUru"));
        assertTrue(predicate.test(new ItemBuilder().withTags(getTypicalTagSet()).build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagMatchesKeywordsPredicate predicate = new TagMatchesKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ItemBuilder().withName("Apple Banana").build()));

        // Non-matching keyword
        predicate = new TagMatchesKeywordsPredicate(Collections.singletonList("Carrot"));
        assertFalse(predicate.test(new ItemBuilder().withName("Apple Banana").build()));
    }
}
