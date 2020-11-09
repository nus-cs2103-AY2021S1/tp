//@@author jerrylchong
package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonHasTagsAndNamePredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
        List<Tag> firstPredicateTagList = Collections.singletonList(new Tag("first"));
        List<Tag> secondPredicateTagList = Arrays.asList(new Tag("first"), new Tag("second"));

        PersonHasTagsAndNamePredicate firstPredicate = new PersonHasTagsAndNamePredicate(
                firstPredicateKeywordList, firstPredicateTagList);
        PersonHasTagsAndNamePredicate secondPredicate = new PersonHasTagsAndNamePredicate(
                secondPredicateKeywordList, secondPredicateTagList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasTagsAndNamePredicate firstPredicateCopy = new PersonHasTagsAndNamePredicate(
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
    public void test_nameMatchesKeywords_returnsTrue() {
        // One keyword
        PersonHasTagsAndNamePredicate predicate = new PersonHasTagsAndNamePredicate(
                Collections.singletonList("Alice Bob"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonHasTagsAndNamePredicate(
                Arrays.asList("Alice Bob", "Carol Tan"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_personHasTags_returnsTrue() {
        // one tag
        PersonHasTagsAndNamePredicate predicate = new PersonHasTagsAndNamePredicate(
                Collections.singletonList("Alice Bob"),
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "male").build()));

        // multiple tags
        predicate = new PersonHasTagsAndNamePredicate(
                Collections.singletonList("Alice Bob"),
                Arrays.asList(new Tag("friend"), new Tag("male")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "male").build()));

        // person has one of the tags
        predicate = new PersonHasTagsAndNamePredicate(
                Collections.singletonList("Alice Bob"),
                Arrays.asList(new Tag("prof"), new Tag("male")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "male").build()));
    }

    @Test
    public void test_personDoesNotHaveNameOrTags_returnsFalse() {
        // zero name keywords
        PersonHasTagsAndNamePredicate predicate = new PersonHasTagsAndNamePredicate(
                Collections.emptyList(),
                Collections.singletonList(new Tag("fake")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // not full name
        predicate = new PersonHasTagsAndNamePredicate(
                Collections.singletonList("Alice"),
                Collections.singletonList(new Tag("fake")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // lower-case name keyword
        predicate = new PersonHasTagsAndNamePredicate(
                Collections.singletonList("alice bob"),
                Collections.singletonList(new Tag("fake")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // mixed-case name keyword
        predicate = new PersonHasTagsAndNamePredicate(
                Collections.singletonList("aLice boB"),
                Collections.singletonList(new Tag("fake")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // name keywords match phone, email and address, but does not match name
        predicate = new PersonHasTagsAndNamePredicate(
                Arrays.asList("12345", "alice@email.com", "Main", "Street"),
                Collections.singletonList(new Tag("fake")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // no tag keywords
        predicate = new PersonHasTagsAndNamePredicate(
                Arrays.asList("fake"),
                Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // no matching tag
        predicate = new PersonHasTagsAndNamePredicate(
                Arrays.asList("fake"),
                Collections.singletonList(new Tag("prof")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // no matching tags
        predicate = new PersonHasTagsAndNamePredicate(
                Arrays.asList("fake"),
                Arrays.asList(new Tag("prof"), new Tag("male")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // mixed-case tag
        predicate = new PersonHasTagsAndNamePredicate(
                Arrays.asList("fake"),
                Collections.singletonList(new Tag("frIenD")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend").build()));

        // name and tags do not match
        predicate = new PersonHasTagsAndNamePredicate(
                Arrays.asList("Charles Darwin"),
                Collections.singletonList(new Tag("prof")));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").withTags("friend").build()));
    }
}
