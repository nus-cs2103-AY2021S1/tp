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

public class PersonHasTagsPredicateTest {

    @Test
    public void equals() {
        List<Tag> firstPredicateKeywordList = Collections.singletonList(new Tag("first"));
        List<Tag> secondPredicateKeywordList = Arrays.asList(new Tag("first"), new Tag("second"));

        PersonHasTagsPredicate firstPredicate = new PersonHasTagsPredicate(firstPredicateKeywordList);
        PersonHasTagsPredicate secondPredicate = new PersonHasTagsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonHasTagsPredicate firstPredicateCopy = new PersonHasTagsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personHasTags_returnsTrue() {
        // one tag
        PersonHasTagsPredicate predicate = new PersonHasTagsPredicate(
                Collections.singletonList(new Tag("friend")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "male").build()));

        // multiple tags
        predicate = new PersonHasTagsPredicate(Arrays.asList(new Tag("friend"), new Tag("male")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "male").build()));

        // person has one of the tags
        predicate = new PersonHasTagsPredicate(Arrays.asList(new Tag("prof"), new Tag("male")));
        assertTrue(predicate.test(new PersonBuilder().withTags("friend", "male").build()));
    }

    @Test public void test_personDoesNotHaveTags_returnFalse() {
        // no tags
        PersonHasTagsPredicate predicate = new PersonHasTagsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().build()));

        // person does not have the matching tag
        predicate = new PersonHasTagsPredicate(Arrays.asList(new Tag("prof")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend", "male").build()));

        // person does not have any of the matching tags
        predicate = new PersonHasTagsPredicate(Arrays.asList(new Tag("prof"), new Tag("female")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend", "male").build()));

        // mixed-case tags
        predicate = new PersonHasTagsPredicate(Arrays.asList(new Tag("Male"), new Tag("maLe")));
        assertFalse(predicate.test(new PersonBuilder().withTags("friend", "male").build()));
    }
}
