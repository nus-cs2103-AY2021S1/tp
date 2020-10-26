package seedu.address.model.item;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.ItemBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NameIsExactlyPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Collections.singletonList("second");

        NameIsExactlyPredicate firstPredicate = new NameIsExactlyPredicate(firstPredicateKeywordList);
        NameIsExactlyPredicate secondPredicate = new NameIsExactlyPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        NameIsExactlyPredicate firstPredicateCopy = new NameIsExactlyPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicateCopy, firstPredicate);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different predicate -> returns false
        assertNotEquals(secondPredicate, firstPredicate);

        // non-empty predicate -> keyword not null
        assertNotNull(firstPredicate.getKeyword());

        // empty predicate -> assertion error on getKeyword
        NameIsExactlyPredicate emptyPredicate = new NameIsExactlyPredicate(Collections.emptyList());
        assertThrows(AssertionError.class, emptyPredicate::getKeyword);
    }

    @Test
    public void test_nameIsExactly_returnsTrue() {
        // exact word, exact case -> true
        NameIsExactlyPredicate predicate = new NameIsExactlyPredicate(Collections.singletonList("Apple"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple").build()));

        // exact word, mixed case -> true
        predicate = new NameIsExactlyPredicate(Collections.singletonList("apple"));
        assertTrue(predicate.test(new ItemBuilder().withName("Apple").build()));
    }

    @Test
    public void test_nameIsExactly_returnsFalse() {
        // Zero keywords
        NameIsExactlyPredicate predicate = new NameIsExactlyPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ItemBuilder().withName("Apple").build()));

        // Non-matching keyword
        predicate = new NameIsExactlyPredicate(Collections.singletonList("Carrot"));
        assertFalse(predicate.test(new ItemBuilder().withName("Apple").build()));
    }
}
