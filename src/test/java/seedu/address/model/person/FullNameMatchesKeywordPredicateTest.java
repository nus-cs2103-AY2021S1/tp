package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class FullNameMatchesKeywordPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        FullNameMatchesKeywordPredicate firstPredicate = new FullNameMatchesKeywordPredicate(
                firstPredicateKeywordList);
        FullNameMatchesKeywordPredicate secondPredicate = new FullNameMatchesKeywordPredicate(
                secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FullNameMatchesKeywordPredicate firstPredicateCopy = new FullNameMatchesKeywordPredicate(
                firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameMatchesKeywords_returnsTrue() {
        // One keyword
        FullNameMatchesKeywordPredicate predicate = new FullNameMatchesKeywordPredicate(
                Collections.singletonList("Alice Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new FullNameMatchesKeywordPredicate(Arrays.asList("Alice Bob", "Carol Tan"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotMatchKeywords_returnsFalse() {
        // Zero keywords
        FullNameMatchesKeywordPredicate predicate = new FullNameMatchesKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new FullNameMatchesKeywordPredicate(Arrays.asList("Alice Bib"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Non-full name keyword
        predicate = new FullNameMatchesKeywordPredicate(Arrays.asList("Alice"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Lower-case keyword
        predicate = new FullNameMatchesKeywordPredicate(Arrays.asList("alice bob"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Mixed-case keyword
        predicate = new FullNameMatchesKeywordPredicate(Arrays.asList("AliCe bOb"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new FullNameMatchesKeywordPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }
}
