package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ContactMatchesFindKeywordPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateNameKeywordList = Collections.singletonList("first");
        String firstPredicateTagKeyword = "first";
        List<String> secondPredicateNameKeywordList = Arrays.asList("first", "second");
        String secondPredicateTagKeyword = "second";

        ContactMatchesFindKeywordPredicate firstPredicate = new ContactMatchesFindKeywordPredicate(
            firstPredicateNameKeywordList, firstPredicateTagKeyword);
        ContactMatchesFindKeywordPredicate secondPredicate = new ContactMatchesFindKeywordPredicate(
            secondPredicateNameKeywordList, firstPredicateTagKeyword);
        ContactMatchesFindKeywordPredicate thirdPredicate = new ContactMatchesFindKeywordPredicate(
            firstPredicateNameKeywordList, secondPredicateTagKeyword);


        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ContactMatchesFindKeywordPredicate firstPredicateCopy = new ContactMatchesFindKeywordPredicate(
            firstPredicateNameKeywordList, firstPredicateTagKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different name keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different tag keyword -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_contactMatchesKeyword_returnsTrue() {
        // One name keyword
        ContactMatchesFindKeywordPredicate predicate =
            new ContactMatchesFindKeywordPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple name keywords matching tag
        predicate = new ContactMatchesFindKeywordPredicate(Arrays.asList("Alice", "Bob"), "friends");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("colleagues", "friends").build()));

        // Only one matching name keyword matching tag
        predicate = new ContactMatchesFindKeywordPredicate(Arrays.asList("Bob", "Carol"), "classmate");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").withTags("classmate").build()));

        // Mixed-case name keywords mixed-case matching tag
        predicate = new ContactMatchesFindKeywordPredicate(Arrays.asList("aLIce", "bOB"), "fRiENds");
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friends").build()));


    }

    @Test
    public void test_contactDoesNotMatch_returnsFalse() {
        ContactMatchesFindKeywordPredicate predicate;

        // Non-matching name keyword
        predicate = new ContactMatchesFindKeywordPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Non-matching tag keyword
        predicate = new ContactMatchesFindKeywordPredicate(Arrays.asList("Alice"), "friends");
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("colleagues").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new ContactMatchesFindKeywordPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
