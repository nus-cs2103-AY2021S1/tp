package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonHasTagsAndKeywordInNamePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        List<Tag> firstPredicateTagList = Collections.singletonList(new Tag("first"));
        List<Tag> secondPredicateTagList = Arrays.asList(new Tag("first"), new Tag("second"));

        PersonHasTagsAndKeywordInNamePredicate firstPredicate = new PersonHasTagsAndKeywordInNamePredicate(
                firstPredicateKeywordList, firstPredicateTagList);
        PersonHasTagsAndKeywordInNamePredicate secondPredicate = new PersonHasTagsAndKeywordInNamePredicate(
                secondPredicateKeywordList, secondPredicateTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasTagsAndKeywordInNamePredicate firstPredicateCopy = new PersonHasTagsAndKeywordInNamePredicate(
                firstPredicateKeywordList, firstPredicateTagList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One matching letter
        PersonHasTagsAndKeywordInNamePredicate predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Collections.singletonList("l"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // One keyword
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Collections.singletonList("lice"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("Alice", "Bob"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("Bob", "Carol"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("bOb", "cAroL"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_initialsContainsKeywords() {
        // One keyword
        PersonHasTagsAndKeywordInNamePredicate predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Collections.singletonList("ab"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("ab", "ac"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Mixed-case keywords
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("aB", "Ac"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_personHasTags_returnsTrue() {
        // one tag
        PersonHasTagsAndKeywordInNamePredicate predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Collections.singletonList("Alice Bob"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "male").build()));

        // multiple tags
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Collections.singletonList("Alice Bob"),
                Arrays.asList(new Tag("friend"), new Tag("male")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "male").build()));

        // person has one of the tags
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Collections.singletonList("Alice Bob"),
                Arrays.asList(new Tag("prof"), new Tag("male")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "male").build()));
    }

    @Test
    public void test_personDoesNotHaveKeywordOrInitialsOrTags_returnsFalse() {
        // zero name keywords
        PersonHasTagsAndKeywordInNamePredicate predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Collections.emptyList(),
                Collections.singletonList(new Tag("fake")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // no matching keyword
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Collections.singletonList("Carol"),
                Collections.singletonList(new Tag("fake")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // incomplete initials
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Collections.singletonList("ab"),
                Collections.singletonList(new Tag("fake")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob Carol").build()));

        // name keywords match phone, email and address, but does not match name
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"),
                Collections.singletonList(new Tag("fake")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // no tag keywords
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("fake"),
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // no matching tag
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("fake"),
                Collections.singletonList(new Tag("prof")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // no matching tags
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("fake"),
                Arrays.asList(new Tag("prof"), new Tag("male")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // mixed-case tag
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("fake"),
                Collections.singletonList(new Tag("frIenD")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // name and tags do not match
        predicate = new PersonHasTagsAndKeywordInNamePredicate(
                Arrays.asList("Charles", "ab"),
                Collections.singletonList(new Tag("prof")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob Carol").withTags("friend").build()));
    }
}
